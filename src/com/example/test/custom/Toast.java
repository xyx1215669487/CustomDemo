package com.example.test.custom;

import com.example.test.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Toast {
	private Context mContext;
	private WindowManager wm;
	private int mDuration;
	private View mNextView;
	public static final int LENGTH_SHORT = 1500;
	public static final int LENGTH_LONG = 3000;
	private static CustomToastImage mSaveSuccess;

	public Toast(Context context) {
		mContext = context.getApplicationContext();
		wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
	}

	public static Toast makeText(Context context, CharSequence text,
			int duration) {
		Toast result = new Toast(context);
		View view = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).getView();
		if (view != null){
			TextView tv = (TextView) view.findViewById(android.R.id.message);
			tv.setText(text);
		}
		result.mNextView = view;
		result.mDuration = duration;
		return result;
	}

	/**
	 * 显示成功或者失败
	 * @param context
	 * @param text
	 * @param duration
	 * @return
	 */
	public static Toast makeImage(Context context, CharSequence text,
			int duration,boolean b) {
		Toast result = new Toast(context);
		View view=LayoutInflater.from(context).inflate(R.layout.save_success, null);
		mSaveSuccess=(CustomToastImage) view.findViewById(R.id.custom_toast);
		mSaveSuccess.isSuccess(b);
		TextView tv = (TextView) view.findViewById(R.id.text);
		tv.setText(text);
		result.mNextView = view;
		result.mDuration = duration;
		return result;
	}

	public static Toast makeText(Context context, int resId, int duration)
			throws Resources.NotFoundException {
		return makeText(context, context.getResources().getText(resId),duration);
	}

	public static Toast makeImage(Context context, int resId, int duration,boolean b)
			throws Resources.NotFoundException {
		return makeImage(context, context.getResources().getText(resId),duration,b);
	}

	public void show() {
		if (mNextView != null) {
			WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
					| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
			params.format = PixelFormat.TRANSLUCENT;
			params.windowAnimations = android.R.style.Animation_Toast;
			params.y = dip2px(mContext, 64);
			params.type = WindowManager.LayoutParams.TYPE_TOAST;
			wm.addView(mNextView, params);
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					if (mNextView != null) {
						wm.removeView(mNextView);
						mNextView = null;
						wm = null;
					}
				}
			}, mDuration);
		}
	}
	private int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
