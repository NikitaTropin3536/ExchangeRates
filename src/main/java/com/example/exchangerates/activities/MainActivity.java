package com.example.exchangerates.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.exchangerates.adapters.PersonsAdapter;
import com.example.exchangerates.databinding.ActivityMainBinding;
import com.example.exchangerates.loadingPeople.LoaderPersons;
import com.example.exchangerates.loadingPeople.Person;
import com.example.exchangerates.parsing.HumanBiographyParser;

import java.util.ArrayList;

/**
 * класс главной активности приложения
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /* парсер биографий */
    private HumanBiographyParser parser;

    /* загрузчик людей */
    private LoaderPersons loaderPersons;

    private Handler handler;

    /* адаптер для крточек с людьми */
    private PersonsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Log.v("PersonNamesList", loaderPersons.personNames().toString());

                /* создаем адаптер после загрузки данных */
                adapter = new PersonsAdapter(
                        getApplicationContext(),
                        loaderPersons.personPhotos(),
                        loaderPersons.personNames(),
                        loaderPersons.personWhatDo()
                );

                binding.gridView.setAdapter(adapter);

                return false;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                loaderPersons = new LoaderPersons(getApplicationContext(), handler);
            }
        }).start();

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        PersonSummaryActivity.class
                );

                intent.putExtra("name", loaderPersons.personNames().get(position));
                intent.putExtra("photoUrl", loaderPersons.personPhotos().get(position));
                intent.putExtra("summary", loaderPersons.personSummaries().get(position));
                startActivity(intent);
            }
        });

        /* инициализируем наш парсер */
//        parser = new HumanBiographyParser(
//                loaderPersons.personNames(),
//                loaderPersons.infoLinks()
//        );
////
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                for (int i = 0; i < parser.infoLinks().size(); i++) {
////                    try {
////                        HumanData humanData = parser.getHumanData(i);
////
////                        String biography = parser.getBiography(humanData);
////
////                        biographies[i] = biography;
////
////                    } catch (IOException e) {
////                        throw new RuntimeException(e);
////                    }
////                }
////            }
////        }).start();
////
////        ViewPager viewPager = findViewById(R.id.view_pager);
////        BiographyPagerAdapter adapter = new BiographyPagerAdapter(
////            this,
////                loaderPersons.infoLinks(),
////                loaderPersons.names()
////        );
////        viewPager.setAdapter(adapter);
    }

}