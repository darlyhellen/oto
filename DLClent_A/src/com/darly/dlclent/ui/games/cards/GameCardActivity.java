/**下午1:58:33
 * @author zhangyh2
 * GameCardActivity.java
 * TODO
 */
package com.darly.dlclent.ui.games.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.dlclent.R;
import com.darly.dlclent.base.APPEnum;
import com.darly.dlclent.base.BaseActivity;
import com.darly.dlclent.ui.games.cards.adapter.GameCardAdapter;
import com.darly.dlclent.ui.games.cards.model.GameCard;
import com.darly.dlclent.widget.listview.WholeGridView;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 GameCardActivity 下午1:58:33 TODO
 */
@ContentView(R.layout.activity_game_cards)
public class GameCardActivity extends BaseActivity implements OnClickListener {
	@ViewInject(R.id.game_cards_gv)
	private WholeGridView gv;

	private List<GameCard> data;

	private GameCardAdapter adapter;

	@ViewInject(R.id.header_back)
	private ImageView back;
	@ViewInject(R.id.header_title)
	private TextView title;
	@ViewInject(R.id.header_other)
	private ImageView other;

	private float X;
	private float Y;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		title.setText("2048");
		back.setVisibility(View.VISIBLE);
		other.setVisibility(View.INVISIBLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#loadData()
	 */
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		data = new ArrayList<GameCard>();
		for (int i = 0; i < 16; i++) {
			GameCard card = new GameCard();
			card.setCard_num(new Random().nextBoolean() ? 2 : 4);
			card.setCard_position(i);
			card.setCard_title(i + "");
			data.add(card);
		}
		gv.setLayoutParams(new LayoutParams(APPEnum.WIDTH.getLen(),
				APPEnum.WIDTH.getLen()));
		adapter = new GameCardAdapter(data, R.layout.activity_game_cards_item,
				this);
		gv.setAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.dlclent.base.BaseActivity#initListener()
	 */
	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		back.setOnClickListener(this);
		gv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 按下
					X = event.getX();
					Y = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					// 移动
					break;
				case MotionEvent.ACTION_UP:
					// 抬起
					if (X >= event.getX()) {
						if (Y >= event.getY()) {
							if (Y - event.getY() > X - event.getX()) {
								LogUtils.i("向上移动");
								goUp();
							} else {
								LogUtils.i("向左移动");
								goLeft();
							}
						} else {
							if (event.getY() - Y > X - event.getX()) {
								LogUtils.i("向下移动");
								goDown();
							} else {
								LogUtils.i("向左移动");
								goLeft();
							}
						}
					} else {
						if (Y >= event.getY()) {
							if (Y - event.getY() > event.getX() - X) {
								LogUtils.i("向上移动");
								goUp();
							} else {
								LogUtils.i("向右移动");
								goRight();
							}
						} else {
							if (event.getY() - Y > event.getX() - X) {
								LogUtils.i("向下移动");
								goDown();
							} else {
								LogUtils.i("向右移动");
								goRight();
							}
						}
					}

					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.header_back:
			finish();
			break;

		default:
			break;
		}
	}

	public void goUp() {
		for (int i = 15; i < 16; i--) {
			if (i <= 3) {
				break;
			}
			GameCard fis = data.get(i);
			GameCard ner = data.get(i - 4);
			if (!fis.isChecked()) {
				if (fis.getCard_num() == ner.getCard_num()) {
					ner.setCard_num(fis.getCard_num() + ner.getCard_num());
					ner.setChecked(true);
					fis.setCard_num(2);
					fis.setChecked(true);
				}
			}

		}
		adapter.setData(data);
	}

	public void goDown() {
		for (int i = 0; i < 16 - 4; i++) {
			GameCard fis = data.get(i);
			GameCard ner = data.get(i + 4);
			if (!fis.isChecked()) {
				if (fis.getCard_num() == ner.getCard_num()) {
					ner.setCard_num(fis.getCard_num() + ner.getCard_num());
					ner.setChecked(true);
					fis.setCard_num(2);
					fis.setChecked(true);
				}
			}

		}
		adapter.setData(data);
	}

	public void goRight() {
		for (int i = 0; i < 16 - 1; i++) {
			if (i % 4 == 3) {
				continue;
			}
			GameCard fis = data.get(i);
			GameCard ner = data.get(i + 1);
			if (!fis.isChecked()) {
				if (fis.getCard_num() == ner.getCard_num()) {
					ner.setCard_num(fis.getCard_num() + ner.getCard_num());
					ner.setChecked(true);
					fis.setCard_num(2);
					fis.setChecked(true);
				}
			}

		}
		adapter.setData(data);
	}

	public void goLeft() {
		for (int i = 15; i < 16; i--) {
			if (i <= 0) {
				break;
			}
			if (i % 4 == 0) {
				continue;
			}
			GameCard fis = data.get(i);
			GameCard ner = data.get(i - 1);
			if (!fis.isChecked()) {
				if (fis.getCard_num() == ner.getCard_num()) {
					ner.setCard_num(fis.getCard_num() + ner.getCard_num());
					ner.setChecked(true);
					fis.setCard_num(2);
					fis.setChecked(true);
				}
			}
		}
		adapter.setData(data);
	}
}
