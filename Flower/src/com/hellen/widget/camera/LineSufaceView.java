/**上午10:04:31
 * @author zhangyh2
 * CamSufaceView.java
 * TODO
 */
package com.hellen.widget.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author zhangyh2 CamSufaceView 上午10:04:31 TODO 绘制矩形框进行匹配。
 */
/* 定义一个画矩形框的类 */
public class LineSufaceView extends SurfaceView implements
		SurfaceHolder.Callback {

	protected SurfaceHolder sh;

	public Rect rect;

	private int RectHeight = 200;

	public LineSufaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		sh = getHolder();
		sh.addCallback(this);
		sh.setFormat(PixelFormat.TRANSPARENT);
		setZOrderOnTop(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int w, int h) {
		// TODO Auto-generated method stub
		rect = new Rect();
		rect.left = w / 2 - RectHeight;
		rect.top = h / 2 - RectHeight;
		rect.right = rect.left + RectHeight * 2;
		rect.bottom = rect.top + RectHeight * 2;
		drawLine();
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	void clearDraw() {
		Canvas canvas = sh.lockCanvas();
		canvas.drawColor(Color.BLUE);
		sh.unlockCanvasAndPost(canvas);
	}

	public void drawLine() {
		Canvas canvas = sh.lockCanvas();
		canvas.drawColor(Color.TRANSPARENT);
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setColor(Color.RED);
		p.setStrokeWidth(4f);
		p.setStyle(Style.STROKE);
		canvas.drawRect(rect, p);
		sh.unlockCanvasAndPost(canvas);

	}
}
