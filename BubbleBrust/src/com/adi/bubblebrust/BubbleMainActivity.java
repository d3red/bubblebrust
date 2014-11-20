package com.adi.bubblebrust;

import java.util.Random;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


//TODO : animate clouds separately
public class BubbleMainActivity extends Activity {

	private ObjectAnimator animation1;
	private ObjectAnimator animation2;
	private ObjectAnimator animation3;
	private ObjectAnimator animation4;
	private Button button;
	private Random randon;
	private int width;
	private int height;
	private AnimatorSet animset;
	private AnimatorSet animset_clouds;
	private MediaPlayer mp;
	private TextView tScore;
	private int s32Score;
	private ImageView imgview = null;
	private ImageView imgview_cloud_anim = null;
	private ImageView imgview_cloud_anim_1 = null;
	private int bubble_start_x = 0;
	private int bubble_start_y = 0;

	public void animateRandom()
	{
		int nextX = randon.nextInt(width);
		int nextY = (int) (randon.nextInt((int) (height - (height*0.5f))) + height*0.5f) ;
		animation1 = ObjectAnimator.ofFloat(imgview, "x", nextX);
		animation1.setDuration(2500);		
		animation2 = ObjectAnimator.ofFloat(imgview, "y", nextY);
		animation2.setDuration(2500);

		animset.playTogether(animation1,animation2);
		
		animset.start();
		
	}
	
//	public void animateclouds()
//	{
//		int nextX = randon.nextInt(width);
//		//int nextY = (int) (randon.nextInt((int) (height - (height*0.5f))) + height*0.5f) ;
//		animation3 = ObjectAnimator.ofFloat(imgview_cloud_anim, "x", 720);
//		animation3.setDuration(12500);		
//		animation4 = ObjectAnimator.ofFloat(imgview_cloud_anim_1, "x", 720);
//		animation4.setDuration(12700);
//
//		animset_clouds.playTogether(animation3,animation4);
//		
//		animset_clouds.start();
//	}

	public void score()
	{
		s32Score++;
		tScore.setText(Integer.toString(s32Score));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubble_main);
		imgview = (ImageView)findViewById(R.id.imageView1);
		final ImageView basketview = (ImageView)findViewById(R.id.imageView_cloud_1);
//		imgview_cloud_anim = (ImageView)findViewById(R.id.imageView_cloud_anim);
//		imgview_cloud_anim_1 = (ImageView)findViewById(R.id.imageView_cloud_anim_1);
//		imgview_cloud_anim.setX(0.0f);
//		imgview_cloud_anim_1.setX(imgview_cloud_anim.getWidth());

		s32Score = 0;
		tScore = (TextView)findViewById(R.id.textView1);
		//tScore.set
		//get display properties
		DisplayMetrics dismet = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dismet);

		width = dismet.widthPixels;
		height = dismet.heightPixels;
		Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
		//set Origin of bubble sprites
		 bubble_start_x =  (width/2) - basketview.getWidth();
		 bubble_start_y =  basketview.getHeight()/2;
		 
		imgview.setX(bubble_start_x);
		imgview.setY(bubble_start_y);

		//Generate Random
		randon = new Random();

		//Add sound
		mp = MediaPlayer.create(this, R.raw.button_7);
		animset = new AnimatorSet();
		animset_clouds = new AnimatorSet();
		
		// animate in beginning 
		animateRandom();
		
		//animateclouds();
		
		//for cloud animations
		animset_clouds.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
		// add Listener to animationSet and implement animation states
		animset.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

				
				int colortype = randon.nextInt(5);
				switch(colortype)
				{
				case 1: 
					imgview.setImageResource(R.drawable.solo_bubble_green);
					break;
				case 2:
					imgview.setImageResource(R.drawable.solo_bubble_yellow);
					break;
				case 3:
					imgview.setImageResource(R.drawable.solo_bubble_red);
					break;
				case 4:
					imgview.setImageResource(R.drawable.solo_bubble_orange);
					break;
				case 5:
					imgview.setImageResource(R.drawable.solo_bubble_md);
					break;
				default:
					imgview.setImageResource(R.drawable.solo_bubble_blue);
					break;

				}

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				//Log.i("Bubble_Brust", "Inside --> onAnimationEnd()");
				//animset.end();				
				animateRandom();
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				//imgview.animate().start();
				Log.i("Bubble_Brust", "Inside Cancel Animation");
				//animset.setStartDelay(10);
				animateRandom();

			}
		});

		//Adding ClickListner
		imgview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Avoid multiple clicks
				Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
				mp.start();//start play sound
				imgview.setImageResource(R.drawable.blast);
				//animset.setDuration(10);
				imgview.setX(bubble_start_x);
				imgview.setY(bubble_start_y);
				animset.cancel();
				

				score();

			}
		});

	}
	
	
	public void cancelspriteAnimation()
	{
		//animset.start();
	}

	@Override
	public void onStop()
	{
		super.onStop();
		Log.i("Bubble_Brust", "Inside OnStop");
		animset.cancel();
		animset_clouds.cancel();
		mp.stop();

	}	

	@Override
	public void onResume()
	{
		super.onResume();
		Log.i("Bubble_Brust", "Inside OnResume");
		animset.start();
		animset_clouds.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bubble_main, menu);
		return true;
	}

}
