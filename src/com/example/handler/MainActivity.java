package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;
	Button mButton;
	public static final int UPDATE_DATA=0; //更新数据
	public static final int UPDATE_COMPLETED=1; //完成数据更新
    Handler myHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			//super.handleMessage(msg);
			switch(msg.what) //判断消息类型
			{
			case UPDATE_DATA:
				tv.setText("数据正在更新"+msg.arg1+"%...");
				break;
			case UPDATE_COMPLETED:
				tv.setText("数据更新完成");
				break;
			}
		}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mButton=(Button)findViewById(R.id.button1);
		tv=(TextView)findViewById(R.id.text1);
		mButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				new Thread(){
					public void run(){
						for(int i=0;i<100;i++){
							try{
								Thread.sleep(100);
								
							}catch(Exception e)
							{
								e.printStackTrace();
							}
							Message m=myHandler.obtainMessage();
							m.what=UPDATE_DATA;
							m.arg1=i+1;
							myHandler.sendMessage(m);
						}
						myHandler.sendEmptyMessage(UPDATE_COMPLETED);
					}
					
				}.start();
			}
			
		});
		
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
