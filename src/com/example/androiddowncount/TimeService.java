package com.example.androiddowncount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service {
	private static final String TAG = "TimeService";
	private Timer timer = null;
	private SimpleDateFormat sdf; 
	private Intent timeIntent = null;
	private Bundle bundle = null;
	
	public void onCreate(){
		super.onCreate();
		Log.i(TAG,"TimeService -> onCreate");
		//初始化
		init();
		//定时器发送广播 
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				//发送广播
				sendTimeChangedBroadcast();
			}
			
		}, 1000,1000);
	}
	/**
	 * 发送广播，通知UI层时间已改变
	 */
	private void sendTimeChangedBroadcast() {
		bundle.putString("time", getTime());
		timeIntent.putExtras(bundle);
		timeIntent.setAction(MainActivity.TIME_CHANGER_ACTION);
		//发送广播，通知UI层时间变了;
		sendBroadcast(timeIntent);
	}
	
	
	
	private String getTime() {
		return sdf.format(new Date());
	}
	private void init(){
		timer = new Timer();
		sdf = new SimpleDateFormat("yyyy年MM月dd日 " + " HH:mm:ss" );
		timeIntent = new Intent();
		bundle = new Bundle();
	}
	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
