/**上午10:05:19
 * @author zhangyh2
 * ShowVideoActivity.java
 * TODO
 */
package com.darly.dlclent.ui.video;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.os.Bundle;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.ClientVideo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 ShowVideoActivity 上午10:05:19 TODO
 */
@ContentView(R.layout.activity_show_video)
public class VideoShowActivity extends BaseActivity {
	@ViewInject(R.id.video_videoview)
	private VideoView vv;

	private ClientVideo video;

	private MediaController controller;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		video = (ClientVideo) getIntent().getSerializableExtra("video");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		if (video != null) {
			vv.setVideoPath(video.getVideoUrl());
			controller = new MediaController(this);
			vv.setMediaController(controller);
			vv.requestFocus();
			vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					mediaPlayer.setPlaybackSpeed(1.0f);
				}
			});
		} else {
			ToastApp.showToast("URL 为空");
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
	}

}
