package com.kitsune.android.u.auth;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;


public class AnimationDialogFragment extends AppCompatActivity {
    private static final String TAG = "AnimationActivity";
    private LottieAnimationView animationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.animation_dialog_ragment);

        animationView = findViewById(R.id.lottie_animation_view);

        try {
            animationView.setAnimation("login_animation.json");
            animationView.playAnimation();
        } catch (Exception e) {
            Log.e(TAG, "Error al cargar animación: " + e.getMessage());
            Toast.makeText(this, "Error al cargar animación", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(AnimationDialogFragment.this, LoginActivity.class);
                startActivity(splash);
                finish();
            }
        }, 3000);
    }
}