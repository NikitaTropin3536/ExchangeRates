package com.example.exchangerates.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exchangerates.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonSummaryActivity extends AppCompatActivity {

    private TextView name; /* имя человека */

    private String photoUrl; /* url фотки человека */

    private CircleImageView photo; /* фотка человека */

    private TextView summary; /* предисловие */

    private AppCompatButton goRead; /* кнопка */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_person);

        name = findViewById(R.id.name);
        photo = findViewById(R.id.photo);
        summary = findViewById(R.id.summary);
        goRead = findViewById(R.id.goRead);

        Intent intent = getIntent();

        /* забираем входящие данные */
        name.setText(intent.getStringExtra("name"));

        photoUrl = intent.getStringExtra("photoUrl");

        /* устанавливаем фотографию */
        Glide.with(this)
                .load(photoUrl).
                into(photo);

        summary.setText(intent.getStringExtra("summary"));
    }
}