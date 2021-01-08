package com.lxa.demo.mydemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import com.lxa.demo.mydemo.R;


public class ToolUtil {
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static Bitmap getPropThumnail(int id, Context context) {
		Drawable d = context.getResources().getDrawable(id);
		Bitmap b = BitmapUtil.drawableToBitmap(d);
		// Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
		int w = context.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
		int h = context.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);

		Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);

		return thumBitmap;
	}
}
