package com.example.nepo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DbHelper;

public class LoginActivity extends AppCompatActivity {

    private String strUsername, strPassword;
    Button btnLogin, btnTambah;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnTambah = findViewById(R.id.btnTambah);
        username = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.pass_word);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(getApplicationContext());
                strUsername = username.getText().toString();
                strPassword = password.getText().toString();

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Lengkapi Username atau Password!", Toast.LENGTH_SHORT).show();
                } else if (db.cekUsername(strUsername)) {
                    if (db.cekUsernameDanPassword(strUsername, strPassword)>0){
                        Intent gotoLoby = new Intent(LoginActivity.this, MainActivity.class);
                        gotoLoby.putExtra("id", String.valueOf(db.cekUsernameDanPassword(strUsername, strPassword)));
                        startActivity(gotoLoby);
                    }else{
                        Toast.makeText(LoginActivity.this, "Password Salah!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Username Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnTambah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TambahUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
