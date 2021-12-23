package com.example.nepo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {
    String[] s2;
    TextView text;
    private final static String TAG="AppCallLink";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

//        text = findViewById(R.id.isi_text);
//        s1 = getResources().getStringArray(R.array.about);
//        text.setText(s1);
        // ATTENTION: This was auto-generated to handle app links.
//        Intent appLinkIntent = getIntent();
//        String appLinkAction = appLinkIntent.getAction();
//        Uri appLinkData = appLinkIntent.getData();
    }
    public void Whatsapp(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.link/0drp2m"));
        startActivity(browserIntent);
    }

    public void Telegram(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/cahyawibawa"));
        startActivity(browserIntent);
    }
}
