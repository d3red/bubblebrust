package com.adi.bubblebrust;

import java.util.Random;

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



public class BubbleMainActivity extends Activity {

	private ObjectAnimator animation1;
	private ObjectAnimator animation2;
	private Button button;
	private Random randon;
	private int width;
	private int height;
	private AnimatorSet animset;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubble_main);
		final ImageView imgview = (ImageView)findViewById(R.id.imageView1);
		
		//get display properties
		DisplayMetrics dismet = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dismet);
		
		width = dismet.widthPixels;
		height = dismet.heightPixels;
		Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
		
		//Generate Random
		randon = new Random();
		
		//imgview.animate().translationX(100.0f);
		
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
				
			}
		});
		
		
		
		
		//Adding ClickListner
		imgview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//imgview.setImageResource(R.drawable.ic_launcher);
				Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
				imgview.setImageResource(R.drawable.orange_bubble);
				imgview.setImageDrawable(null);

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
