package com.darly.im.ui;

import java.io.InvalidClassException;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.im.common.CCPAppManager;
import com.darly.im.common.utils.ECNotificationManager;
import com.darly.im.common.utils.ECPreferenceSettings;
import com.darly.im.common.utils.ECPreferences;
import com.darly.im.common.utils.LogUtil;
import com.darly.im.common.utils.ToastUtil;
import com.darly.im.core.ClientUser;
import com.darly.im.storage.ContactSqlManager;
import com.darly.im.storage.ConversationSqlManager;
import com.darly.im.storage.GroupMemberSqlManager;
import com.darly.im.storage.GroupNoticeSqlManager;
import com.darly.im.storage.GroupSqlManager;
import com.darly.im.storage.IMessageSqlManager;
import com.darly.im.storage.ImgInfoSqlManager;
import com.darly.im.ui.chatting.IMChattingHelper;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDeskManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECGroupManager;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECNotifyOptions;
import com.yuntongxun.ecsdk.SdkErrorCode;

/**
 * Created by Jorstin on 2015/3/17.
 */
public class SDKCoreHelper implements ECDevice.InitListener , ECDevice.OnECDeviceConnectListener,ECDevice.OnLogoutListener {

    public static final String TAG = "SDKCoreHelper";
    public static final String ACTION_LOGOUT = "com.yuntongxun.ECDemo_logout";
    public static final String ACTION_SDK_CONNECT = "com.yuntongxun.Intent_Action_SDK_CONNECT";
    public static final String ACTION_KICK_OFF = "com.yuntongxun.Intent_ACTION_KICK_OFF";
    private static SDKCoreHelper sInstance;
    private Context mContext;
    private ECDevice.ECConnectState mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
    private ECInitParams mInitParams;
    private ECInitParams.LoginMode mMode = ECInitParams.LoginMode.FORCE_LOGIN;
    /**初始化错误*/
    public static final int ERROR_CODE_INIT = -3;
    
    public static final int WHAT_SHOW_PROGRESS = 0x101A;
	public static final int WHAT_CLOSE_PROGRESS = 0x101B;
    private boolean mKickOff = false;
    private ECNotifyOptions mOptions;
    public static SoftUpdate mSoftUpdate;
    
    private Handler handler;
    private SDKCoreHelper() {
    	initNotifyOptions();
    }

    public static SDKCoreHelper getInstance() {
        if (sInstance == null) {
            sInstance = new SDKCoreHelper();
        }
        return sInstance;
    }
    
    public synchronized void setHandler(final Handler handler) {
		this.handler = handler;
	}

    public static boolean isKickOff() {
        return getInstance().mKickOff;
    }

    public static void init(Context ctx) {
        init(ctx, ECInitParams.LoginMode.AUTO);
    }

    public static void init(Context ctx , ECInitParams.LoginMode mode) {
        getInstance().mKickOff = false;
        LogUtil.d(TAG , "[init] start regist..");
        ctx = APP.getInstance().getApplicationContext();
        getInstance().mMode = mode;
        getInstance().mContext = ctx;
        // 判断SDK是否已经初始化，没有初始化则先初始化SDK
        if(!ECDevice.isInitialized()) {
            getInstance().mConnect = ECDevice.ECConnectState.CONNECTING;
            // ECSDK.setNotifyOptions(getInstance().mOptions);
            ECDevice.initial(ctx, getInstance());

            postConnectNotify();
            return ;
        }
        LogUtil.d(TAG, " SDK has inited , then regist..");
        // 已经初始化成功，直接进行注册
        getInstance().onInitialized();
    }

    public static void setSoftUpdate(String version , String desc , boolean mode) {
        mSoftUpdate = new SoftUpdate(version ,desc ,mode);
    }
    
    private void initNotifyOptions() {
        if(mOptions == null) {
            mOptions = new ECNotifyOptions();
        }
        // 设置新消息是否提醒
        mOptions.setNewMsgNotify(true);
        // 设置状态栏通知图标
        mOptions.setIcon(R.drawable.ic_launcher);
        // 设置是否启用勿扰模式（不会声音/震动提醒）
        mOptions.setSilenceEnable(false);
        // 设置勿扰模式时间段（开始小时/开始分钟-结束小时/结束分钟）
        // 小时采用24小时制
        // 如果设置勿扰模式不启用，则设置勿扰时间段无效
        // 当前设置晚上11点到第二天早上8点之间不提醒
        mOptions.setSilenceTime(23, 0, 8, 0);
        // 设置是否震动提醒(如果处于免打扰模式则设置无效，没有震动)
        mOptions.enableShake(true);
        // 设置是否声音提醒(如果处于免打扰模式则设置无效，没有声音)
        mOptions.enableSound(true);
    }

    @Override
    public void onInitialized() {
        LogUtil.d(TAG, "ECSDK is ready");
        
        // 设置消息提醒
        ECDevice.setNotifyOptions(mOptions);

        // 设置SDK注册结果回调通知，当第一次初始化注册成功或者失败会通过该引用回调
        // 通知应用SDK注册状态
        // 当网络断开导致SDK断开连接或者重连成功也会通过该设置回调
        ECDevice.setOnChatReceiveListener(IMChattingHelper.getInstance());
        ECDevice.setOnDeviceConnectListener(this);

        ClientUser clientUser = CCPAppManager.getClientUser();
        if (mInitParams == null){
            mInitParams = ECInitParams.createParams();
        }
        mInitParams.reset();
        // 如：VoIP账号/手机号码/..
        mInitParams.setUserid(clientUser.getUserId());
        // appkey
        mInitParams.setAppKey(clientUser.getAppKey());
        // mInitParams.setAppKey(/*clientUser.getAppKey()*/"ff8080813d823ee6013d856001000029");
        // appToken
        mInitParams.setToken(clientUser.getAppToken());
        // mInitParams.setToken(/*clientUser.getAppToken()*/"d459711cd14b443487c03b8cc072966e");
        // ECInitParams.LoginMode.FORCE_LOGIN
        mInitParams.setMode(getInstance().mMode);

        // 如果有密码（VoIP密码，对应的登陆验证模式是）
        // ECInitParams.LoginAuthType.PASSWORD_AUTH
        if(!TextUtils.isEmpty(clientUser.getPassword())) {
            mInitParams.setPwd(clientUser.getPassword());
        }

        // 设置登陆验证模式（是否验证密码/如VoIP方式登陆）
        if(clientUser.getLoginAuthType() != null) {
            mInitParams.setAuthType(clientUser.getLoginAuthType());
        }

        if(!mInitParams.validate()) {
            ToastUtil.showMessage(R.string.regist_params_error);
            Intent failIntent = new Intent(ACTION_SDK_CONNECT);
            failIntent.putExtra("error", -1);
            mContext.sendBroadcast(failIntent);
            return ;
        }

        ECDevice.login(mInitParams);
        
    }

    @Override
    public void onConnect() {
        // Deprecated
    }

    @Override
    public void onDisconnect(ECError error) {
        // SDK与云通讯平台断开连接
        // Deprecated
    }

    @Override
    public void onConnectState(ECDevice.ECConnectState state, ECError error) {
        if(state == ECDevice.ECConnectState.CONNECT_FAILED && error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
            try {
                ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_REGIST_AUTO, "", true);
//                ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_FULLY_EXIT, true, true);
//                ECPreferences.savePreference(ECPreferenceSettings.SETTINGS_FULLY_EXIT, true, true);
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
            mKickOff = true;
            // 失败，账号异地登陆
            Intent intent = new Intent(ACTION_KICK_OFF);
            intent.putExtra("kickoffText" , error.errorMsg);
            mContext.sendBroadcast(intent);
            LauncherActivity.mLauncherUI.handlerKickOff(error.errorMsg);
            ECNotificationManager.getInstance().showKickoffNotification(mContext ,error.errorMsg);
        }
        getInstance().mConnect = state;
        Intent intent = new Intent(ACTION_SDK_CONNECT);
        intent.putExtra("error", error.errorCode);
        mContext.sendBroadcast(intent);
        postConnectNotify();
    }

    /**
     * 当前SDK注册状态
     * @return
     */
    public static ECDevice.ECConnectState getConnectState() {
        return getInstance().mConnect;
    }

    @Override
    public void onLogout() {
        getInstance().mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
        if(mInitParams != null && mInitParams.getInitParams() != null) {
            mInitParams.getInitParams().clear();
        }
        mInitParams = null;
        mContext.sendBroadcast(new Intent(ACTION_LOGOUT));
    }

    @Override
    public void onError(Exception exception) {
        LogUtil.e(TAG, "ECSDK couldn't start: " + exception.getLocalizedMessage());
        Intent intent = new Intent(ACTION_SDK_CONNECT);
        intent.putExtra("error", ERROR_CODE_INIT);
        mContext.sendBroadcast(intent);
        ECDevice.unInitial();
    }

    /**
     * 状态通知
     */
    private static void postConnectNotify() {
        if(getInstance().mContext instanceof LauncherActivity) {
            ((LauncherActivity) getInstance().mContext).onNetWorkNotify(getConnectState());
        }
    }

    public static void logout(boolean isNotice) {
    	ECDevice.NotifyMode notifyMode = (isNotice) ? ECDevice.NotifyMode.IN_NOTIFY : ECDevice.NotifyMode.NOT_NOTIFY;
        ECDevice.logout(notifyMode, getInstance());
        release();
    }

    public static void release() {
        getInstance().mKickOff = false;
        IMChattingHelper.getInstance().destroy();
        ContactSqlManager.reset();
        ConversationSqlManager.reset();
        GroupMemberSqlManager.reset();
        GroupNoticeSqlManager.reset();
        GroupSqlManager.reset();
        IMessageSqlManager.reset();
        ImgInfoSqlManager.reset();
    }

    /**
     * IM聊天功能接口
     * @return
     */
    public static ECChatManager getECChatManager() {
        ECChatManager ecChatManager = ECDevice.getECChatManager();
        LogUtil.d(TAG, "ecChatManager :" + ecChatManager);
        return ecChatManager;
    }

    /**
     * 群组聊天接口
     * @return
     */
    public static ECGroupManager getECGroupManager() {
        return ECDevice.getECGroupManager();
    }

    public static ECDeskManager getECDeskManager() {
        return ECDevice.getECDeskManager();
    }



    public static class SoftUpdate  {
        public String version;
        public String desc;
        public boolean force;

        public SoftUpdate(String version ,String desc, boolean force) {
            this.version = version;
            this.force = force;
            this.desc = desc;
        }
    }
    
    /**
     * 
     * @return返回底层so库 是否支持voip及会议功能 
     * true 表示支持 false表示不支持
     * 请在sdk初始化完成之后调用
     */
    public boolean isSupportMedia(){
    	
    	return ECDevice.isSupportMedia();
    }
    
    public static boolean hasFullSize(String inStr) {
		if (inStr.getBytes().length != inStr.length()) {
			return true;
		}
		return false;
	}

    /**
     * 判断服务是否自动重启
     * @return 是否自动重启
     */
    public static boolean isUIShowing() {
        return ECDevice.isInitialized();
    }
    
}
