package com.example.nepo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database.AmbilEventInter;
import Database.DbHelper;
import Database.KegiatanHandler;
import Database.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KegiatanActivity extends AppCompatActivity {

    private String judul_kegiatan, deskripsi_kegiatan,Tglawal,Tglakhir;
    private EditText judul, deskripsi, tanggal_akhir, tanggal_awal;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);

        judul = (EditText)findViewById(R.id.edit_judul_kegiatan);
        deskripsi = (EditText)findViewById(R.id.edit_deskripsi);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        tanggal_awal = findViewById(R.id.edit_tanggal_mulai);
        tanggal_akhir = findViewById(R.id.edit_tanggal_akhir);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul_kegiatan = judul.getText().toString();
                deskripsi_kegiatan = deskripsi.getText().toString();
                Tglawal = tanggal_awal.getText().toString();
                Tglakhir = tanggal_akhir.getText().toString();

                if(judul_kegiatan.trim().equals("")){
                    judul.setError("Judul Kegiatan Harus Diisi");
                }
                else if(deskripsi_kegiatan.trim().equals("")){
                    deskripsi.setError("Deskripsi Harus Diisi");
                }
                else if(Tglawal.trim().equals("")){
                    tanggal_awal.setError("Tanggal Mulai Harus Diisi");
                }
                else if(Tglakhir.trim().equals("")){
                    tanggal_akhir.setError("Tanggal Berakhir Harus Diisi");
                }
                else{
                    dialogAlert();
                    createData();
                }
//                dialogAlert();
            }
        });
    }

    private void dialogAlert(){
        AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(KegiatanActivity.this);
        dialogAlertBuilder.setTitle("Konfirmasi Data");
        dialogAlertBuilder
                .setMessage("Judul : " +judul_kegiatan+ "\n" +
                        "Kategori : " +deskripsi_kegiatan+ "\n"+
                        "Tanggal Mulai : " +Tglawal+ "\n"+
                         "Tanggal Akhir : " +Tglakhir+ "\n"
                        )
                .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbHelper dbHelper = new DbHelper(getApplicationContext());
                        KegiatanHandler kegiatanHandler = new KegiatanHandler();
                        kegiatanHandler.setJudul(judul_kegiatan.toUpperCase());
                        kegiatanHandler.setDeskripsi(deskripsi_kegiatan.toUpperCase());
                        kegiatanHandler.setTanggal_Mulai(Tglawal.toUpperCase());
                        kegiatanHandler.setTanggal_Akhir(Tglakhir.toUpperCase());

                        boolean tambahKegiatan = dbHelper.tambahKegiatan(kegiatanHandler);

                        if (tambahKegiatan) {
                            Toast.makeText(KegiatanActivity.this, "Tambah Kegiatan Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KegiatanActivity.this, "Tambah Kegiatan Gagal", Toast.LENGTH_SHORT).show();
                        }
//                        dbHelper.close();

                        judul.getText().clear();
                        deskripsi.getText().clear();
                        tanggal_awal.getText().clear();
                        tanggal_akhir.getText().clear();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = dialogAlertBuilder.create();

        dialog.show();
    }

    public void createData(){

        AmbilEventInter ambileventInter = RetrofitHelper.connectRetrofit().create(AmbilEventInter.class);
        Call<List<KegiatanHandler>> simpanData = ambileventInter.CreateEventInter(judul_kegiatan, deskripsi_kegiatan,Tglawal,Tglakhir);

        simpanData.enqueue(new Callback<List<KegiatanHandler>>() {
            @Override
            public void onResponse(Call<List<KegiatanHandler>> call, Response<List<KegiatanHandler>> response) {
//                int kode = response.body();
//                String pesan = response.body();

                Toast.makeText(KegiatanActivity.this, "Kode: ", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<List<KegiatanHandler>> call, Throwable t) {
//                Toast.makeText(KegiatanActivity.this, "Gagal menghubungi server : "+ t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}