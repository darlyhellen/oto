/**下午2:22:16
 * @author zhangyh2
 * OrnamentalFlowerInterface.java
 * TODO
 */
package com.hellen.biz;

import java.util.List;

import com.hellen.base.BaseListener;
import com.hellen.base.BasePresenter;
import com.hellen.base.BaseView;
import com.hellen.bean.Bannar;

/**
 * @author zhangyh2 OrnamentalFlowerInterface 下午2:22:16 TODO
 */
public interface VenusaurInterface extends BaseView{

	void isShowTitle(boolean show);

	void onSucces();

	void onFaild();

	public interface VenusaurBiz extends BasePresenter {
		void findVenusaur(BaseListener<List<Bannar>> listener);
	}
}
