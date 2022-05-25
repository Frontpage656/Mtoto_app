package com.example.mtoto_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ingia_button,jisajiri_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingia_button = findViewById(R.id.ingia_button);
        jisajiri_btn = findViewById(R.id.jisajiri_btn);


        jisajiri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Jisajiri.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}