package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@SuppressLint("ResourceAsColor")
public class CustomView extends View{
	private Paint paint;
	private int roundWidth =200;
	private int roundWidth2 =150;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		Log.e("XYX", "left-->"+left);
		Log.e("XYX", "top-->"+top);
		Log.e("XYX", "right-->"+right);
		Log.e("XYX", "bottom-->"+bottom);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	int i=10;
	int j=1;
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		/**
		 * 画最外层的大圆环
		 */
		int centre = getWidth() / 2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 4); //圆环的半径
		paint=new Paint();
		 RectF oval = new RectF(centre - radius, centre - radius, centre
	                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限
		paint.setColor(Color.GRAY);//设置圆环的颜色
		paint.setStyle(Paint.Style.STROKE); //设置空心
		paint.setStrokeWidth(20);
		paint.setAntiAlias(true);  //消除锯齿
		canvas.drawArc(oval, 0, j, false, paint);  //根据进度画圆弧
		mHandler.post(run);
	}
	
	Runnable run=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
				i=i+10;
				j=j+1;
				if(i==360){
					i=10;
				}
				if(j>=360){
					j=1;
				}
			postInvalidate();
		}
	};
	Handler mHandler=new Handler();
	

//	Handler mHandler=new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			if(msg.what==1){
//				roundWidth=roundWidth+10;
//				if(roundWidth>=600){
//					roundWidth=200;
//				}
//			}else{
//				i=i+10;
//				j=j+1;
//				if(i==360){
//					i=10;
//				}
//				if(j>=360){
//					j=1;
//				}
//			}
//			postInvalidate();
//		};
//	};

}
