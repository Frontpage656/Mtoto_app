package com.example.mtoto_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ValueLineChart lineChart;
    ValueLineSeries series;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lineChart = findViewById(R.id.lineChart);

        lineChart.setLegendColor(getColor(R.color.white));
        series = new ValueLineSeries();
        series.setColor(getColor(R.color.white));

        series.addPoint(new ValueLinePoint("Jan", 0));
        series.addPoint(new ValueLinePoint("Feb", 7));
        series.addPoint(new ValueLinePoint("Mar", 10));
        series.addPoint(new ValueLinePoint("Apr", 3));
        series.addPoint(new ValueLinePoint("Mai", 15));
        series.addPoint(new ValueLinePoint("Jun", 20));
        series.addPoint(new ValueLinePoint("Jul", 25));

        lineChart.addSeries(series);
        lineChart.startAnimation();

    }
}