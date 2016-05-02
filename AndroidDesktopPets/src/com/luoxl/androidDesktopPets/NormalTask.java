package com.luoxl.androidDesktopPets;


import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.Log;

/*
 * 随机动画
 */
public class NormalTask extends AsyncTask<Void, Void, Boolean> {
	int count = 0;
	int animationIndex;
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
			if(FloatWindowPetView.flag){
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
		if(FloatWindowPetView.flag){
			count ++;
			int petIndex = FloatWindowPetView.getPetIndex();
			int animationSize = SingleAnimaion.animationSet[petIndex].length;
			if(count >= 3 && !((AnimationDrawable) FloatWindowPetView.petView.getBackground()).isRunning()){
				count = 0;
				//((AnimationDrawable)FloatWindowSmallView.petView.getBackground()).stop();
				animationIndex = (int)(Math.random() * animationSize);
				SingleAnimaion.animationSet[petIndex][animationIndex].play();
			}
			else
				SingleAnimaion.animationSet[petIndex][0].play();
			Log.d("animation index:"+String.valueOf(animationIndex),String.valueOf(count));
		}
	}
}