/**
 * 
 * @author heqing1
 * @version 2012-10-19
 */
package com.core.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Bitmap工具类
 */
public class BitmapUtil {

	/**
	 * 获取压缩比sampleSize
	 * 
	 * @param origWidth
	 *            原始图像宽度
	 * @param origHeight
	 *            原始图像高度
	 * @param displayWidth
	 *            要达到的宽度
	 * @param displayHeight
	 *            要达到的高度
	 * @return 压缩比sampleSize
	 */
	private static int getSampleSize(int origWidth, int origHeight,
			int displayWidth, int displayHeight) {
		int inSampleSize = 1;
		// 获取比例大小，若超出指定大小，则缩小相应的比例
		int wRatio = (int) Math.ceil(origWidth / displayWidth);
		int hRatio = (int) Math.ceil(origWidth / displayHeight);
		if (wRatio > 1 && hRatio > 1) {
			inSampleSize = Math.max(wRatio, hRatio);
		}
		return inSampleSize;
	}

	/**
	 * 获取压缩后bitmap
	 * 
	 * @param imgLocalPath
	 *            本地图片路径
	 * @param displayWidth
	 * @param displayHeight
	 * @return 压缩后bitmap
	 */
	public static Bitmap getCompressedBitmap(String imgLocalPath,
			int displayWidth, int displayHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(imgLocalPath, options);
		options.inSampleSize = getSampleSize(options.outWidth,
				options.outHeight, displayWidth, displayHeight);
		options.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(imgLocalPath, options);
		return bmp;

	}

	/**
	 * 获取压缩后bitmap
	 * 
	 * @param bytes原始图像字节数组
	 * @param displayWidth
	 * @param displayHeight
	 * @return 压缩后bitmap
	 */
	public static Bitmap getCompressedBitmap(byte[] bytes, int displayWidth,
			int displayHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
				options);
		options.inSampleSize = getSampleSize(options.outWidth,
				options.outWidth, displayWidth, displayHeight);
		options.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		return bmp;

	}

	/**
	 * 将bitmap保存成文件
	 * 
	 * @param bmp
	 * @param imgFile
	 *            本地文件
	 * @return 是否成功
	 */
	public static boolean saveBitmp2File(Bitmap bmp, File imgFile) {
		FileOutputStream fOut = null;
		if (imgFile.exists()) {
			imgFile.delete();
		}
		try {
			imgFile.createNewFile();
			fOut = new FileOutputStream(imgFile);
			bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取圆角图片
	 * 
	 * @param origBmp
	 *            原始bitmap
	 * @param roundPx
	 *            圆角弧度（像素）
	 * @return 圆角的bitmap
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap origBmp, float roundPx) {
		if (origBmp == null) {
			return null;
		}
		// 创建新的图片以及画布
		Bitmap output = Bitmap.createBitmap(origBmp.getWidth(),
				origBmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, origBmp.getWidth(),
				origBmp.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(origBmp, rect, rect, paint);

		return output;
	}

	/**
	 * getAvatorBitmap:获取正圆图标. <br/>
	 * 
	 * @author hushuan
	 * @param origBmp
	 * @return
	 */
	public static Bitmap getAvatorBitmap(Bitmap origBmp) {
		if (origBmp == null) {
			return null;
		}
		// 创建新的图片以及画布
		int size = Math.min(origBmp.getWidth(), origBmp.getHeight());
		float roundPx = size / 2;
		Bitmap output = Bitmap.createBitmap(size, size, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		int left = (origBmp.getWidth() - size) / 2;
		int top = (origBmp.getHeight() - size) / 2;
		final Rect src = new Rect(left, top, left + size, top + size);
		final RectF dst = new RectF(0, 0, size, size);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(dst, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(origBmp, src, dst, paint);

		return output;
	}
}
