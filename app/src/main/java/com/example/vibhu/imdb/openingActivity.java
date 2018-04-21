package com.example.vibhu.imdb;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class openingActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        textView=findViewById(R.id.vibhu);
        imageView=findViewById(R.id.logo);
        imageView.startAnimation(fadeInAnimation);
        textView.startAnimation(fadeInAnimation);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(openingActivity.this, StartActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
