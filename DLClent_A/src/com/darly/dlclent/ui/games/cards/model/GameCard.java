/**下午1:59:36
 * @author zhangyh2
 * GameCard.java
 * TODO
 */
package com.darly.dlclent.ui.games.cards.model;

/**
 * @author zhangyh2 GameCard 下午1:59:36 TODO
 */
public class GameCard {

	private int card_num;

	private int card_position;

	private String card_title;

	private int clm_x;

	private int clm_y;

	private boolean checked;

	public int getCard_num() {
		return card_num;
	}

	public void setCard_num(int card_num) {
		this.card_num = card_num;
	}

	public int getCard_position() {
		return card_position;
	}

	public void setCard_position(int card_position) {
		this.card_position = card_position;
	}

	public String getCard_title() {
		return card_title;
	}

	public void setCard_title(String card_title) {
		this.card_title = card_title;
	}

	public int getClm_x() {
		return clm_x;
	}

	public void setClm_x(int clm_x) {
		this.clm_x = clm_x;
	}

	public int getClm_y() {
		return clm_y;
	}

	public void setClm_y(int clm_y) {
		this.clm_y = clm_y;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
