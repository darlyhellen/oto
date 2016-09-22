/**下午2:48:06
 * @author zhangyh2
 * WelComeInterface.java
 * TODO
 */
package com.hellen.biz;

import android.graphics.Bitmap;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;

/**
 * @author zhangyh2 WelComeInterface 下午2:48:06 TODO
 */
public interface WelComeInterface extends BaseView {

	void setVersion(String version);

	void setBack(Bitmap bitmap);

	void startAnim();

	interface WelComeBiz extends BasePresenter {
		
		void findBackGround(BaseListener<Bitmap> listener);
	}

}
