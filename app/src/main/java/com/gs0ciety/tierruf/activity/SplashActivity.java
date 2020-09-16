package com.gs0ciety.tierruf.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gs0ciety.tierruf.R;

public class SplashActivity extends AppCompatActivity {

    private final static Long SCREEN_DURATION = 3000L;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        scheduleSplashScreen();
    }

    private void scheduleSplashScreen() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.elephant);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(final MediaPlayer mp) {
                mp.release();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the splash screen duration, route to the right activities
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                stopActiveSound();
            }
        }, SCREEN_DURATION);
    }

    @Override
    public void onDestroy() {
        stopActiveSound();
        super.onDestroy();
    }

    private void stopActiveSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}