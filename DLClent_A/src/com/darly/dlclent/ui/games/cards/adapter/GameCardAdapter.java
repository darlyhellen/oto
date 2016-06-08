/**下午2:24:59
 * @author zhangyh2
 * GameCardAdapter.java
 * TODO
 */
package com.darly.dlclent.ui.games.cards.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.adapter.ParentAdapter;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.ui.games.cards.model.GameCard;

/**
 * @author zhangyh2 GameCardAdapter 下午2:24:59 TODO
 */
public class GameCardAdapter extends ParentAdapter<GameCard> {

	/**
	 * 下午2:25:37
	 * 
	 * @author zhangyh2
	 */
	public GameCardAdapter(List<GameCard> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.adapter.ParentAdapter#HockView(int,
	 * android.view.View, android.view.ViewGroup, int, android.content.Context,
	 * java.lang.Object)
	 */
	@Override
	public View HockView(int position, View view, ViewGroup parent, int resID,
			Context context, GameCard t) {
		// TODO Auto-generated method stub
		ViewHocker hocker = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(resID, null);
			hocker = new ViewHocker();
			hocker.bg = (LinearLayout) view
					.findViewById(R.id.game_card_item_bg);

			hocker.bg.setLayoutParams(new LayoutParams(
					APPEnum.WIDTH.getLen() / 4, APPEnum.WIDTH.getLen() / 4));
			hocker.tv = (TextView) view.findViewById(R.id.game_card_item_tv);
			view.setTag(hocker);
		} else {
			hocker = (ViewHocker) view.getTag();
		}
		t.setChecked(false);
		t.setClm_x(position % 4);
		t.setClm_y((int) Math.floor(position / 4));

		hocker.tv.setText(t.getCard_num() + ""/*
											 * + "(" + t.getClm_x() + "," +
											 * t.getClm_y() + ")"
											 */);
		switch (t.getCard_num()) {
		case 2:
			hocker.bg.setBackgroundColor(Color.WHITE);
			break;
		case 4:
			hocker.bg.setBackgroundColor(Color.LTGRAY);
			break;
		case 8:
			hocker.bg.setBackgroundColor(Color.YELLOW);
			break;
		case 16:
			hocker.bg.setBackgroundColor(Color.GREEN);
			break;
		case 32:
			hocker.bg.setBackgroundColor(Color.GRAY);
			break;
		case 64:
			hocker.bg.setBackgroundColor(Color.RED);
			break;

		default:
			break;
		}
		return view;
	}

	class ViewHocker {
		LinearLayout bg;
		TextView tv;
	}

}
