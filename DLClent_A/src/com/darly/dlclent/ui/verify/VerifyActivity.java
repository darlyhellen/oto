/**
 * 上午10:08:06
 * @author zhangyh2
 * $
 * RegisterActivity.java
 * TODO
 */
package com.darly.dlclent.ui.verify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.BaseActivity;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 RegisterActivity $ 上午10:08:06 TODO
 */
@ContentView(R.layout.activity_verify)
public class VerifyActivity extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	@ViewInject(R.id.verify_tel)
	private Button tel;
	
	private String username;
	private String paseword;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		back.setVisibility(View.VISIBLE);
		title.setText("手机验证");
		other.setVisibility(View.INVISIBLE);
		tel.setText("通过短信验证身份");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		if (getIntent()!=null) {
			username = getIntent().getStringExtra("tel");
			paseword = getIntent().getStringExtra("pass");
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
		tel.setOnClickListener(this);
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
		case R.id.verify_tel:
			// 通过短信进行验证
			Intent intent = new Intent(this, VerifyPhonoActivity.class);
			intent.putExtra("tel", username);
			intent.putExtra("pass", paseword);
			startActivity(intent);
			break;
		case R.id.header_back:
			finish();
			break;
		case R.id.verify_nottel:
			// 手机不在身边
			break;

		default:
			break;
		}
	}
}
