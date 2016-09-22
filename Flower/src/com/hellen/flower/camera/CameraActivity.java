/**上午10:08:31
 * @author zhangyh2
 * CameraActivity.java
 * TODO
 */
package com.hellen.flower.camera;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.hellen.base.BaseActivity;
import com.hellen.base.ConsMVP;
import com.hellen.common.BitmapUtils;
import com.hellen.common.LogApp;
import com.hellen.flower.R;
import com.hellen.widget.camera.CamSufaceView;
import com.hellen.widget.camera.CamSufaceView.PreViewFrame;
import com.hellen.widget.camera.LineSufaceView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 CameraActivity 上午10:08:31 TODO
 */
@ContentView(R.layout.activity_camera)
public class CameraActivity extends BaseActivity implements OnClickListener {

	@ViewInject(R.id.camera_cam)
	private CamSufaceView cam;
	@ViewInject(R.id.camera_line)
	private LineSufaceView line;
	@ViewInject(R.id.camera_btn)
	private Button btn;
	@ViewInject(R.id.camera_iv)
	private ImageView iv;

	private static Bitmap bitmap;

	@Override
	protected void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		cam.setFrame(new PreViewFrame() {

			@Override
			public void onPreview(byte[] data, Camera camera) {
				// TODO Auto-generated method stub

				Camera.Parameters parameters = camera.getParameters();
				int imageFormat = parameters.getPreviewFormat();
				int w = parameters.getPreviewSize().width;
				int h = parameters.getPreviewSize().height;
				Rect rect = new Rect(0, 0, w, h);
				YuvImage yuvImg = new YuvImage(data, imageFormat, w, h, null);
				try {
					ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
					yuvImg.compressToJpeg(rect, 100, outputstream);
					bitmap = BitmapFactory.decodeByteArray(
							outputstream.toByteArray(), 0, outputstream.size());

					camera.startPreview();
					String arg = BitmapUtils.getHash(bitmap);
					LogApp.i(arg + "");
				} catch (Exception e) {

				}
			}
		});
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.camera_btn:
			if (bitmap != null) {
				Bitmap b = rotateBitmapByDegree(bitmap, 90);
				Bitmap p = ImageCrop(b, line);
				iv.setImageBitmap(p);
			}
			break;

		default:
			break;
		}
	}

	public static Bitmap ImageCrop(Bitmap bitmap, LineSufaceView line) {
		int w = bitmap.getWidth();
		int rex = line.rect.left * w / ConsMVP.WIDTH.getLen();
		int rey = line.rect.top * w / ConsMVP.WIDTH.getLen();
		int desw = (line.rect.right - line.rect.left) * w
				/ ConsMVP.WIDTH.getLen();
		int desh = (line.rect.bottom - line.rect.top) * w
				/ ConsMVP.WIDTH.getLen();
		LogApp.i(w + "rex=" + rex + "rey=" + rey + "desw=" + desw + "desh="
				+ desh);
		// 下面这句是关键
		return Bitmap.createBitmap(bitmap, rex, rey, desw, desh, null, false);
	}

	/**
	 * 将图片按照某个角度进行旋转
	 * 
	 * @param bm
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
		Bitmap returnBm = null;

		// 根据旋转角度，生成旋转矩阵
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		try {
			// 将原始图片按照旋转矩阵进行旋转，并得到新的图片
			returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
					bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError e) {
		}
		if (returnBm == null) {
			returnBm = bm;
		}
		if (bm != returnBm) {
			bm.recycle();
		}
		return returnBm;
	}
}
