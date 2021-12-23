package com.example.nepo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DbHelper;
import Database.FormHandler;
import Database.UserHandler;

public class TambahUserActivity extends AppCompatActivity {
    Button daftar;
    EditText nama, username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_login);

        daftar = (Button)findViewById(R.id.btnBuat);
        nama = (EditText)findViewById(R.id.edit_nama_lengkap);
        username = (EditText)findViewById(R.id.edit_username);
        password = (EditText)findViewById(R.id.edit_password);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper dbHelper = new DbHelper(getApplicationContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahUserActivity.this);
                builder.setIcon(R.drawable.warning);
                builder.setTitle("Submit");
                builder.setMessage(
                        "Apakah Anda Sudah Yakin Dengan Data Anda ?\n\n"+
                                "Nama Lengkap : \n" + nama.getText().toString() + "\n\n"+
                                "Username : \n" + username.getText().toString() + "\n\n"+
                                "Password : \n" + password.getText().toString()
                );

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() { //method button positive desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TambahUserActivity.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                        UserHandler userHandler = importToModel();
                        simpanPenggunaKeDB(userHandler);
                        String id = getLastID();
                        Intent gotoLoby = new Intent(TambahUserActivity.this, MainActivity.class);
                        gotoLoby.putExtra("id", id);
//                        DbHelper dbHelper = new DbHelper(getApplicationContext());
//                        UserHandler userHandler = new UserHandler();
//                        userHandler.setNama_Lengkap(nama.getText().toString());
//                        userHandler.setUsername(username.getText().toString());
//                        userHandler.setPassword(password.getText().toString());
//
//                        boolean tambahUser = dbHelper.tambahUser(userHandler);
//
//                        if(tambahUser) {
//                            Toast.makeText(TambahUserActivity.this, "Data anda berhasil terdaftarkan !", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(TambahUserActivity.this, "Data anda gagal terdaftar!", Toast.LENGTH_SHORT);
//                        }
////                        dbHelper.close();
//
////                        NamaLengkap.setText("");
////                        TanggalLahir.setText("");
////                        gender.setText("");
                        startActivity(gotoLoby);
                        finish();
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() { //method button negative desicion
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create(); //method get alert dan create alert
                alertDialog.show(); //to show alert
            }
        });
    }
    private UserHandler importToModel(){
        UserHandler userHandler = new UserHandler();
        userHandler.setNama_Lengkap(nama.getText().toString());
        userHandler.setUsername(username.getText().toString());
        userHandler.setPassword(password.getText().toString());
        return userHandler;
    }

    private void simpanPenggunaKeDB(UserHandler userHandler){
        DbHelper db = new DbHelper(getApplicationContext());
        db.tambahUser(userHandler);
    }

    private String getLastID(){
        String id = new String();
        DbHelper db = new DbHelper(this);
        Cursor cursor = db.tampilkanLastID();

        while (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        return id;
    }
}
