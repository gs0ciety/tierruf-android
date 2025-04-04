package com.gs0ciety.tierruf.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(final Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 40;
        private static final int SWIPE_VELOCITY_THRESHOLD = 40;

        @Override
        public boolean onDown(final MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(final MotionEvent e1, final MotionEvent e2, float velocityX, float velocityY) {
            if (e2 == null || e1 == null) {
                return false;
            }

            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0) {
                    onSwipeRight();
                }
                return true;
            } else {
                return false;
            }
        }
    }
    protected abstract void onSwipeRight();
}