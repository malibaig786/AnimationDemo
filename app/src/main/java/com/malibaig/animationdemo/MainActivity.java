package com.malibaig.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int BOTTOM_NAVIGATION_BAR_HEIGHT = 245;
    AnimatorSet setAnimation2;
    View image, layout;
    int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.view_poster);
        layout = findViewById(R.id.layout_group);


        image.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(LOG_TAG, "onWindowFocusChanged: ");

        getScreenWidthHeight();


        final int[] imageStartingPoint = new int[2];
        image.getLocationOnScreen(imageStartingPoint);
        Log.w(LOG_TAG, "Image X:" + imageStartingPoint[0]);
        Log.w(LOG_TAG, "Image Y:" + imageStartingPoint[1]);

        int imageStartPointY = -(imageStartingPoint[1] + image.getHeight());

        int[] layoutStartingPoint = new int[2];
        layout.getLocationOnScreen(layoutStartingPoint);
        Log.w(LOG_TAG, "Layout X:" + layoutStartingPoint[0]);
        Log.w(LOG_TAG, "Layout Y:" + layoutStartingPoint[1]);

        int LayoutStartPointY = -(layoutStartingPoint[1] + layout.getHeight());

        //EndPoint will add up on Starting point of view plus px we added
        int layoutEndPointY = (screenHeight - layoutStartingPoint[1]) - BOTTOM_NAVIGATION_BAR_HEIGHT;

        Log.w(LOG_TAG, "Layout startPointY:" + layoutStartingPoint[1]);
        Log.w(LOG_TAG, "Image startPointY:" + imageStartingPoint[1]);
        Log.w(LOG_TAG, "Animation End Point:" + layoutEndPointY);
        Log.w(LOG_TAG, "Image Down:" + (imageStartingPoint[1] + layoutEndPointY));
        Log.w(LOG_TAG, "Layout Down:" + (layoutStartingPoint[1] + layoutEndPointY));
        Log.w(LOG_TAG, "Image UP:" + (imageStartingPoint[1] + 400));
        Log.w(LOG_TAG, "Layout Up:" + (layoutStartingPoint[1] + 400));

        final ObjectAnimator translateAnimationImageDown = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, imageStartPointY, layoutEndPointY - 70);
        translateAnimationImageDown.setInterpolator(new DecelerateInterpolator());
        translateAnimationImageDown.setDuration(550);
        translateAnimationImageDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                image.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        final ObjectAnimator translateAnimationImageUp = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, 400);
        translateAnimationImageUp.setInterpolator(new DecelerateInterpolator());
        translateAnimationImageUp.setDuration(900);

        setAnimation2 = new AnimatorSet();
        setAnimation2.play(translateAnimationImageDown).before(translateAnimationImageUp);
        setAnimation2.setStartDelay(200);

        // For Layout Animation
        final ObjectAnimator translateAnimationDown = ObjectAnimator.ofFloat(layout, View.TRANSLATION_Y, LayoutStartPointY, layoutEndPointY);
        translateAnimationDown.setInterpolator(new DecelerateInterpolator());
        translateAnimationDown.setDuration(400);

        final ObjectAnimator translateAnimationUp = ObjectAnimator.ofFloat(layout, View.TRANSLATION_Y, 400);
        translateAnimationUp.setInterpolator(new DecelerateInterpolator());
        translateAnimationUp.setDuration(800);

        final AnimatorSet setAnimation = new AnimatorSet();
        setAnimation.play(translateAnimationDown).before(translateAnimationUp);

        Button animate = (Button) findViewById(R.id.btn_animate);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout.setVisibility(View.VISIBLE);
                setAnimation.start();


                setAnimation2.start();
                image.setVisibility(View.INVISIBLE);

//                createDelay(200);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "onResume: ");
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

//    private void createDelay(int delay) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                setAnimation2.start();
//                image.setVisibility(View.VISIBLE);
//            }
//        }, delay);
//    }

}
