package com.example.yixin.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * @category Bitmap工具类 用于处理图像
 * @author 王鹏
 *
 */
public class BitmapUtil {

	public BitmapUtil() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 获取Bitmap的缩略图
	 */
	public static Bitmap getBitmapThumbnail(String path, int width, int height) {
		Bitmap bitmap = null;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		opts.inSampleSize = Math.max((int) (opts.outHeight / (float) height),
				(int) (opts.outWidth / (float) width));
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path, opts);
		return bitmap;
	}
	/*
	 * 获取Bitmap的缩略图
	 */
	public static Bitmap getBitmapThumbnail(Bitmap bmp, int width, int height) {
		Bitmap bitmap = null;
		if (bmp != null) {
			int bmpWidth = bmp.getWidth();
			int bmpHeight = bmp.getHeight();
			if (width != 0 && height != 0) {
				Matrix matrix = new Matrix();
				float scaleWidth = ((float) width / bmpWidth);
				float scaleHeight = ((float) height / bmpHeight);
				matrix.postScale(scaleWidth, scaleHeight);
				bitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
						matrix, true);
			} else {
				bitmap = bmp;
			}
		}
		return bitmap;
	}

	
}
