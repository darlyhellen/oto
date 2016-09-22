/**下午2:45:08
 * @author zhangyh2
 * Register.java
 * TODO
 */
package com.hellen.biz.imp;

import com.hellen.base.BaseListener;
import com.hellen.biz.RegisterInterface.RegisterBiz;
import com.hellen.common.LogApp;

/**
 * @author zhangyh2 Register 下午2:45:08 TODO
 */
public class Register implements RegisterBiz {

	private String tag = getClass().getSimpleName();
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		LogApp.i(tag, "onStart");
	}

	@Override
	public void doSomething(BaseListener<String> listener) {
		// TODO Auto-generated method stub
		LogApp.i(tag, "doSomething");
	}

}
