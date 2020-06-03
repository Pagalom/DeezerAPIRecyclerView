package com.example.deezerapirecyclerview.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deezerapirecyclerview.R;
import com.example.deezerapirecyclerview.controller.Constante;

public class SplashScreen extends Activity {

    protected void onCreate(Bundle bd) {
        super.onCreate(bd);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        }, Constante.SPLASH_TIME_OUT);
    }

}
