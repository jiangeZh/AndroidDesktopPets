package com.luoxl.androidDesktopPets;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FloatWindowToolbarView extends LinearLayout {

	/**
	 * 记录大悬浮窗的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录大悬浮窗的高度
	 */
	public static int viewHeight;

	public FloatWindowToolbarView(final Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
		View view = findViewById(R.id.big_window_layout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		Button home = (Button) findViewById(R.id.home);
		Button clock = (Button) findViewById(R.id.clock);
		Button bluetooth = (Button) findViewById(R.id.bluetooth);
		Button set = (Button) findViewById(R.id.set);
		Button next = (Button) findViewById(R.id.next);
		Button exit = (Button) findViewById(R.id.exit);
/*		close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service
				MyWindowManager.removeBigWindow(context);
//				MyWindowManager.removeSmallWindow(context);
//				Intent intent = new Intent(getContext(), FloatWindowService.class);
//				context.stopService(intent);
			}
		});*/
		
		home.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击时，显示宠物属性
				Intent petInfo = new Intent(context, ShowPetInfoActivity.class);
				petInfo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(petInfo);
			}
		});
		
		clock.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击闹钟时，开启系统闹钟
				Intent alarmas = new Intent(AlarmClock.ACTION_SET_ALARM);
				alarmas.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(alarmas);  
			}
		});
		
		bluetooth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent bluetooth = new Intent(context,ModeActivity.class);
				bluetooth.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		        context.startActivity(bluetooth);
			}
		});
		
		set.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击设置时，跳转至设置窗口
				Intent settings = new Intent(context, Setting.class);
				settings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(settings);
			}
		});
		
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击next时，切换宠物模型
				MyWindowManager.changePetModel(context);
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 点击返回的时候，移除大悬浮窗，创建小悬浮窗
				MyWindowManager.removeBigWindow(context);
				MyWindowManager.createSmallWindow(context);
			}
		});
	}
}
