package com.example.androiddowncount;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static String TIME_CHANGER_ACTION = "com.zyg.demo.service.dynamicui.action.TIME_CHANGED_ACTION";
	
	public TextView time= null;
	private Intent timeService = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //初始化控件
        initView();
        //开启服务
        startTimeService();
        //注册广播
        registerTimeReceiver();
    }
    

	private void registerTimeReceiver() {
		IntentFilter filter = new IntentFilter(TIME_CHANGER_ACTION);
		registerReceiver(timeReceiver,filter);
		
	}


	private void startTimeService() {
		timeService = new Intent(this,TimeService.class);
		startService(timeService);
	}

	private void initView() {
		time = (TextView) findViewById(R.id.time);
		time.setTextColor(Color.RED);
	}
	
	BroadcastReceiver timeReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(TIME_CHANGER_ACTION.equals(action)){
				Bundle bundle = intent.getExtras();
				String strtime = bundle.getString("time");
				time.setText(strtime);
			}
		}
	};
	
	


    
}
