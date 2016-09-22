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
import com.hellen.bean.Main_Flower;

/**
 * @author zhangyh2 OrnamentalFlowerInterface 下午2:22:16 TODO
 */
public interface OrnamentalFlowerInterface extends BaseView {

	void isShowTitle(boolean show);
	
	void onSucces();
	
	void onFaild();

	public interface OrnamentalFlowerBiz extends BasePresenter {
		void findOrnamental(BaseListener<List<Main_Flower>> listener);
	}
}
