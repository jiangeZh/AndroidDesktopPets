package com.luoxl.androidDesktopPets;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

/*
 * 定时器
 */

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
		if(FloatWindowPetView.flag){
		if(!((AnimationDrawable) FloatWindowPetView.petView.getBackground()).isRunning()){		
			FloatWindowPetView.petView.setBackgroundResource(source);	
			((AnimationDrawable) FloatWindowPetView.petView.getBackground()).start();
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
			if(FloatWindowPetView.flag)
			((AnimationDrawable) FloatWindowPetView.petView.getBackground()).stop();
				//Log.d(MainActivity.ani.toString(),"now stopTimer executed");
				//Log.d(String.valueOf(MainActivity.img.getX()),String.valueOf(MainActivity.img.getY()));
			}
		}, mills);
	}
}
