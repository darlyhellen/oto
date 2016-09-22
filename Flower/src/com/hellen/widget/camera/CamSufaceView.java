/**上午10:04:31
 * @author zhangyh2
 * CamSufaceView.java
 * TODO
 */
package com.hellen.widget.camera;

import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hellen.base.ConsMVP;
import com.hellen.common.LogApp;

/**
 * @author zhangyh2 CamSufaceView 上午10:04:31 TODO
 *         使用SurfaceView进行相机预览功能展示。并提供对外接口。方便调用。
 */
public class CamSufaceView extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	private SurfaceHolder holder;
	private Camera camera;

	private PreViewFrame frame;

	public CamSufaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initSurface(context);
	}

	public CamSufaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initSurface(context);
	}

	public CamSufaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initSurface(context);
	}

	@SuppressWarnings("deprecation")
	private void initSurface(Context context) {
		// TODO Auto-generated method stub
		holder = this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (null != camera) {
			camera.setPreviewCallback(null); // ！！这个必须在前，不然退出出错
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (OpenBackCamera() != -1) {
			camera = Camera.open(OpenBackCamera());
		} else {
			camera = Camera.open(OpenFrontCamera());
		}
		try {
			camera.setPreviewDisplay(holder);
			setCameraParamers();
		} catch (Exception e) {
			if (null != camera) {
				camera.release();
				camera = null;
			}
			LogApp.i(e.getMessage());
		}

	}

	@SuppressWarnings("deprecation")
	private void setCameraParamers() {
		// TODO Auto-generated method stub
		Camera.Parameters parameters = camera.getParameters();
		// // 设置
		parameters.setPreviewSize(200, 200);
		parameters.setFocusMode(Parameters.FOCUS_MODE_AUTO);
		/* 每秒从摄像头捕获5帧画面， */
		parameters.setPreviewFrameRate(2);
		/* 设置照片的输出格式:jpg */
		parameters.setPictureFormat(PixelFormat.JPEG);
		// 设定相片格式为JPEG，默认为NV21
		parameters.setPreviewFormat(PixelFormat.JPEG);
		/* 照片质量 */
		parameters.set("jpeg-quality", 85);

		List<Size> size = parameters.getSupportedPictureSizes();

		for (Size si : size) {
			if (ConsMVP.WIDTH.getLen() == si.width) {
				parameters.setPictureSize(si.width, si.height);
			}
		}
		// 横竖屏镜头自动调整
		if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
			parameters.set("orientation", "portrait"); //
			parameters.set("rotation", 90); // 镜头角度转90度（默认摄像头是横拍）
			camera.setDisplayOrientation(90); // 在2.2以上可以使用
		} else {
			parameters.set("orientation", "landscape"); //
			camera.setDisplayOrientation(0); // 在2.2以上可以使用
		}
		camera.startPreview();
		camera.setPreviewCallback(new Camera.PreviewCallback() {

			@Override
			public void onPreviewFrame(byte[] data, Camera camera) {
				// TODO Auto-generated method stub
				// 在视频聊天中，这里传送本地frame数据给remote端
				if (frame != null) {
					frame.onPreview(data, camera);
				}
			}

		});
	}

	public void setFrame(PreViewFrame frame) {
		this.frame = frame;
	}

	public interface PreViewFrame {

		void onPreview(byte[] data, Camera camera);

	}

	public int OpenFrontCamera() {
		int cameraCount = 0;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras(); // get cameras number

		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				// 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
				return camIdx;
			}
		}
		return -1;
	}

	public int OpenBackCamera() {
		int cameraCount = 0;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras(); // get cameras number

		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo); // get camerainfo
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				// 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
				return camIdx;
			}
		}
		return -1;
	}
}
