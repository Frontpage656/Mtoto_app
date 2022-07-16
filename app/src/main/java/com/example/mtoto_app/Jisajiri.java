package com.example.mtoto_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Jisajiri extends AppCompatActivity {

    ProgressDialog dialog;

    TextView next_btn;
    EditText mama_name,baba_name,mtoto_date,mimba_umri,mtoto_uzito,mtoto_urefu,mzingo,wengapi,mtoto_id;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jisajiri);

        dialog = new ProgressDialog(Jisajiri.this);

        next_btn = findViewById(R.id.reg_btn);
        mama_name = findViewById(R.id.mama_name);
        baba_name = findViewById(R.id.baba_name);
        mtoto_date = findViewById(R.id.mtoto_date);
        mimba_umri = findViewById(R.id.mimba_umri);
        mtoto_uzito = findViewById(R.id.mtoto_uzito);
        mtoto_urefu = findViewById(R.id.mtoto_urefu);
        mzingo = findViewById(R.id.mzingo);
        wengapi = findViewById(R.id.wengapi);
        mtoto_id = findViewById(R.id.mtoto_id);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_700));
        }


        next_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(mama_name.getText().toString())
                        || TextUtils.isEmpty(mama_name.getText().toString())
                        || TextUtils.isEmpty(mtoto_id.getText().toString())
                        || TextUtils.isEmpty(baba_name.getText().toString())
                        || TextUtils.isEmpty(mtoto_date.getText().toString())
                        || TextUtils.isEmpty(mimba_umri.getText().toString())
                        || TextUtils.isEmpty(mtoto_uzito.getText().toString())
                        || TextUtils.isEmpty(mtoto_urefu.getText().toString())
                        || TextUtils.isEmpty(mzingo.getText().toString())
                        || TextUtils.isEmpty(wengapi.getText().toString())){

                    Toast.makeText(Jisajiri.this, "Jaza sehemu zote!!!", Toast.LENGTH_SHORT).show();
                }else {

                    HashMap<String, Object> data = new HashMap<>();
                    data.put("mamaName", mama_name.getText().toString());
                    data.put("mtoto_id", mtoto_id.getText().toString());
                    data.put("baba_name", baba_name.getText().toString());
                    data.put("mtoto_date", mtoto_date.getText().toString());
                    data.put("mimba_umri", mimba_umri.getText().toString());
                    data.put("mtoto_uzito", mtoto_uzito.getText().toString());
                    data.put("mtoto_urefu", mtoto_urefu.getText().toString());
                    data.put("mzingo", mzingo.getText().toString());
                    data.put("wengapi", wengapi.getText().toString());

                    dialog.setMessage("taarifa zinahifadhiwa...");
                    dialog.setCancelable(false);
                    dialog.show();


                    db.collection("mtoto").document(mtoto_id.getText().toString()).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();

                                    Toast.makeText(Jisajiri.this, "Mtoto mpya kasajiriwa!!!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                    finish();

                            }else {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"id ipo",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

}