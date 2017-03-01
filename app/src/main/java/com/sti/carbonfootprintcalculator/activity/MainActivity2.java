package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sti.carbonfootprintcalculator.R;

public class MainActivity2 extends Activity {

    LinearLayout btnAppliances;
    LinearLayout btnVehicle;
    LinearLayout btnHistory;
    LinearLayout btnReport;
    LinearLayout btnMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // getActionBar().setTitle("Carbon Footprint Monitoring & Mapping");

        btnAppliances = (LinearLayout) findViewById(R.id.btnAppliances);
        btnAppliances.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(),
                        AppliancesActivity.class));
            }
        });

        btnVehicle = (LinearLayout) findViewById(R.id.btnVehicles);
        btnVehicle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(),
                        VehiclesActivity.class));
            }
        });

        btnHistory = (LinearLayout) findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(),
                        HistoryActivity.class));
            }
        });

        btnReport = (LinearLayout) findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"ored4a@yahoo.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Carbon Footprint report");
                i.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity2.this,
                            "There are no email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMap = (LinearLayout) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Choose Action");
                builder.setNegativeButton("Bar Graph", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(getApplicationContext(),
                                GraphActivity.class));
                    }
                }).setPositiveButton("Cavite Map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(getApplicationContext(),
                                MapActivity.class));
                    }
                }).show();
            }
        });

    }
}
