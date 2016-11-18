package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LodingProgressActivity extends Activity {
	 SuperLoadingProgress mSuperLoadingProgress;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.loding_activity);
	        mSuperLoadingProgress = (SuperLoadingProgress) findViewById(R.id.pro);
	        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {

	                new Thread(){
	                    @Override
	                    public void run() {
	                        try {
	                            mSuperLoadingProgress.setProgress(0);
	                            while(mSuperLoadingProgress.getProgress()<100) {
	                                Thread.sleep(10);
	                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
	                            }
	                            mSuperLoadingProgress.finishFail();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }.start();
	            }
	        });

	        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {

	                new Thread(){
	                    @Override
	                    public void run() {
	                        try {
	                            mSuperLoadingProgress.setProgress(0);
	                            while(mSuperLoadingProgress.getProgress()<100) {
	                                Thread.sleep(10);
	                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
	                            }
	                            mSuperLoadingProgress.finishSuccess();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }.start();
	            }
	        });
	    }
}
