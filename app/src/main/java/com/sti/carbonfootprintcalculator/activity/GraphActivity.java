package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sti.carbonfootprintcalculator.R;

import java.util.ArrayList;

public class GraphActivity extends Activity {


    HorizontalBarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        barChart = (HorizontalBarChart) findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 0));
        entries.add(new BarEntry(0f, 1));
        entries.add(new BarEntry(0f, 2));
        entries.add(new BarEntry(0f, 3));
        entries.add(new BarEntry(0f, 4));
        entries.add(new BarEntry(0f, 5));
        entries.add(new BarEntry(0f, 6));
        entries.add(new BarEntry(0f, 7));
        entries.add(new BarEntry(0f, 8));
        entries.add(new BarEntry(0f, 9));

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Alfonso");
        labels.add("Amadeo");
        labels.add("Bacoor");
        labels.add("Carmona");
        labels.add("Cavite");
        labels.add("Dasmariñas");
        labels.add("General Emilio Aguinaldo");
        labels.add("General Mariano Alvarez");
        labels.add("General Trias");
        labels.add("Imus");

        BarDataSet dataSet = new BarDataSet(entries, "Label");

        BarData data = new BarData(labels, dataSet);
        barChart.setData(data);

        /**
         * <item>Alfonso</item>
         <item>Amadeo</item>
         <item>Bacoor</item>
         <item>Carmona</item>
         <item>Cavite City</item>
         <item>Dasmariñas</item>
         <item>General Emilio Aguinaldo</item>
         <item>General Mariano Alvarez</item>
         <item>General Trias</item>
         <item>Imus</item>
         <item>Indang</item>
         <item>Kawit</item>
         <item>Magallanes</item>
         <item>Maragondon</item>
         <item>Mendez</item>
         <item>Naic</item>
         <item>Noveleta</item>
         <item>Rosario</item>
         <item>Silang</item>
         <item>Tagaytay</item>
         <item>Tanza</item>
         <item>Trece Martires</item>
         <item>Ternate</item>
         */
    }
}
