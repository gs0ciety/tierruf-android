package com.gs0ciety.interfaces;

import androidx.annotation.IntegerRes;

import com.airbnb.lottie.LottieAnimationView;

public interface ButtonPanelBehaviours {
    void playSound(@IntegerRes int audioResId, LottieAnimationView lottieAnimationView);
}
