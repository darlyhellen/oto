package com.hellen.flower.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.alipay.sdk.app.PayTask;
import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.hellen.flower.pay.alipay.AuthResult;
import com.hellen.flower.pay.alipay.OrderInfoUtil2_0;
import com.hellen.flower.pay.alipay.PayResult;
import com.hellen.flower.pay.weixin.Util;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.Map;

public class PayPop extends PopupWindow implements OnClickListener {


    public PayPop(Context context) {
        super();
        this.context = context;
        init();
    }

    /**
     * 下午1:29:10 TODO 系统参数。
     */
    private Context context;

    private Button item_popupwindows_camera;

    private Button item_popupwindows_Photo;

    private Button item_popupwindows_cancel;

    /**
     * 下午1:29:54
     *
     * @author Zhangyuhui PhotoPop.java TODO 初始化控件集合。
     */
    private void init() {
        // TODO Auto-generated method stub

        View view = LayoutInflater.from(context).inflate(R.layout.popupwindows,
                null);
        item_popupwindows_camera = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        item_popupwindows_camera.setText("微信支付");
        item_popupwindows_camera.setOnClickListener(this);
        item_popupwindows_Photo = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        item_popupwindows_Photo.setText("支付宝支付");
        item_popupwindows_Photo.setOnClickListener(this);
        item_popupwindows_cancel = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        item_popupwindows_cancel.setOnClickListener(this);

        setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(false);
        setContentView(view);

    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.item_popupwindows_camera:
                LogApp.i("微信支付");
                startWXpay();
                break;
            case R.id.item_popupwindows_Photo:
                LogApp.i("支付宝支付");
                startAlipay();
                break;
            case R.id.item_popupwindows_cancel:
                // 取消
                break;
            default:
                break;
        }
        dismiss();
    }


    /**
     * @param v 下午3:15:27
     * @author Zhangyuhui PhotoPop.java TODO 展示POP
     */
    public void show(View v) {
        showAtLocation(v, Gravity.CENTER, 0, 0);
    }

    private IWXAPI api;

    private void startWXpay() {
        //商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID，代码如下：
        api = WXAPIFactory.createWXAPI(context, ConsPay.WXAPPID);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
                try {
                    byte[] buf = Util.httpGet(url);
                    if (buf != null && buf.length > 0) {
                        String content = new String(buf);
                        Log.e("get server pay params:", content);
                        JSONObject json = new JSONObject(content);
                        if (null != json && !json.has("retcode")) {
                            PayReq request = new PayReq();
                            request.appId			= json.getString("appid");
                            request.partnerId		= json.getString("partnerid");
                            request.prepayId		= json.getString("prepayid");
                            request.nonceStr		= json.getString("noncestr");
                            request.timeStamp		= json.getString("timestamp");
                            request.packageValue	= json.getString("package");
                            request.sign			= json.getString("sign");
                            request.extData			= "app data"; // optional
                            api.sendReq(request);
                        } else {
                            LogApp.d("PAY_GET" + json.getString("retmsg"));
                        }
                    } else {
                        LogApp.d("PAY_GET");
                    }
                } catch (Exception e) {
                    LogApp.d("PAY_GET" + e.getMessage());
                }
            }
        }).start();

    }



    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastApp.showToast("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastApp.showToast("支付失败");
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    //进行授权，不过正式使用时，不考虑授权问题。
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        ToastApp.showToast("授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
                    } else {
                        // 其他状态值则为授权失败
                        ToastApp.showToast("授权失败" + String.format("authCode:%s", authResult.getAuthCode()));
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    /**
     * 支付宝支付业务
     */
    public void startAlipay() {
        if (TextUtils.isEmpty(ConsPay.APPID) || TextUtils.isEmpty(ConsPay.RSA_PRIVATE)) {
            ToastApp.showToast("需要配置APPID | RSA_PRIVATE");
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         * orderInfo的获取必须来自服务端；
         */
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(ConsPay.APPID);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, ConsPay.RSA_PRIVATE);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
