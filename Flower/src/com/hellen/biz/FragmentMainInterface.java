/**下午3:40:08
 * @author zhangyh2
 * MainInterface.java
 * TODO
 */
package com.hellen.biz;

import java.util.List;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;
import com.hellen.widget.carousel.Carousel.CarouselModel;

/**
 * @author zhangyh2 MainInterface 下午3:40:08 TODO
 */
public interface FragmentMainInterface extends BaseView {

	void setImageViewSize();

	void onBannarSuccess();

	void onBannarFailed();

	interface MainBiz extends BasePresenter {
		void main_bannar(BaseListener<List<CarouselModel>> listener);
	}
}
