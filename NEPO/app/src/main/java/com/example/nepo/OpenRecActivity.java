package com.example.nepo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Database.AmbilEventInter;
import Database.DbHelper;
import Database.KegiatanHandler;
import Database.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenRecActivity extends AppCompatActivity {
    protected RecyclerView recyclerView;
    private DbHelper database;
    protected RecyclerView.Adapter openRecAdapter;
    private List<KegiatanHandler> kegiatanHandler = new ArrayList<KegiatanHandler>();
    Button btnAdd;
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

//        database = new DbHelper(this);
        recyclerView = findViewById(R.id.recyclerview);
        btnAdd = findViewById(R.id.btn_add);
        srlData = findViewById(R.id.swl_data);
        pbData = findViewById(R.id.pb_data);

//        final DbHelper dh = new DbHelper(getApplicationContext());
//        Cursor cursor = dh.tampilKegiatan();
//        cursor.moveToFirst();
//        if (cursor.getCount() > 0){
//            while (!cursor.isAfterLast()){
//                KegiatanHandler kegiatanHandlerList =new KegiatanHandler();
//                kegiatanHandlerList.setJudul((cursor.getString(cursor.getColumnIndexOrThrow("judul"))));
//                kegiatanHandlerList.setDeskripsi((cursor.getString((cursor.getColumnIndexOrThrow("deskripsi")))));
//                kegiatanHandlerList.setTanggal_Mulai((cursor.getString((cursor.getColumnIndexOrThrow("tanggal_mulai")))));
//                kegiatanHandlerList.setTanggal_Akhir((cursor.getString((cursor.getColumnIndexOrThrow("tanggal_akhir")))));
//                kegiatanHandler.add(kegiatanHandlerList);
//                cursor.moveToNext();
//            }
////            dh.close();
//        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenRecActivity.this, KegiatanActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
//        openRecAdapter = new OpenRecAdapter(kegiatanHandler,OpenRecActivity.this, recyclerView);
//        recyclerView.setAdapter(openRecAdapter);
//        retrieveData();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        retrieveData();
    }

    public void retrieveData(){
        pbData.setVisibility(View.VISIBLE);

        AmbilEventInter ambileventInter = RetrofitHelper.connectRetrofit().create(AmbilEventInter.class);
        Call<List<KegiatanHandler>> getKegiatan = ambileventInter.AmbilEventInter();

        getKegiatan.enqueue(new Callback<List<KegiatanHandler>>() {
            @Override
            public void onResponse(Call<List<KegiatanHandler>> call, Response<List<KegiatanHandler>> response) {
                kegiatanHandler = response.body();
                openRecAdapter = new OpenRecAdapter(kegiatanHandler, OpenRecActivity.this, recyclerView);
                recyclerView.setAdapter(openRecAdapter);
                openRecAdapter.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<KegiatanHandler>> call, Throwable t) {
                Toast.makeText(OpenRecActivity.this, "Gagal mengambil data pinjaman : "+ t.getMessage(), Toast.LENGTH_LONG).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }

}
