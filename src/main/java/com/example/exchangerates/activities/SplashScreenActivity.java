package com.example.exchangerates.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.exchangerates.R;
import com.example.exchangerates.databinding.ActivitySplashScreenBinding;

// TODO ПРОВЕРЕНО

/**
 * стартовая активность - splash screen
 */
public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    /* загрузчик людей */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // todo далее в программе идет повторение этих стрчек - плохо!!!
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /** создаем новый поток */
        Thread thread = new Thread(new MyThread());
        thread.start();
    }

    /**
     * переход на новую активность
     */
    private void transition() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // анимация
        finish();
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                /* загружаем людей */
                Thread.sleep(450);
            } catch (InterruptedException ignored) {
            }
            // проходит 0, 45 секунды - переход
            transition();
        }
    }
}