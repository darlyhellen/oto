/**上午10:49:43
 * @author zhangyh2
 * AutoNavInterface.java
 * TODO
 */
package com.hellen.biz;

import com.amap.api.location.AMapLocation;
import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;

/**
 * @author zhangyh2 AutoNavInterface 上午10:49:43 TODO
 */
public interface AutoNavInterface {

	/**
	 * @author zhangyh2 AutoNavView 上午10:51:04 TODO View 的一些控制方法和参数获取
	 */
	interface AutoNavView extends BaseView {

		void setTextView(String text);

		void setDisableClick();

		void setEnableClick();
	}

	/**
	 * @author zhangyh2 AutoNavPresenter 上午10:51:07 TODO 业务需求接口
	 */
	interface AutoNavBiz extends BasePresenter {
		void location(BaseListener<AMapLocation> listener);
	}
}
