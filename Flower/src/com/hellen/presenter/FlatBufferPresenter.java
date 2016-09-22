/**上午11:17:35
 * @author zhangyh2
 * FlatBufferPresenter.java
 * TODO
 */
package com.hellen.presenter;

import java.nio.ByteBuffer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hellen.bean.flatmodel.People;
import com.hellen.bean.flatmodel.PeopleList;
import com.hellen.bean.jsonmodel.PeopleListJson;
import com.hellen.biz.FlatBufferInterface;
import com.hellen.biz.imp.FlatBuffer;
import com.hellen.common.FindByteFromRES;
import com.hellen.common.LogApp;
import com.hellen.flower.R;

/**
 * @author zhangyh2 FlatBufferPresenter 上午11:17:35 TODO
 */
public class FlatBufferPresenter {
	private String TAG = getClass().getSimpleName();

	private FlatBufferInterface view;

	private FlatBufferInterface.FlatBufferBiz biz;

	public FlatBufferPresenter(FlatBufferInterface views) {
		super();
		this.view = views;
		biz = new FlatBuffer();
		view.setPersenter(biz);
	}

	/**
	 * 上午11:21:47
	 * 
	 * @author zhangyh2 TODO
	 */
	public void init() {
		// TODO Auto-generated method stub

	}

	/**
	 * 上午11:24:09
	 * 
	 * @author zhangyh2 TODO 点击事件
	 */
	public void clickDown(View v, TextView flat_time, TextView json_time,
			Context context) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.flat_flat:
			LogApp.i(TAG, Thread.currentThread().toString());
			String jsonTexts = new String(FindByteFromRES.readRawResource(
					context, R.raw.sample_json));
			byte[] buffer = FindByteFromRES.readRawResource(context, jsonTexts);
			FindByteFromRES.readRawResource(context, R.raw.sample_flatbuffer);
			long startTime = System.currentTimeMillis();
			ByteBuffer bb = ByteBuffer.wrap(buffer);
			PeopleList peopleList = PeopleList.getRootAsPeopleList(bb);
			toJson(peopleList);
			long timeTaken = System.currentTimeMillis() - startTime;
			String logText = "FlatBuffer : " + timeTaken + "ms";
			flat_time.setText(logText);
			LogApp.i(TAG, "loadFromFlatBuffer " + logText);
			break;
		case R.id.flat_json:
			String jsonText = new String(FindByteFromRES.readRawResource(
					context, R.raw.sample_json));
			long startTimes = System.currentTimeMillis();
			new Gson().fromJson(jsonText, PeopleListJson.class);
			long timeTakens = System.currentTimeMillis() - startTimes;
			String logTexts = "Json : " + timeTakens + "ms";
			json_time.setText(logTexts);
			LogApp.i(TAG, "loadFromJson " + logTexts);
			break;
		default:
			break;
		}
	}

	void toJson(PeopleList person) {
		LogApp.i(TAG, person.toString());
		LogApp.i(TAG, "peoplesLength" + person.peoplesLength());
		for (int i = 0; i < person.peoplesLength(); i++) {
			People people = person.peoples(i);
			LogApp.i(TAG, i + people.id() + people.name());
		}
	}

}
