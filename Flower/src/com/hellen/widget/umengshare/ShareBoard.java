package com.hellen.widget.umengshare;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.hellen.common.LogApp;
import com.hellen.common.ToastApp;
import com.hellen.flower.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * 
 */
public class ShareBoard extends PopupWindow implements OnClickListener {

	private Context mActivity;

	private String title;

	private String desc;

	private String icon;

	private String url;

	public ShareBoard(Context activity) {
		super(activity);
		this.mActivity = activity;
		initView(activity);
	}

	@SuppressWarnings("deprecation")
	private void initView(Context context) {
		View rootView = LayoutInflater.from(context).inflate(
				R.layout.custom_board, null);
		rootView.findViewById(R.id.wechat).setOnClickListener(this);
		rootView.findViewById(R.id.wechat_circle).setOnClickListener(this);
		rootView.findViewById(R.id.qq).setOnClickListener(this);
		rootView.findViewById(R.id.qzone).setOnClickListener(this);
		setContentView(rootView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		setBackgroundDrawable(new BitmapDrawable());
		setTouchable(true);
	}

	public void setContent(String title, String desc, String icon, String url) {
		this.title = title;
		this.desc = desc;
		this.icon = icon;
		this.url = url;
	}

	public void showBoard() {
		showAtLocation(((Activity) mActivity).getWindow().getDecorView(),
				Gravity.BOTTOM, 0, 0);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.wechat:
			performShare(SHARE_MEDIA.WEIXIN);
			break;
		case R.id.wechat_circle:
			performShare(SHARE_MEDIA.WEIXIN_CIRCLE);
			break;
		case R.id.qq:
			performShare(SHARE_MEDIA.QQ);
			break;
		case R.id.qzone:
			performShare(SHARE_MEDIA.QZONE);
			break;
		default:
			break;
		}
	}

	private void performShare(SHARE_MEDIA platform) {
		ShareAction shareAction = new ShareAction((Activity) mActivity);
		UMImage image = new UMImage(mActivity, icon);
		shareAction.withText(desc);
		shareAction.withMedia(image);
		shareAction.withTitle(title);
		shareAction.withTargetUrl(url);
		shareAction.setPlatform(platform).setCallback(umShareListener).share();
		dismiss();
	}

	private UMShareListener umShareListener = new UMShareListener() {
		@Override
		public void onResult(SHARE_MEDIA platform) {
			LogApp.d("plat", "platform" + platform);
			if (platform.name().equals("WEIXIN_FAVORITE")) {
				ToastApp.showToast(platform + " 收藏成功啦");
			} else {
				ToastApp.showToast(platform + " 分享成功啦");
			}
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			ToastApp.showToast(platform + " 分享失败啦");
			if (t != null) {
				LogApp.d("throw", "throw:" + t.getMessage());
			}
		}

		@Override
		public void onCancel(SHARE_MEDIA platform) {
			ToastApp.showToast(platform + " 分享取消了");
		}
	};

}
