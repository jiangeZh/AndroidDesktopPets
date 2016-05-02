package com.luoxl.androidDesktopPets;

/*
 * 宠物动画工厂
 */
public class SingleAnimaion {

	static public Animation [][] animationSet = new Animation[][]{
		/*
		 * cat
		 */
		{
			new Animation(R.drawable.cat_stay,2000),
			new Animation(R.drawable.cat_afraid,4000),
			new Animation(R.drawable.cat_angry,3000),
			new Animation(R.drawable.cat_anti,3000),
			new Animation(R.drawable.cat_christmas1,4000),
			new Animation(R.drawable.cat_clean,9000),
			new Animation(R.drawable.cat_cold,4000),
			new Animation(R.drawable.cat_cry,4000),
			new Animation(R.drawable.cat_die,3000),
			new Animation(R.drawable.cat_drink,5000),
			new Animation(R.drawable.cat_explore,3000),
			new Animation(R.drawable.cat_feed,3000),
			new Animation(R.drawable.cat_guilian,3500),
			new Animation(R.drawable.cat_haixiu,3000),
			new Animation(R.drawable.cat_heng,3500),
			new Animation(R.drawable.cat_ill,5500),
			new Animation(R.drawable.cat_lazy,2500),
			new Animation(R.drawable.cat_learn,4000),
			new Animation(R.drawable.cat_lovely,4000),
			new Animation(R.drawable.cat_move,3000),
			new Animation(R.drawable.cat_poor,2000),
			new Animation(R.drawable.cat_shy,2000),
			new Animation(R.drawable.cat_sleep,4500),
			new Animation(R.drawable.cat_sleepy,3000),
			new Animation(R.drawable.cat_stun,3500),
			new Animation(R.drawable.cat_unhappy,2000),
			new Animation(R.drawable.cat_walk,9500),
			new Animation(R.drawable.cat_work,9000)	
		},
		
		/*
		 * pika
		 */
		{
			new Animation(R.drawable.pika_stay,2000),
			//new Animation(R.drawable.pika_walk,5500),
		},
	
		/*
		 *sponse 
		 */
		{
			new Animation(R.drawable.sponge_stay,2000),
			//new Animation(R.drawable.sponge_walk,6000),
		},
		
		/*
		 * dog
		 */
		{
			new Animation(R.drawable.dog_stay,2000),
			new Animation(R.drawable.dog_bath,8500),
			new Animation(R.drawable.dog_feed,2000),
			new Animation(R.drawable.dog_lonely,3000),
			new Animation(R.drawable.dog_sleep,1500),
		}
	};
}
