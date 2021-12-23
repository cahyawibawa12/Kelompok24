package com.example.nepo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Database.DbHelper;

public class ProfileActivity extends AppCompatActivity {
    private String strId,strNama,strUsername,strPassword;
    private TextView nama_lengkap, user_name, pass_word;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nama_lengkap = (TextView)findViewById(R.id.liat_nama);
        user_name = (TextView)findViewById(R.id.liat_username);
        pass_word = (TextView)findViewById(R.id.liat_password);

        Intent getData = getIntent();
        strId = getData.getStringExtra("id");

        DbHelper dbHelper = new DbHelper(this);

        Cursor cursor = dbHelper.tampilkanPenggunaDariID(strId);

        while (cursor.moveToNext()) {
            strNama = cursor.getString(1);
            strUsername = cursor.getString(2);
            strPassword = cursor.getString(3);
        }
        nama_lengkap.setText(strNama);
        user_name.setText(strUsername);
        pass_word.setText(strPassword);
    }
}
