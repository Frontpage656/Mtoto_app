package com.example.mtoto_app;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Uzito extends Fragment {

    private static final String API_FROM_HARDWARE = "https://api.thingspeak.com/channels/1720215/feeds.json?api_key=E0KZA5LSSNGNP42A&results";
    ValueLineChart lineChart_uzito;
    ValueLineSeries series;
    ModeClass modeClass;
    String months[] = {"Jan", "Feb", "Mar", "Apr", "Mai", "Jun", "Jul"};
    ProgressDialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uzito, container, false);

        lineChart_uzito = view.findViewById(R.id.lineChart_uzito);
        lineChart_uzito.setLegendColor(getActivity().getColor(R.color.white));
        series = new ValueLineSeries();
        series.setColor(getActivity().getColor(R.color.white));

        dataFromHardWare();


        return view;
    }

    private void dataFromHardWare() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_FROM_HARDWARE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray today = response.getJSONArray("feeds");
                    JSONObject single_field = (JSONObject) today.get(today.length() - 1);

                    modeClass = new ModeClass(single_field.getInt("field1"), single_field.getInt("field2"));

                    for (int i = 0; i <= today.length(); i++) {

                        try {

                            JSONObject urefu_object = (JSONObject) today.get(i);
                            series.addPoint(new ValueLinePoint(months[i], urefu_object.getInt("field1")));

                            Log.e("ans uzito----------> ", months[i] + "," + urefu_object.getInt("field1"));

                        } catch (Exception e) {

                        }


                    }
                    lineChart_uzito.addSeries(series);
                    lineChart_uzito.startAnimation();

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "error" + e, Toast.LENGTH_SHORT).show();
                    Log.e("error is --->", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Angalia mtandao!!!", Toast.LENGTH_SHORT).show();
            }
        });

        SingletonClass.getInstance(getContext()).addToRequestQueue(request);
    }
}