package com.malibaig.animationdemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private String LOG_TAG = SecondActivity.class.getSimpleName();
    View viewYellow, viewRed;
    int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        viewYellow = findViewById(R.id.view_yellow);
        viewRed = findViewById(R.id.view_red);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        getScreenWidthHeight();
        getViewPositionOnScreen(viewYellow, "ViewYellow");
        getViewPositionOnScreen(viewRed, "ViewRed");


    }

    private void getViewPositionOnScreen(final View view, final String viewName) {
        int[] viewPositions = new int[2];
        view.getLocationInWindow(viewPositions);

        Log.i(LOG_TAG, viewName + " Width:" + view.getWidth());
        Log.i(LOG_TAG, viewName + " Height:" + view.getHeight());

        Log.w(LOG_TAG, viewName + " X on Screen:" + viewPositions[0]);
        Log.w(LOG_TAG, viewName + " Y on Screen:" + viewPositions[1]);

        final int endPointY = screenHeight - viewPositions[1] - 260;
        Log.e(LOG_TAG, viewName + " EndPointY:" + endPointY);


        final Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                int[] viewPositions = new int[2];
                view.getLocationInWindow(viewPositions);

                Log.w(LOG_TAG, viewName + " X on Screen Animation DOWN:" + viewPositions[0]);
                Log.w(LOG_TAG, viewName + " Y on Screen: Animation DOWN:" + viewPositions[1]);

                animationUp(endPointY, view);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };

        // Move the button over to the right and then back
        final ObjectAnimator translateAnimationDown = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, endPointY);
//        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimationDown.setDuration(3_000);
        translateAnimationDown.start();
        translateAnimationDown.addListener(animatorListener);


    }

    private void animationUp(int endPointY, final View view) {
        final ObjectAnimator translateAnimationUp = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 1538, 300);
        translateAnimationUp.setDuration(3_000);
        translateAnimationUp.start();
        translateAnimationUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                int[] viewPositions = new int[2];
                view.getLocationInWindow(viewPositions);

                Log.w(LOG_TAG, "ViewRed X on Screen Animation UP:" + viewPositions[0]);
                Log.w(LOG_TAG, "ViewRed Y on Screen: Animation UP:" + viewPositions[1]);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void getScreenWidthHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        Log.e(LOG_TAG, "Width of Screen:" + screenWidth);
        Log.e(LOG_TAG, "Height of Screen:" + screenHeight);
    }
}
