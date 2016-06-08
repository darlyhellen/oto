/**上午11:08:18
 * @author zhangyh2
 * Pinker.java
 * TODO
 */
package com.darly.dlclent.ui.games.pike;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author zhangyh2 Pinker 上午11:08:18 TODO 实现一个彩绘类
 */
public class Pinker extends View {

	private Paint paint;

	private List<Point> points = new ArrayList<Point>();

	private Bitmap mBitmap; // 保存每次绘画的结果

	private Canvas tmpCanvas;

	public Pinker(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView();
	}

	public Pinker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public Pinker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	/**
	 * 上午11:18:26
	 * 
	 * @author zhangyh2 TODO初始化对象中的一些属性值
	 */
	private void initView() {
		// TODO Auto-generated method stub
		paint = new Paint();
		paint.setColor(Color.BLACK);
		/* 去锯齿 */
		paint.setAntiAlias(true);
		/* 设置paint的　style　为STROKE：空心 */
		paint.setStyle(Paint.Style.FILL);
		/* 设置paint的外框宽度 */
		paint.setStrokeWidth(2);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (mBitmap == null) {
			mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
					Bitmap.Config.ARGB_8888);
			tmpCanvas = new Canvas(mBitmap);
		}
		// 先将结果画到Bitmap上

		if (tmpCanvas != null && points.size() >= 1) {
			if (points.size() == 1) {
				tmpCanvas.drawPoint(points.get(0).x, points.get(0).y, paint);
			} else {
				for (int i = 1; i < points.size() - 1; i++) {
					tmpCanvas.drawLine(points.get(i).x, points.get(i).y,
							points.get(i - 1).x, points.get(i - 1).y, paint);
				}
			}
		}
		// 再把Bitmap画到canvas上
		canvas.drawBitmap(mBitmap, 0, 0, paint);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			points.clear();
			points.add(new Point((int) event.getX(), (int) event.getY()));
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			points.add(new Point((int) event.getX(), (int) event.getY()));
			invalidate();
			break;
		default:
			break;
		}
		return true;
	}

}
