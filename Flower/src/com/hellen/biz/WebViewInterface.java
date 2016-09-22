/**下午4:03:28
 * @author zhangyh2
 * WebViewInterface.java
 * TODO
 */
package com.hellen.biz;

import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;

/**
 * @author zhangyh2 WebViewInterface 下午4:03:28 TODO
 */
public interface WebViewInterface extends BaseView {

	void initHeader();

	void initWeb();

	interface WebViewBiz extends BasePresenter {

	}

}
