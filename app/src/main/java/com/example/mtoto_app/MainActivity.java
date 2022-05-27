package com.example.mtoto_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView ingia_button, jisajiri_btn;
    EditText mtoto_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingia_button = findViewById(R.id.ingia_button);
        jisajiri_btn = findViewById(R.id.jisajiri_btn);
        mtoto_number = findViewById(R.id.mtoto_number);

        ingia_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mtoto_number.getText().toString().equals("")) {
                    if (mtoto_number.getText().toString().equals("123")) {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Namba ya mtoto sio sahihi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Jaza namba ya mtoto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jisajiri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Jisajiri.class));
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}