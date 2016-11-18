package com.example.test.custom;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

@SuppressLint("NewApi")
public class CustomToastImage extends View{

	private int mWidth,mHeight;
	/**
	 * 画笔宽度
	 */
	private int strokeWidth = 5;
	/**
	 * 测量打钩
	 */
	private PathMeasure successPathMeasure;

	private PathMeasure failurePathMeasure1;
	private PathMeasure failurePathMeasure2;



	private float radius = 0;

	/**
	 * 打钩百分比
	 * @param context
	 */
	float tickPrecent = 0;

	/**
	 * 最大进度
	 */
	private static final int maxProgress = 100;

	/**
	 * 打钩
	 */
	private ValueAnimator mTickAnimation;

	private RectF mRectF = new RectF();
	private Paint tickPaint = new Paint();
	private int mPaintStatus;


	public CustomToastImage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public CustomToastImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public CustomToastImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}


	private void init(){
		tickPaint.setColor(Color.WHITE);
		tickPaint.setAntiAlias(true);
		tickPaint.setStrokeWidth(strokeWidth);
		tickPaint.setStyle(Paint.Style.STROKE);

		//打钩动画        
		mTickAnimation = ValueAnimator.ofFloat(0f, 1f);
		mTickAnimation.setStartDelay(1000);
		mTickAnimation.setDuration(500);
		mTickAnimation.setInterpolator(new AccelerateInterpolator());
		mTickAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				tickPrecent = (Float) animation.getAnimatedValue();
				invalidate();
			}
		});
		mTickAnimation.start();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		mWidth = w;
		mHeight = h;
		radius = Math.min(getMeasuredWidth(),getMeasuredHeight())/4-strokeWidth;
		mRectF.set(new RectF(radius+strokeWidth, radius+strokeWidth, 3 * radius+strokeWidth, 3 * radius+strokeWidth));

		radius = Math.min(getMeasuredWidth(),getMeasuredHeight())/4-strokeWidth;
		//初始化打钩路径
		Path successPath = new Path();
		successPath.moveTo(1.5f * radius+strokeWidth, 2 * radius+strokeWidth);
		successPath.lineTo(1.5f * radius + 0.3f * radius+strokeWidth, 2 * radius + 0.3f * radius+strokeWidth);
		successPath.lineTo(2*radius+0.5f * radius+strokeWidth,2*radius-0.3f * radius+strokeWidth);
		successPathMeasure = new PathMeasure(successPath,false);

		//初始化打叉路径
		Path failurePath1 = new Path();
		failurePath1.moveTo(1.6f * radius+strokeWidth, 1.5f * radius+strokeWidth);
		failurePath1.lineTo(2.4f * radius+strokeWidth, 2 * radius + 0.5f * radius+strokeWidth);
		Path failurePath2 = new Path();
		failurePath2.moveTo(2.4f * radius+strokeWidth, 1.5f * radius+strokeWidth);
		failurePath2.lineTo(2.1f*radius-0.5f * radius+strokeWidth,2*radius+0.5f * radius+strokeWidth);
		failurePathMeasure1 = new PathMeasure(failurePath1,false);
		failurePathMeasure2 = new PathMeasure(failurePath2,false);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthSpecSize + 10 * strokeWidth, heightSpecSize + 10 * strokeWidth);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		switch (mPaintStatus) {
		case 1:
			drawSuccess(canvas);
			break;

		case 2:
			drawFailure(canvas);
			break;
		}
	}

	/**
	 * 画成功
	 * @param canvas
	 */
	private void drawSuccess(Canvas canvas){
		Path path = new Path();
		successPathMeasure.getSegment(0, tickPrecent * successPathMeasure.getLength(), path, true);
		path.rLineTo(0, 0);
		canvas.drawPath(path, tickPaint);
		canvas.drawArc(mRectF, 0, 360, false, tickPaint);
	}

	/**
	 * 画失败
	 * @param canvas
	 */
	private void drawFailure(Canvas canvas){
		Path path1 = new Path();
		Path path2 = new Path();
		failurePathMeasure1.getSegment(0, tickPrecent * failurePathMeasure1.getLength(), path1, true);
		failurePathMeasure2.getSegment(0, tickPrecent * failurePathMeasure2.getLength(), path2, true);
		path1.rLineTo(0, 0);
		path2.rLineTo(0, 0);
		canvas.drawPath(path1, tickPaint);
		//画第二根线  
		canvas.drawPath(path2, tickPaint);  
		canvas.drawArc(mRectF, 0, 360, false, tickPaint);
	}


	public void isSuccess(boolean b){
		if(b){
			mPaintStatus=1;
		}else{
			mPaintStatus=2;
		}
	}


}
