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
	private MediaPlayer mp;
	private TextView tScore;
	private int s32Score;
	private ImageView imgview = null;
	private ImageView imgview_cloud_anim = null;
	private ImageView imgview_cloud_anim_1 = null;

	public void animateRandom()
	{
		int nextX = randon.nextInt(width);
		int nextY = (int) (randon.nextInt((int) (height - (height*0.5f))) + height*0.5f) ;
		animation1 = ObjectAnimator.ofFloat(imgview, "x", nextX);
		animation1.setDuration(2500);		
		animation2 = ObjectAnimator.ofFloat(imgview, "y", nextY);
		animation2.setDuration(2500);
		animation3 = ObjectAnimator.ofFloat(imgview_cloud_anim, "x", nextX);
		animation3.setDuration(12500);		
		animation4 = ObjectAnimator.ofFloat(imgview_cloud_anim_1, "x", nextY);
		animation4.setDuration(14500);
		

		animset.playTogether(animation1,animation2,animation3,animation4);
		
		animset.start();
	}

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
		imgview_cloud_anim = (ImageView)findViewById(R.id.imageView_cloud_anim);
		imgview_cloud_anim_1 = (ImageView)findViewById(R.id.imageView_cloud_anim_1);

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
		imgview.setX((width/2) - basketview.getWidth());
		imgview.setY(basketview.getHeight()/2);

		//Generate Random
		randon = new Random();

		//Add sound
		mp = MediaPlayer.create(this, R.raw.button_7);
		animset = new AnimatorSet();
		
		// animate in beginning 
		animateRandom();
		// add Listener to animationSet and implement animation states
		animset.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

				imgview.setImageResource(R.drawable.solo_bubble_md);
				int colortype = randon.nextInt(5);
				switch(colortype)
				{
				case 1: 
					imgview.getDrawable().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
					break;
				case 2:
					imgview.getDrawable().setColorFilter(Color.BLUE,PorterDuff.Mode.MULTIPLY);
					break;
				case 3:
					imgview.getDrawable().setColorFilter(Color.GREEN,PorterDuff.Mode.MULTIPLY);
					break;
				case 4:
					imgview.getDrawable().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
					break;
				case 5:
					imgview.getDrawable().setColorFilter(Color.CYAN,PorterDuff.Mode.MULTIPLY);
					break;
				default:
					imgview.getDrawable().setColorFilter(Color.MAGENTA,PorterDuff.Mode.MULTIPLY);
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
				//Log.i("Bubble_Brust", "Inside Cancel Animation");

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
				score();

			}
		});

	}

	@Override
	public void onStop()
	{
		super.onStop();
		Log.i("Bubble_Brust", "Inside OnStop");
		animset.cancel();
		//mp.stop();

	}	

	@Override
	public void onResume()
	{
		super.onResume();
		Log.i("Bubble_Brust", "Inside OnResume");
		animset.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bubble_main, menu);
		return true;
	}

}
