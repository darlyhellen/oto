/**上午10:05:19
 * @author zhangyh2
 * ShowVideoActivity.java
 * TODO
 */
package com.darly.dlclent.ui.video;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.provider.MediaStore.Video.VideoColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.VideoListAdapter;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.base.ConsHttpUrl;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.model.ClientVideo;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 ShowVideoActivity 上午10:05:19 TODO
 */
@ContentView(R.layout.activity_video_list)
public class VideoListActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {
	@ViewInject(R.id.video_list)
	private ListView lv;
	private VideoListAdapter adapter;

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private ProgressDialogUtil loading;

	private List<Video> dataLocal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
		loading = new ProgressDialogUtil(this);
		loading.setMessage("加载中...");
		loading.show();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		getVideos();

		adapter = new VideoListAdapter(null, R.layout.item_video_list, this);
		lv.setAdapter(adapter);
	}

	/**
	 * 上午10:48:59
	 * 
	 * @author zhangyh2 TODO 获取服务端Video视频集合
	 */
	private void getVideos() {
		// TODO Auto-generated method stub
		HttpClient.get(this, ConsHttpUrl.CLIENTVIDEO, new RequestParams(),
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						loading.dismiss();
						paserVideo(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						loading.dismiss();
						ToastApp.showToast(R.string.neterror_norespanse);
					}
				});
	}

	/**
	 * 上午10:51:44
	 * 
	 * @author zhangyh2 TODO 解析服务器返回的视频JSON
	 */
	protected void paserVideo(String result) {
		// TODO Auto-generated method stub
		if (result == null) {
			return;
		}
		LogUtils.i(result);
		BaseModel<List<ClientVideo>> data = new BaseModelPaser<List<ClientVideo>>()
				.paserJson(result, new TypeToken<List<ClientVideo>>() {
				});
		if (data != null && data.getCode() == 200) {
			List<ClientVideo> vids = data.getData();
			dataLocal = getList();
			if (dataLocal != null && dataLocal.size() > 0) {
				for (Video video : dataLocal) {
					ClientVideo vi = new ClientVideo();
					vi.setVideoName(video.getDisplayName());
					vi.setVideoImage(video.getAlbum());
					vi.setVideoDescripe(video.getTitle());
					vi.setVideoUrl(video.getPath());
					vids.add(vi);
				}
			}
			adapter.setData(vids);
		} else {
			ToastApp.showToast(data.getMsg());
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
		back.setOnClickListener(this);
		lv.setOnItemClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ClientVideo video = (ClientVideo) parent.getItemAtPosition(position);
		if (video != null) {
			Intent intent = new Intent(this, VideoShowActivity.class);
			intent.putExtra("video", video);
			startActivity(intent);
		} else {
			ToastApp.showToast("数据为空");
		}
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
		case R.id.header_back:
			finish();
			break;

		default:
			break;
		}
	}

	public List<Video> getList() {
		List<Video> list = null;
		Cursor cursor = getContentResolver().query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		if (cursor != null) {
			list = new ArrayList<Video>();
			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor
						.getColumnIndexOrThrow(BaseColumns._ID));
				String title = cursor.getString(cursor
						.getColumnIndexOrThrow(MediaColumns.TITLE));
				String album = cursor.getString(cursor
						.getColumnIndexOrThrow(VideoColumns.ALBUM));
				String artist = cursor.getString(cursor
						.getColumnIndexOrThrow(VideoColumns.ARTIST));
				String displayName = cursor
						.getString(cursor
								.getColumnIndexOrThrow(MediaColumns.DISPLAY_NAME));
				String mimeType = cursor
						.getString(cursor
								.getColumnIndexOrThrow(MediaColumns.MIME_TYPE));
				String path = cursor.getString(cursor
						.getColumnIndexOrThrow(MediaColumns.DATA));
				long duration = cursor
						.getInt(cursor
								.getColumnIndexOrThrow(VideoColumns.DURATION));
				long size = cursor.getLong(cursor
						.getColumnIndexOrThrow(MediaColumns.SIZE));
				Video video = new Video(id, title, album, artist, displayName,
						mimeType, path, size, duration);
				list.add(video);
			}
			cursor.close();
		}
		return list;
	}

}
