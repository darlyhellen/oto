/**下午2:39:49
 * @author zhangyh2
 * RegisterInterface.java
 * TODO
 */
package com.hellen.biz;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;

/**
 * @author zhangyh2 RegisterInterface 下午2:39:49 TODO
 */
public interface RegisterInterface extends BaseView {

	void setView();
	
	String findTel();

	void gtoText(String msg);

	void gtoText(int res);

	void disClicked();

	void enableClicked();

	void end();

	public interface RegisterBiz extends BasePresenter {

		void doSomething(BaseListener<String> listener);

	}

}
