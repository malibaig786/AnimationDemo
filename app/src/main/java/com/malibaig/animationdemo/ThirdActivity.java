package com.malibaig.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    View image, layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.view_poster);
        layout = findViewById(R.id.layout_group);
        Button animate = (Button) findViewById(R.id.btn_animate);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThirdActivity.this, "Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        animate.setOnClickListener(this);

        image.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        startAnimation(layout, R.anim.layout_translation);
        startAnimation(image, R.anim.translate_image);
    }

    private void startAnimation(View view, int animId) {
        view.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, animId);
        view.startAnimation(animation);
    }
}
