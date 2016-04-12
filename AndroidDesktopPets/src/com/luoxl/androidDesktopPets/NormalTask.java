package com.luoxl.androidDesktopPets;


import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.Log;

public class NormalTask extends AsyncTask<Void, Void, Boolean> {
	int count = 0;
	int index;
	@Override
	protected Boolean doInBackground(Void... params) {
		// TODO Auto-generated method stub
		while(true){
			/*
			try {
				int sleepTime = (int) (Math.random() * 5000 + 2500);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			if(FloatWindowSmallView.flag){
			//if(!((AnimationDrawable) FloatWindowSmallView.petView.getBackground()).isRunning()){
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress();
			}
		//}
		}
	}
	protected void onProgressUpdate(Void... params){
		if(FloatWindowSmallView.flag){
			count ++;
			if(FloatWindowSmallView.getPetIndex()==0 && count >= 3 && !((AnimationDrawable) FloatWindowSmallView.petView.getBackground()).isRunning()){
				count = 0;
				//((AnimationDrawable)FloatWindowSmallView.petView.getBackground()).stop();
				index = (int)(Math.random() * 27);
				SingleAnimaion.animationSet[index].play();
			}
			//else
			//	SingleAnimaion.stay.play();
			Log.d("animation index:"+String.valueOf(index),String.valueOf(count));
		}
	}
}