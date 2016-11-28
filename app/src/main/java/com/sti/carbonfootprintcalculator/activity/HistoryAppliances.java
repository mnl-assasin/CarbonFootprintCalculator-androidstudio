package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.sti.carbonfootprintcalculator.R;
import com.sti.carbonfootprintcalculator.adapter.AppliancesAdapter;
import com.sti.carbonfootprintcalculator.model.AppliancesItem;

import java.util.ArrayList;


public class HistoryAppliances extends Activity {
	String TAG = "HistoryAppliances";
	ListView listAppliances;
	ArrayList<AppliancesItem> item;
	AppliancesAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_appliances);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		item = new ArrayList<AppliancesItem>();
		adapter = new AppliancesAdapter(HistoryAppliances.this, item);
		listAppliances = (ListView) findViewById(R.id.listAppliances);
		listAppliances.setAdapter(adapter);

		String id = getIntent().getStringExtra("id");
		populateRecord(id);
	}

	private void populateRecord(String id) {
		DatabaseHelper dbHelper = DatabaseHelper
				.getInstance(getApplicationContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("tbl_emission_household", null, "id = ?",
				new String[] { id }, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String appliancesName;
				int wattage;
				int hours;
				int kwh;

				appliancesName = cursor.getString(1);
				wattage = cursor.getInt(2);
				hours = cursor.getInt(3);
				kwh = wattage * hours * 30;
				Log.d(TAG, "Wattage: " + wattage);
				Log.d(TAG, "Hours: " + hours);
				Log.d(TAG, "Watts Hour (1 month): " + kwh);
				item.add(new AppliancesItem(appliancesName, wattage, hours, kwh));
			}
		}

		adapter.notifyDataSetChanged();
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
}
