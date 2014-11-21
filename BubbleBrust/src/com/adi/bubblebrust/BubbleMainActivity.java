package com.adi.bubblebrust;

import java.util.Random;

import android.R.bool;
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
import android.view.animation.TranslateAnimation;
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
	private TextView tLives;
	private int s32Score = 0;
	private ImageView imgview = null;
	private ImageView imgview_cloud_anim = null;
	private ImageView imgview_cloud_anim_1 = null;
	private int bubble_start_x = 0;
	private int bubble_start_y = 0;
	private short S32Lives = 5;
	private boolean clicked = false;

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

	public void exitApp(View v)
	{
		finish();
	}
	public void score()
	{
		s32Score++;
		tScore.setText(Integer.toString(s32Score));
	}

	public void lives() {

		if(S32Lives <= 0)
		{
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubble_main);

		//get display properties
		DisplayMetrics dismet = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dismet);	

		/** Cloud drawing  **/
		imgview = (ImageView)findViewById(R.id.imageView1);
		final ImageView basketview = (ImageView)findViewById(R.id.imageView_cloud_1);


		/** App Statistic related **/
		tScore = (TextView)findViewById(R.id.textView1);
		tLives = (TextView)findViewById(R.id.textView2);
		tScore.setText(Integer.toString(s32Score));
		tLives.setText(Integer.toString(S32Lives));



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

		// animate in beginning 
		animateRandom();


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
				Log.i("Bubble_Brust", "Inside --> onAnimationEnd()");
				//animset.end();
				if(!clicked)
				{
					S32Lives -= 1;
					tLives.setText(Integer.toString(S32Lives));
					lives();
				}

				clicked = false;
				animateRandom();

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
				// TODO Avoid multiple clicks
				Log.i("Bubble_Brust", "Width"+ width+"height"+ height);
				mp.start();//start play sound
				imgview.setImageResource(R.drawable.blast);
				//animset.setDuration(10);

				imgview.setX(bubble_start_x);
				imgview.setY(bubble_start_y);
				score();
				clicked = true;

			}
		});

	}

	@Override
	public void onStop()
	{
		super.onStop();
		Log.i("Bubble_Brust", "Inside OnStop");
		animset.cancel();
		mp.stop();

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
