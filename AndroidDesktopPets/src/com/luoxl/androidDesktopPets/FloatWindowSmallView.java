package com.luoxl.androidDesktopPets;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FloatWindowSmallView extends LinearLayout {

	/**
	 * 记录小悬浮窗的宽度
	 */
	public static int viewWidth;

	/**
	 * 记录小悬浮窗的高度
	 */
	public static int viewHeight;

	/**
	 * 记录系统状态栏的高度
	 */
	private static int statusBarHeight;

	/**
	 * 用于更新小悬浮窗的位置
	 */
	private WindowManager windowManager;

	/**
	 * 小悬浮窗的参数
	 */
	private WindowManager.LayoutParams mParams;

	/**
	 * 记录当前手指位置在屏幕上的横坐标值
	 */
	private float xInScreen;

	/**
	 * 记录当前手指位置在屏幕上的纵坐标值
	 */
	private float yInScreen;

	/**
	 * 记录手指按下时在屏幕上的横坐标的值
	 */
	private float xDownInScreen;

	/**
	 * 记录手指按下时在屏幕上的纵坐标的值
	 */
	private float yDownInScreen;

	/**
	 * 记录手指按下时在小悬浮窗的View上的横坐标的值
	 */
	private float xInView;

	/**
	 * 记录手指按下时在小悬浮窗的View上的纵坐标的值
	 */
	private float yInView;

	private float swidth;

	private View view;

	public static ImageView petView;

	public static boolean flag = true;
	
	private boolean isOpenBigWin = false;

	private int[] petStayModelID = {
			R.drawable.cat_stay,
			R.drawable.pika_stay,
			R.drawable.sponge_stay,
			R.drawable.dog_stay
	};
	
	private int[] petRunModelID = {
			R.drawable.cat_run,
			R.drawable.pika_run,
			R.drawable.sponge_run,
			R.drawable.dog_run
	};
	
	private static int petIndex = 0;
	private int petNum = 4;
	
	public FloatWindowSmallView(Context context) {
		super(context);
		windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
		view = findViewById(R.id.small_window_layout);
		viewWidth = view.getLayoutParams().width;
		viewHeight = view.getLayoutParams().height;
		petView = (ImageView) findViewById(R.id.percent);	
		petView.setBackgroundResource(R.drawable.stay);
		new NormalTask().execute();
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		swidth = outMetrics.widthPixels;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!flag) {
				TranslateAnimation tAnim = getTranslateAnimation(0, false);
				view.startAnimation(tAnim);
				flag = true;
			}
			petView.setBackgroundResource(petRunModelID[petIndex]);
			((AnimationDrawable) petView.getBackground()).start();
			//SingleAnimaion.feed.play();
			
			String is;
			if(((AnimationDrawable) petView.getBackground()).isRunning())
				is = "isRunning!";
				else is = "notRunning!";
			Log.d("1",is);
			// 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
			xInView = event.getX();
			yInView = event.getY();
			xDownInScreen = event.getRawX();
			yDownInScreen = event.getRawY() - getStatusBarHeight();
			xInScreen = event.getRawX();
			yInScreen = event.getRawY() - getStatusBarHeight();
			break;
		case MotionEvent.ACTION_MOVE:
			xInScreen = event.getRawX();
			yInScreen = event.getRawY() - getStatusBarHeight();
			// 手指移动的时候更新小悬浮窗的位置
			updateViewPosition();
			break;
		case MotionEvent.ACTION_UP:
			// 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
			if (xDownInScreen == xInScreen && yDownInScreen == yInScreen) {
				if (!isOpenBigWin) {
					openBigWindow();
					isOpenBigWin = true;
				}
				else {
					closeBigWindow();
					isOpenBigWin = false;
				}					
			} else {
				if (isOpenBigWin) {
					closeBigWindow();
					isOpenBigWin = false;
				}
				updateViewPosition3();
			}
			petView.setBackgroundResource(petStayModelID[petIndex]);
			//((AnimationDrawable) petView.getBackground()).start();
			//((AnimationDrawable) petView.getBackground()).stop();
			//SingleAnimaion.stay.play();
			
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。
	 * 
	 * @param params
	 *            小悬浮窗的参数
	 */
	public void setParams(WindowManager.LayoutParams params) {
		mParams = params;
	}

	/**
	 * 更新小悬浮窗在屏幕中的位置。
	 */
	private void updateViewPosition() {
		mParams.x = (int) (xInScreen - xInView);
		mParams.y = (int) (yInScreen - yInView);
		windowManager.updateViewLayout(this, mParams);
	}

//	private void updateViewPosition2() {
//		if (mParams.x >= swidth / 2) {
//			mParams.x = (int) swidth;
//		} else {
//			mParams.x = 0;
//		}
//		windowManager.updateViewLayout(this, mParams);
//	}

	/*
	 * 贴屏动画
	 */
	private void updateViewPosition3() {
		if (mParams.x <= 0) {
			doTranslateAnimation(-1);
			flag = false;
		} else if (mParams.x + viewWidth >= swidth) {
			doTranslateAnimation(1);
			flag = false;
		}
	}

	private TranslateAnimation getTranslateAnimation(float x,
			boolean FillAfter) {
		TranslateAnimation tAnim = new TranslateAnimation(0, x, 0, 0);// 横向位移
		tAnim.setDuration(500);
		tAnim.setFillAfter(FillAfter);
		tAnim.setInterpolator(new AccelerateDecelerateInterpolator());
		return tAnim;
	}

	public void doTranslateAnimation(final float x) {
		AnimationSet set = new AnimationSet(true);
		flag = false;
		TranslateAnimation outAnim = new TranslateAnimation(
				TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_PARENT, x,
				TranslateAnimation.RELATIVE_TO_SELF, 0,
				TranslateAnimation.RELATIVE_TO_SELF, 0);
		outAnim.setDuration(500);
		outAnim.setStartOffset(100);
		TranslateAnimation readyAnim2 = new TranslateAnimation(
				TranslateAnimation.RELATIVE_TO_SELF, 0f,
				TranslateAnimation.RELATIVE_TO_SELF, -x/2f,
				TranslateAnimation.RELATIVE_TO_SELF, 0f,
				TranslateAnimation.RELATIVE_TO_SELF, 0f);
		readyAnim2.setDuration(300);
		readyAnim2.setStartOffset(550);
		set.addAnimation(readyAnim2);
		set.setFillAfter(true);
		outAnim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			/*
			 * 设置贴屏换图
			 */
			@SuppressLint("NewApi")
			@Override
			public void onAnimationEnd(Animation animation) {
				petView.setScaleX(x);  //设置镜像
				petView.setBackground(getResources().getDrawable(R.drawable.lovely_info_win));
				
			}
		});
		set.addAnimation(outAnim);
		view.startAnimation(set);
	}

	/**
	 * 打开大悬浮窗
	 */
	private void openBigWindow() {
		MyWindowManager.createBigWindow(getContext());
	}
	
	/**
	 * 关闭大悬浮窗
	 */
	private void closeBigWindow() {
		MyWindowManager.removeBigWindow(getContext());
	}

	/**
	 * 用于获取状态栏的高度。
	 * @return 返回状态栏高度的像素值。
	 */
	private int getStatusBarHeight() {
		if (statusBarHeight == 0) {
			try {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object o = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = (Integer) field.get(o);
				statusBarHeight = getResources().getDimensionPixelSize(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusBarHeight;
	}
	
	public void changePetModel() {
		petIndex++;
		petIndex = petIndex%petNum;
		petView.setBackgroundResource(petStayModelID[petIndex]);
		((AnimationDrawable) petView.getBackground()).start();
	}
	
	public static int getPetIndex() {
		return petIndex;
	}

}
