package com.gs0ciety.tierruf.interfaces;

import androidx.annotation.IntegerRes;

import com.airbnb.lottie.LottieAnimationView;

public interface ButtonPanelBehaviors {
    void playSound(@IntegerRes int audioResId, LottieAnimationView lottieAnimationView);
}
