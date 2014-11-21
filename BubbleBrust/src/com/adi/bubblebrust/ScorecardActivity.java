package com.adi.bubblebrust;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScorecardActivity extends Activity {
	
//	/** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecard);

        Button next = (Button) findViewById(R.id.button1);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                
                finish();
            }

        });
    }
}
