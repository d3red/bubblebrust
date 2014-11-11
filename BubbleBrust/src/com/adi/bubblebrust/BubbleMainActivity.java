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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class BubbleMainActivity extends Activity {

	private ObjectAnimator animation1;
	private ObjectAnimator animation2;
	private Button button;
	private Random randon;
	private int width;
	private int height;
	private AnimatorSet animset;
	private MediaPlayer mp;
	private TextView tScore;
	private int s32Score;
	
	
	
	public void score()
	{
		s32Score++;
		tScore.setText(Integer.toString(s32Score));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubble_main);
		final ImageView imgview = (ImageView)findViewById(R.id.imageView1);
		final ImageView basketview = (ImageView)findViewById(R.id.imageView2);
		s32Score = 0;
		tScore = (TextView)findViewById(R.id.textView1);
		
		

		//get display properties
		DisplayMetrics dismet = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dismet);

		width = dismet.widthPixels;
		height = dismet.heightPixels;
		Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
		imgview.setX(width/2);
		
		//Generate Random
		randon = new Random();

		//Add sound
		mp = MediaPlayer.create(this, R.raw.button_7);
		

		

		animset = new AnimatorSet();
		animset.start();

		int nextX = randon.nextInt(width);
		int nextY = randon.nextInt(height);
		animation1 = ObjectAnimator.ofFloat(imgview, "x", nextX);
		animation1.setDuration(1400);
		animation2 = ObjectAnimator.ofFloat(imgview, "y", nextY);
		animation2.setDuration(1400);	    

		animset.playTogether(animation1,animation2);
		animset.start();

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
						imgview.getDrawable().setColorFilter(Color.DKGRAY,PorterDuff.Mode.MULTIPLY);
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

				int nextX = randon.nextInt(width);
				int nextY = randon.nextInt(height);
				
				
				animation1 = ObjectAnimator.ofFloat(imgview, "x", nextX);
				animation1.setDuration(1400);
				animation2 = ObjectAnimator.ofFloat(imgview, "y", nextY);
				animation2.setDuration(1400);	    
				
				animset.playTogether(animation1,animation2);
				animset.start();

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				imgview.animate().start();

			}
		});




		//Adding ClickListner
		imgview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//imgview.setImageResource(R.drawable.ic_launcher);
				Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
				mp.start();//start play sound
				imgview.setImageResource(R.drawable.blast);
				score();
				
				
				
				
				
				


			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bubble_main, menu);
		return true;
	}

}
