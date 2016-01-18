/**上午10:21:20
 * @author zhangyh2
 * CommentWithFloorActivity.java
 * TODO
 */
package com.darly.dlclent.ui.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.widget.floorview.Comment;
import com.darly.dlclent.widget.floorview.CommentData;
import com.darly.dlclent.widget.floorview.DateFormatUtils;
import com.darly.dlclent.widget.floorview.FloorView;
import com.darly.dlclent.widget.floorview.SubComments;
import com.darly.dlclent.widget.floorview.SubFloorFactory;
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
	private List<Comment> datas;

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
		datas = new CommentData(this).getComments();
		for (Comment cmt : datas) {
			addComment(cmt);
		}

		LogUtils.i("systemtime"
				+ DateFormatUtils.format(new Date(System.currentTimeMillis())));
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
	 */
	private void addComment(Comment cmt) {
		// TODO Auto-generated method stub
		ViewGroup floor = (ViewGroup) inflater.inflate(
				R.layout.comment_list_item, null);
		TextView floor_date = (TextView) floor.findViewById(R.id.floor_date);
		TextView floor_username = (TextView) floor
				.findViewById(R.id.floor_username);
		TextView floor_content = (TextView) floor
				.findViewById(R.id.floor_content);
		floor_date.setText(DateFormatUtils.formatPretty(cmt.getDate()));
		floor_username.setText(cmt.getUserName());
		floor_content.setText(cmt.getContent());
		FloorView subFloors = (FloorView) floor.findViewById(R.id.sub_floors);
		if (cmt.getParentId() != Comment.NULL_PARENT) {

			SubComments cmts = new SubComments(addSubFloors(cmt.getParentId(),
					cmt.getFloorNum() - 1));
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
	 */
	private List<Comment> addSubFloors(long parentId, int num) {
		// TODO Auto-generated method stub
		if (num == 0)
			return null;
		Comment[] cmts;
		cmts = new Comment[num];
		for (Comment cmt : datas) {
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
}
