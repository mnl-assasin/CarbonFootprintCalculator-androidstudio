package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.sti.carbonfootprintcalculator.R;


public class HistoryVehicles extends Activity {

	TextView tvDistance;
	TextView tvFuel;
	TextView tvCar;
	TextView tvYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_appliances);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		tvDistance = (TextView) findViewById(R.id.tvDistance);
		tvFuel = (TextView) findViewById(R.id.tvFuel);
		tvCar = (TextView) findViewById(R.id.tvCar);
		tvYear = (TextView) findViewById(R.id.tvYear);

		String id = getIntent().getStringExtra("id");
		populateRecord(id);

	}

	private void populateRecord(String id) {
		DatabaseHelper dbHelper = DatabaseHelper
				.getInstance(getApplicationContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("tbl_emission_vehicle", null, "id = ?",
				new String[] { id }, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				tvDistance.setText(tvDistance.getText().toString()
						+ cursor.getString(1) + "KM");

				tvFuel.setText(tvFuel.getText().toString()
						+ cursor.getString(2));

				tvCar.setText(tvCar.getText().toString() + cursor.getString(3));

				tvYear.setText(tvYear.getText().toString()
						+ cursor.getString(4));
			}
		}
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
