package com.example.nepo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import Database.DbHelper;

public class MainActivity extends AppCompatActivity {
    private String id,nama;
    private TextView label_nama;
    private ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOprec, btnDaftar, btnHelp;

        btnOprec = findViewById(R.id.button6);
        btnDaftar = findViewById(R.id.button7);
        btnHelp = findViewById(R.id.button8);
        label_nama = findViewById(R.id.user);
        profile = findViewById(R.id.profile);
        ImageSlider imageSlide = findViewById(R.id.slide);

        Intent getData = getIntent();
        id = getData.getStringExtra("id");

        DbHelper dbHelper = new DbHelper(this);

        Cursor cursor = dbHelper.tampilkanPenggunaDariID(id);

        while (cursor.moveToNext()) {
            nama = cursor.getString(1);
        }

        label_nama.setText(nama);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://1.bp.blogspot.com/-lTJvQzNtTRw/XMTxH9UGFCI/AAAAAAAAPFQ/iVfu94tODOQ_AVuG1m-zN1Hl4NcipaCIACLcBGAs/w1200-h630-p-k-no-nu/event.png","Event A"));
        slideModels.add(new SlideModel("https://i2.wp.com/eventpelajar.com/wp-content/uploads/2019/11/IMG-20191109-WA0013.jpg?fit=768%2C1280&ssl=1","Event B"));
        slideModels.add(new SlideModel("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/summer-festival-event-flyer-template-design-8d443930f69ca9a66af7ff87a6046877_screen.jpg?ts=1636996859","Event C"));
        slideModels.add(new SlideModel("https://i2.wp.com/unnes.ac.id/wp-content/uploads/PAMFLET-WORKSHOP.jpg?ssl=1","Event D"));
        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8ULrTgleNSVdQSeg9m0lE-8-5DV8z3b1iu_7QztCX5tVudpiewpzJBnQNUuBKFmUTDQ0&usqp=CAU","Event E"));
        imageSlide.setImageList(slideModels,true);

        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(MainActivity.this, ProfileActivity.class);
                gotoProfile.putExtra("id", id);
                startActivity(gotoProfile);
            }
        });

        btnOprec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenRecActivity.class);
                startActivity(intent);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListFormActivity.class);
                startActivity(intent);
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }
}