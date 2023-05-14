package com.example.exchangerates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.exchangerates.databinding.ActivityMainBinding;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

/**
 * класс первой активности приложения
 * тут находится кнопка для получения данных
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Thread secThread;
    private Runnable runnable;
    private PeopleParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        parser = new PeopleParser();
        init();
    }

    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                parser.getTag();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
}