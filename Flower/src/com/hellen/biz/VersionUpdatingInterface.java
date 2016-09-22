/**上午10:58:17
 * @author zhangyh2
 * VersionUpdatingInterface.java
 * TODO
 */
package com.hellen.biz;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;

/**
 * @author zhangyh2 VersionUpdatingInterface 上午10:58:17 TODO
 */
public interface VersionUpdatingInterface extends BaseView {

	public interface VersionUpdatingBiz extends BasePresenter {

		void versionUpdate(BaseListener<String> listener);

	}

}
