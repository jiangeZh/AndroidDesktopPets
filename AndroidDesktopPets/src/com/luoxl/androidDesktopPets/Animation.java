package com.luoxl.androidDesktopPets;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.ImageView;

public class Animation {
	Timer timer = new Timer();			//����ÿ�������Ķ�ʱ��������ֱ�ӵ���stop()����
	public int mills;
	public int source;
	ImageView holder;
	Animation(int source, int mills){
		this.mills = mills;
		this.source = source;
		
	}
	public void play(){
		if(FloatWindowSmallView.flag){
		if(!((AnimationDrawable) FloatWindowSmallView.petView.getBackground()).isRunning()){		
			FloatWindowSmallView.petView.setBackgroundResource(source);	
			((AnimationDrawable) FloatWindowSmallView.petView.getBackground()).start();
			stopTimer();
		}
		}
	}
	public void stopTimer(){
		// TODO Auto-generated method stub
		timer.schedule(new TimerTask(){
		@Override
			public void run() {
			// TODO Auto-generated method stub
			if(FloatWindowSmallView.flag)
			((AnimationDrawable) FloatWindowSmallView.petView.getBackground()).stop();
				//Log.d(MainActivity.ani.toString(),"now stopTimer executed");
				//Log.d(String.valueOf(MainActivity.img.getX()),String.valueOf(MainActivity.img.getY()));
			}
		}, mills);
	}
}
