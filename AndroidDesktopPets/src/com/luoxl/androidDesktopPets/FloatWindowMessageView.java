package com.luoxl.androidDesktopPets;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class FloatWindowMessageView extends LinearLayout{
	/**
	 * 记录消息框的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录消息框的高度
	 */
	public static int viewHeight;
	
	/**
	 * 记录事件
	 */
	public static AccessibilityEvent myEvent;
	
	public FloatWindowMessageView(final Context context,  final PendingIntent pendingIntent, final String text) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.float_window_message, this);
		View view = findViewById(R.id.message_window_layout);
		TextView textView = (TextView)view.findViewById(R.id.inform_text);
		textView.setText(text);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		Button ok = (Button) findViewById(R.id.inform_ok);
		Button cancel = (Button) findViewById(R.id.inform_cancel);
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击时，跳转到微信界面
				MyWindowManager.readMessage(context, pendingIntent);	
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击返回的时候，移除大悬浮窗，创建小悬浮窗
				MyWindowManager.removeMessageWindow(context);
			}
		});
	}
}
