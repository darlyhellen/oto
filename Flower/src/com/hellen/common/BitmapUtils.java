package com.hellen.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class BitmapUtils {

	private static int sum = 0;

	public static String getHash(Bitmap bitmap) {
		Bitmap temp = Bitmap.createScaledBitmap(bitmap, 8, 8, false);
		int[] grayValues = reduceColor(temp);
		int average = sum / grayValues.length;
		String reslut = computeBits(grayValues, average);
		return reslut;
	}

	private static String computeBits(int[] grayValues, int average) {
		char[] result = new char[grayValues.length];
		for (int i = 0; i < grayValues.length; i++) {
			if (grayValues[i] < average)
				result[i] = '0';
			else
				result[i] = '1';
		}
		return new String(result);
	}

	private static int[] reduceColor(Bitmap bitmap) {
		sum = 0;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int[] grayValues = new int[width * height];
		int[] pix = new int[width * height];
		bitmap.getPixels(pix, 0, width, 0, 0, width, height);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int x = j * width + i;
				int r = (pix[x] >> 16) & 0xff;
				int g = (pix[x] >> 8) & 0xff;
				int b = pix[x] & 0xff;
				int grayValue = (r * 30 + g * 59 + b * 11) / 100;
				sum += grayValue;
				grayValues[x] = grayValue;
			}
		return grayValues;
	}

	public static void saveToFile(Bitmap... bitmaps) {
		int i = 0;
		for (Bitmap bitmap : bitmaps) {
			String path = Environment.getExternalStorageDirectory().getPath();
			File file = new File(path + "/" + (i++) + ".jpg");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}