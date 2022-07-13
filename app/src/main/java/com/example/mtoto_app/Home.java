package com.example.mtoto_app;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Home extends AppCompatActivity {

    String API_FROM_HARDWARE = "https://api.thingspeak.com/channels/1720215/feeds.json?api_key=E0KZA5LSSNGNP42A&results";
    TextView today_uzito, today_urefu;
    ProgressDialog dialog;
    ModeClass modeClass;
    ImageView refresh;

    TabLayout tab;
    ViewPager2 pageView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.purple_700));
        }

        tab = findViewById(R.id.tab);
        pageView = findViewById(R.id.pageView);

        today_uzito = findViewById(R.id.today_uzito);
        today_urefu = findViewById(R.id.today_urefu);

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                dataFromHardWare();
            }
        });

        dialog = new ProgressDialog(Home.this);
        dialog.setCancelable(false);
        dialog.setMessage("subiri kidogo...");

        pageView.setAdapter(new FragmentAdapter(Home.this));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab, pageView, false, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("UZITO");
                        break;
                    case 1:
                        tab.setText("UREFU");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

    }

    @Override
    protected void onStart() {
        super.onStart();

        dialog.show();
        dataFromHardWare();
    }

    private void dataFromHardWare() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_FROM_HARDWARE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray today = null;
                try {
                    dialog.dismiss();
                    today = response.getJSONArray("feeds");
                    JSONObject single_field = (JSONObject) today.get(today.length() - 1);
                    modeClass = new ModeClass(single_field.getInt("field1"), single_field.getInt("field2"));

                    today_uzito.setText(String.valueOf(single_field.getInt("field1")));
                    today_urefu.setText(String.valueOf(single_field.getInt("field2")));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(Home.this, "Angalia mtandao wako!!!", Toast.LENGTH_SHORT).show();
            }
        });

        SingletonClass.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

}