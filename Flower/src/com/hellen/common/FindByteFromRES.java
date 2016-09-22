package com.hellen.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * @author zhangyh2 Utils 上午11:31:29 TODO 从资源文件中获取字节数组
 */
public class FindByteFromRES {

	public static byte[] readRawResource(Context context, int resId) {
		InputStream stream = null;
		byte[] buffer = null;
		try {
			stream = context.getResources().openRawResource(resId);
			buffer = new byte[stream.available()];
			while (stream.read(buffer) != -1)
				;
		} catch (IOException e) {
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
		return buffer;
	}

	public static byte[] readRawResource(Context context, String res) {
		InputStream stream = null;
		byte[] buffer = null;
		try {

			stream = new ByteArrayInputStream(res.getBytes("UTF-8"));
			buffer = new byte[stream.available()];
			while (stream.read(buffer) != -1)
				;
		} catch (IOException e) {
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
		return buffer;
	}
}
