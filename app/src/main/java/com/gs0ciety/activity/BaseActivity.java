package com.gs0ciety.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class BaseActivity extends AppCompatActivity {

    private ImageView panelIndicator;
    private ImageView questionIndicator;
    private ImageView soundIndicator;
    private ImageView nameIndicator;

    private ImageView panelButton;
    private ImageView questionButton;
    private ImageView soundButton;
    private ImageView nameButton;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        final DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        final FrameLayout activityContainer = fullView.findViewById(R.id.frame_base_activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        panelIndicator = findViewById(R.id.img_indicator_animal_panel);
        questionIndicator = findViewById(R.id.img_indicator_animal_question);
        soundIndicator = findViewById(R.id.img_indicator_animal_sound);
        nameIndicator = findViewById(R.id.img_indicator_animal_name);

        panelButton = findViewById(R.id.btn_animal_panel);
        questionButton = findViewById(R.id.btn_animal_question);
        soundButton = findViewById(R.id.btn_animal_sound);
        nameButton = findViewById(R.id.btn_animal_name);
    }

    public void onClickPanelButton(final View view) {
        panelIndicator.setAlpha(1f);
        panelButton.setAlpha(1f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
    }

    public void onClickSoundButton(final View view) {
        soundIndicator.setAlpha(1f);
        soundButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
    }

    public void onClickQuestionButton(final View view) {
        questionIndicator.setAlpha(1f);
        questionButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
        nameIndicator.setAlpha(0f);
        nameButton.setAlpha(0.7f);
    }

    public void onClickNameButton(final View view) {
        nameIndicator.setAlpha(1f);
        nameButton.setAlpha(1f);
        panelIndicator.setAlpha(0f);
        panelButton.setAlpha(0.7f);
        questionIndicator.setAlpha(0f);
        questionButton.setAlpha(0.7f);
        soundIndicator.setAlpha(0f);
        soundButton.setAlpha(0.7f);
    }
}