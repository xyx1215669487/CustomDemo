package com.example.test;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	Button mBtn_1,mBtn_2,mBtn_3,mBtn_4,mBtn_5,mBtn_6;
	Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBtn_1 = (Button) findViewById(R.id.btn_1);
		mBtn_2 = (Button) findViewById(R.id.btn_2);
		mBtn_3 = (Button) findViewById(R.id.btn_3);
		mBtn_4 = (Button) findViewById(R.id.btn_4);
		mBtn_5 = (Button) findViewById(R.id.btn_5);
		mBtn_6 = (Button) findViewById(R.id.btn_6);
		mBtn_1.setOnClickListener(this);
		mBtn_2.setOnClickListener(this);
		mBtn_3.setOnClickListener(this);
		mBtn_4.setOnClickListener(this);
		mBtn_5.setOnClickListener(this);
		mBtn_6.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_1:
			Toast.makeText(MainActivity.this, "System Toast", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_2:
			com.example.test.custom.Toast.makeText(MainActivity.this, "Custom Toast", com.example.test.custom.Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_3:
			Intent intent =new Intent(MainActivity.this,CustomActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_4:
			Intent intent2 =new Intent(MainActivity.this,LodingProgressActivity.class);
			startActivity(intent2);
			break;
		case R.id.btn_5:
			com.example.test.custom.Toast.makeImage(MainActivity.this, "保存成功", com.example.test.custom.Toast.LENGTH_SHORT, true).show();
			break;
		case R.id.btn_6:
			com.example.test.custom.Toast.makeImage(MainActivity.this, "保存失败", com.example.test.custom.Toast.LENGTH_SHORT, false).show();
			break;
		}
	}

}
