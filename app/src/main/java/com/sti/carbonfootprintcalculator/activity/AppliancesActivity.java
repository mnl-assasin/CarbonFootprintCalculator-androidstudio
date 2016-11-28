package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sti.carbonfootprintcalculator.R;
import com.sti.carbonfootprintcalculator.adapter.AppliancesAdapter;
import com.sti.carbonfootprintcalculator.model.AppliancesItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AppliancesActivity extends Activity {
	String TAG = "AppliancesActivity";
	LinearLayout container;
	Spinner spinnerAppliances;
	EditText etWattage;
	EditText etHours;
	Button btnAdd;
	ListView listAppliances;
	ArrayList<AppliancesItem> item;
	AppliancesAdapter adapter;
	Spinner spinnerCaviteArea;
	Button btnCalculate;

	double totalKWH = 0;

	DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appliances);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		dbHelper = DatabaseHelper.getInstance(getApplicationContext());

		container = (LinearLayout) findViewById(R.id.container);
		Utils.hideSoftKeyboard(this, container);

		spinnerAppliances = (Spinner) findViewById(R.id.spinnerAppliances);
		etWattage = (EditText) findViewById(R.id.etWattage);
		etHours = (EditText) findViewById(R.id.etHours);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int wattage;
				int hours;
				if (!(etWattage.getText().toString().equals("")))
					wattage = Integer.parseInt(etWattage.getText().toString());
				else
					wattage = 0;
				if (!(etHours.getText().toString().equals("")))
					hours = Integer.parseInt(etHours.getText().toString());
				else
					hours = 0;
				if (!(wattage == 0 && hours == 0))
					addAppliances(wattage, hours);
				else
					Toast.makeText(getApplicationContext(),
							"Please enter valid WATTAGE and HOURS",
							Toast.LENGTH_SHORT).show();
			}
		});

		item = new ArrayList<AppliancesItem>();
		adapter = new AppliancesAdapter(AppliancesActivity.this, item);
		listAppliances = (ListView) findViewById(R.id.listAppliances);
		listAppliances.setAdapter(adapter);

		spinnerCaviteArea = (Spinner) findViewById(R.id.spinnerCaviteArea);
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		btnCalculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (!(item.size() == 0))
					calculateEmission();
				else
					Toast.makeText(AppliancesActivity.this,
							"Please add Appliances first!", Toast.LENGTH_LONG)
							.show();
			}
		});

	}

	private void addAppliances(int wattage, int hours) {

		String appliancesName = spinnerAppliances.getSelectedItem().toString();
		int kwh = wattage * hours * 30;
		totalKWH += (kwh);
		Log.d(TAG, "Wattage: " + wattage);
		Log.d(TAG, "Hours: " + hours);
		Log.d(TAG, "Watts Hour (1 month): " + kwh);
		item.add(new AppliancesItem(appliancesName, wattage, hours, kwh));
		adapter.notifyDataSetChanged();
		spinnerAppliances.setSelection(0);
		etHours.setText("");
		etWattage.setText("");

	}

	private void calculateEmission() {
		Log.d(TAG, "Total KWH of Appliances: " + totalKWH / 1000);
		double ef = 0.58;
		double emission = (totalKWH / 1000 * ef);

		Log.d(TAG, "Emission: " + String.format("%.2f", emission));
		addRecord(emission);
		clear();
		Bundle extras = new Bundle();
		extras.putDouble("emission", emission);
		Intent intent = new Intent(getApplicationContext(),
				ResultActivity.class);
		intent.putExtras(extras);
		startActivity(intent);
	}

	private void addRecord(double emission) {

		String time = getCurrentTime();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", time);
		values.put("emission_type", 0);
		values.put("emission_value", emission);
		values.put("area", spinnerCaviteArea.getSelectedItem().toString());

		db.insert("tbl_emission_history", null, values);

		values.clear();

		for (int ctr = 0; ctr < item.size(); ctr++) {
			values.put("id", time);
			values.put("appliances_name", item.get(ctr).getAppliancesName());
			values.put("wattage", item.get(ctr).getWattage());
			values.put("hours", item.get(ctr).getHours());
			values.put("kwh", item.get(ctr).getKwh());
			db.insert("tbl_emission_household", null, values);
			values.clear();
		}
	}

	private String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a yyyy-MM-dd",
				Locale.ENGLISH);
		String time = sdf.format(c.getTime());
		Log.d("AppliancesActivity", time);

		return time;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void clear() {
		spinnerAppliances.setSelection(0);
		spinnerCaviteArea.setSelection(0);
		item.clear();
		adapter.notifyDataSetChanged();
		totalKWH = 0;
		etHours.setText("");
		etWattage.setText("");
	}

}
