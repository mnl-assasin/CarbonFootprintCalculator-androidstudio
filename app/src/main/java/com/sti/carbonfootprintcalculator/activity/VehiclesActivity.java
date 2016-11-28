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
import android.widget.RadioButton;
import android.widget.Spinner;

import com.sti.carbonfootprintcalculator.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VehiclesActivity extends Activity {

	EditText etTraveled;
	RadioButton rbDiesel;
	RadioButton rbGasoline;
	Spinner spinVehicleType;
	Spinner spinVehicleYear;
	Button btnCalculate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicles);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		etTraveled = (EditText) findViewById(R.id.etTraveled);
		rbDiesel = (RadioButton) findViewById(R.id.rbDiesel);
		spinVehicleType = (Spinner) findViewById(R.id.spinVehicleType);
		spinVehicleYear = (Spinner) findViewById(R.id.spinVehicleYear);
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		btnCalculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				compute();
			}
		});

	}

	private void compute() {
		int km = Integer.parseInt(etTraveled.getText().toString());
		int fuelType;
		String fuel;
		if (rbDiesel.isChecked()) {
			fuelType = 0;
			fuel = "Diesel";
		} else
			fuel = "Gasoline";
		fuelType = 1;

		int carType = spinVehicleType.getSelectedItemPosition();
		String type = spinVehicleType.getSelectedItem().toString();

		int yearModel = spinVehicleYear.getSelectedItemPosition();
		String year = spinVehicleYear.getSelectedItem().toString();
		double ef = 0;
		if (fuelType == 0) {
			switch (carType) {
			case 0:
				ef = diesel0(yearModel);
				break;
			case 1:
				ef = diesel1(yearModel);
				break;
			case 2:
			case 3:
				ef = diesel23(yearModel);
				break;
			}
		} else {
			switch (carType) {
			case 0:
				ef = gas0(yearModel);
				break;
			case 1:
			case 2:
				ef = gas12(yearModel);
				break;
			case 3:
				ef = gas3(yearModel);
				break;
			}
		}
		Log.d("Vehicles", "KM: " + km);
		Log.d("Vehicles", "Fuel Type: " + fuel);
		Log.d("Vehicles", "Car Type: " + type);
		Log.d("Vehicles", "Year Model: " + year);
		Log.d("Vehicles", "Emission Factor: " + ef);

		double emission = km * ef;
		Bundle extras = new Bundle();
		extras.putDouble("emission", emission);

		Intent intent = new Intent(getApplicationContext(),
				ResultActivity.class);
		intent.putExtras(extras);
		startActivity(intent);
		addRecord(km, fuel, type, year, emission);
	}

	private void addRecord(int km, String fuel, String type, String year,
			double emission) {
		// TODO Auto-generated method stub
		Spinner spinnerCaviteArea = (Spinner) findViewById(R.id.spinnerCaviteArea);
		DatabaseHelper dbHelper = DatabaseHelper
				.getInstance(getApplicationContext());

		String time = getCurrentTime();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put("id", time);
		values.put("emission_type", 1);
		values.put("emission_value", emission);
		values.put("area", spinnerCaviteArea.getSelectedItem().toString());
		db.insert("tbl_emission_history", null, values);

		values.clear();
		values.put("id", time);
		values.put("km", km);
		values.put("fuelType", fuel);
		values.put("carType", type);
		values.put("yearModel", year);
		db.insert("tbl_emission_vehicle", null, values);
		values.clear();

	}

	private String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a yyyy-MM-dd",
				Locale.ENGLISH);
		String time = sdf.format(c.getTime());
		Log.d("AppliancesActivity", time);

		return time;
	}

	private double gas0(int yearModel) {
		switch (yearModel) {
		case 0:
			return 0.0704;
		case 1:
			return 0.0531;
		case 2:
			return 0.0358;
		case 3:
			return .0272;
		case 4:
			return .0268;
		case 5:
			return .0249;
		case 6:
			return .0216;
		case 7:
			return .0178;
		case 8:
			return .0110;
		case 9:
			return .0107;
		case 10:
			return .0114;
		case 11:
			return .0145;
		case 12:
			return 0.0147;
		case 13:
			return .0161;
		case 14:
			return .0170;
		case 15:
			return .0172;
		default:
			return 0;
		}
	}

	private double gas12(int yearModel) {
		switch (yearModel) {
		case 0:
			return 0.0813;
		case 1:
			return 0.0646;
		case 2:
			return 0.0517;
		case 3:
		case 4:
			return 0.0452;
		case 5:
			return 0.0391;
		case 6:
			return 0.0321;
		case 7:
			return 0.0346;
		case 8:
			return 0.0151;
		case 9:
			return 0.0178;
		case 10:
			return 0.0155;
		case 11:
			return 0.0152;
		case 12:
			return 0.0157;
		case 13:
			return 0.0159;
		case 14:
			return 0.0161;
		case 15:
			return 0.0163;
		default:
			return 0;

		}
	}

	private double gas3(int yearModel) {
		switch (yearModel) {
		case 0:

		case 1:

		case 2:
			return 0.3246;
		case 3:
			return 0.1278;
		case 4:
			return 0.0924;
		case 5:
			return 0.0641;
		case 6:
			return 0.0578;
		case 7:
			return 0.0493;
		case 8:
			return 0.0528;
		case 9:
			return 0.0546;
		case 10:
			return 0.0533;
		case 11:
			return 0.0341;
		case 12:
			return 0.0326;
		case 13:
			return 0.0327;
		case 14:
			return 0.0330;
		case 15:
			return 0.0333;
		default:
			return 0;

		}
	}

	private double diesel0(int yearModel) {
		if (yearModel <= 1995)
			return 0.0006;
		else
			return 0.0005;
	}

	private double diesel1(int yearModel) {
		if (yearModel <= 1995)
			return 0.0009;
		else
			return 0.0010;
	}

	private double diesel23(int yearModel) {
		return 0.0051;
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
