/**上午10:21:20
 * @author zhangyh2
 * CommentWithFloorActivity.java
 * TODO
 */
package com.darly.dlclent.ui.comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APP;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.common.HttpClient;
import com.darly.dlclent.common.JsonUtil;
import com.darly.dlclent.common.SharePreferHelp;
import com.darly.dlclent.common.ToastApp;
import com.darly.dlclent.model.BaseModel;
import com.darly.dlclent.model.BaseModelPaser;
import com.darly.dlclent.widget.floorview.Comment;
import com.darly.dlclent.widget.floorview.CommentComparator;
import com.darly.dlclent.widget.floorview.DateFormatUtils;
import com.darly.dlclent.widget.floorview.FloorView;
import com.darly.dlclent.widget.floorview.SubComments;
import com.darly.dlclent.widget.floorview.SubFloorFactory;
import com.darly.dlclent.widget.load.ProgressDialogUtil;
import com.darly.dlclent.widget.roundedimage.RoundedImageView;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 CommentWithFloorActivity 上午10:21:20 TODO
 *         仿网页评论的盖楼效果。首先进行实现。然后考虑OOM问题。
 */
@ContentView(R.layout.activity_comment_floor)
public class CommentWithFloorActivity extends BaseActivity implements
		OnClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private LinearLayout container;
	private LayoutInflater inflater;

	private ProgressDialogUtil load;

	/**
	 * 下午3:06:14 TODO 用户头像集合
	 */
	private static final String[] IMAGES = new String[] {
			"http://pic2.ooopic.com/01/01/17/53bOOOPIC4e.jpg",
			"http://pic2.ooopic.com/01/01/17/39bOOOPICe8.jpg",
			"http://pic13.nipic.com/20110424/818468_090858462000_2.jpg",
			"http://thumbs.dreamstime.com/z/%C9%BD%C2%B7%BE%B6-20729104.jpg",
			"http://image.72xuan.com/cases/100305/600_600/1003051017041241.jpg",
			"http://pica.nipic.com/2007-11-14/20071114114452315_2.jpg",
			"http://md.cuncun8.com/media/cc8/upload/68192031/0c67e362be347607a877697f46c5f773/101104142242_2026.jpg",
			"http://pic16.nipic.com/20110824/8169416_135754121000_2.jpg",
			"http://b.hiphotos.bdimg.com/album/w%3D2048/sign=79f7b0c594cad1c8d0bbfb274b066509/5366d0160924ab18de9241dd34fae6cd7a890b57.jpg",
			"http://pic2.ooopic.com/01/01/18/42bOOOPIC6c.jpg" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		inflater = this.getLayoutInflater();
		container = (LinearLayout) findViewById(R.id.container);
		load = new ProgressDialogUtil(this);
		back.setVisibility(View.VISIBLE);
		title.setText("仿网易评论楼层");
		other.setVisibility(View.INVISIBLE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		List<Comment> datas = new ArrayList<Comment>();
		int id = 2;
		for (int i = 0; i < IMAGES.length; i++) {
			Comment useCom = null;
			switch (i) {
			case 0:
				useCom = new Comment(i, id, "测试数据——" + i, "user" + i,
						IMAGES[i], DateFormatUtils.format(new Date()));
				break;
			case 8:
			case 9:
				continue;
			default:
				useCom = new Comment(id, i, i + 2, "测试数据——" + i, "user" + i,
						IMAGES[i], DateFormatUtils.format(new Date()), i + 1);
				break;
			}
			datas.add(useCom);
		}
		Collections.sort(datas, CommentComparator.getInstance());
		startCommon(datas);
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

	/**
	 * 上午11:29:20
	 * 
	 * @author zhangyh2 TODO
	 * @param list
	 */
	private void addComment(Comment cmt, List<Comment> list) {
		// TODO Auto-generated method stub
		ViewGroup floor = (ViewGroup) inflater.inflate(
				R.layout.comment_list_item, null);
		TextView floor_date = (TextView) floor.findViewById(R.id.floor_date);
		TextView floor_username = (TextView) floor
				.findViewById(R.id.floor_username);
		TextView floor_content = (TextView) floor
				.findViewById(R.id.floor_content);
		RoundedImageView floor_icon = (RoundedImageView) floor
				.findViewById(R.id.floor_avater);
		floor_icon.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen() / 8,
				APPEnum.WIDTH.getLen() / 8));
		floor_date.setText(DateFormatUtils.formatPretty(DateFormatUtils
				.parse(cmt.getDate())));
		floor_username.setText(cmt.getUserName());
		floor_content.setText(cmt.getContent());
		imageLoader.displayImage(cmt.getIcon(), floor_icon);
		FloorView subFloors = (FloorView) floor.findViewById(R.id.sub_floors);
		if (cmt.getParentId() != Comment.NULL_PARENT) {

			SubComments cmts = new SubComments(addSubFloors(cmt.getParentId(),
					cmt.getFloorNum() - 1, list));
			subFloors.setComments(cmts);
			subFloors.setFactory(new SubFloorFactory());
			subFloors.setBoundDrawer(this.getResources().getDrawable(
					R.drawable.floor_bound));
			subFloors.init();
		} else {
			subFloors.setVisibility(View.GONE);
		}
		container.addView(floor);
	}

	/**
	 * 上午11:29:51
	 * 
	 * @author zhangyh2 TODO
	 * @param list2
	 */
	private List<Comment> addSubFloors(long parentId, int num,
			List<Comment> data) {
		// TODO Auto-generated method stub
		if (num == 0)
			return null;
		Comment[] cmts;
		cmts = new Comment[num];
		for (Comment cmt : data) {
			if (cmt.getId() == parentId)
				cmts[0] = cmt;
			if (cmt.getParentId() == parentId && cmt.getFloorNum() <= num)
				cmts[cmt.getFloorNum() - 1] = cmt;
		}
		ArrayList<Comment> list = new ArrayList<Comment>();
		for (int i = 0; i < cmts.length; i++) {
			list.add(cmts[i]);
		}
		return list;
	}

	/**
	 * 下午4:37:27
	 * 
	 * @author zhangyh2 TODO 进行网络请求数据
	 * @param datas
	 */
	private void startCommon(List<Comment> datas) {
		if (!APP.isNetworkConnected(this)) {
			ToastApp.showToast(R.string.neterror);
			return;
		} else {
			if (load != null && !load.isShowing()) {
				load.setMessage(R.string.xlistview_header_hint_loading);
				load.show();
			}
			String url = "";
			if (url != null && url.length() > 0) {
				// 网络请求
				HttpClient.get(this, url, null, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						paserFloor(arg0.result);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
					}
				});

			} else {
				// 轮播假数据
				String json = null;
				boolean time = false/*new Random().nextBoolean()*/;
				if (time) {
					BaseModel<List<Comment>> model = new BaseModel<List<Comment>>(
							200, "", datas);
					json = JsonUtil.pojo2Json(model);
				} else {
					BaseModel<List<Comment>> model = new BaseModel<List<Comment>>(
							110, "网络数据不存在", null);
					json = JsonUtil.pojo2Json(model);
				}
				paserFloor(json);
			}

		}
	}

	/**
	 * 下午4:35:37
	 * 
	 * @author zhangyh2 TODO 返回数据进行解析
	 */
	private void paserFloor(String json) {
		// TODO Auto-generated method stub
		load.dismiss();
		if (json == null) {
			return;
		}
		LogUtils.i(json);
		BaseModel<List<Comment>> data = new BaseModelPaser<List<Comment>>()
				.paserJson(json, new TypeToken<List<Comment>>() {
				});
		if (data != null && data.getCode() == 200) {
			SharePreferHelp.putValue(APPEnum.FLOOR.getDec(), json);
			for (Comment cmt : data.getData()) {
				addComment(cmt, data.getData());
			}
		} else {
			paserFloor(SharePreferHelp.getValue(APPEnum.FLOOR.getDec(), null));
			ToastApp.showToast(data.getMsg());
		}
	}

}
