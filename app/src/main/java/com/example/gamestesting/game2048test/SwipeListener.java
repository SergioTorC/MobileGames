package com.example.gamestesting.game2048test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.gamestesting.R;

public class SwipeListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    private final SwipeDirection swipeDirection;

    private final MediaPlayer slideUp;
    private final MediaPlayer slideDown;
    private final MediaPlayer slideLeft;
    private final MediaPlayer slideRight;


    public SwipeListener(Context context, SwipeDirection swipeDirection) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.swipeDirection = swipeDirection;
        this.slideUp = MediaPlayer.create(context, R.raw.hurt1);
        this.slideDown = MediaPlayer.create(context, R.raw.hurt2);
        this.slideLeft = MediaPlayer.create(context, R.raw.say1);
        this.slideRight = MediaPlayer.create(context, R.raw.say2);
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;

        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            slideRight.start();
                            swipeDirection.onSwipeRight();
                        } else {
                            slideLeft.start();
                            swipeDirection.onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            slideDown.start();
                            swipeDirection.onSwipeDown();
                        } else {
                            slideUp.start();
                            swipeDirection.onSwipeUp();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }
}



