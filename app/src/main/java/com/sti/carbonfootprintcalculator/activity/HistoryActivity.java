package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

import com.sti.carbonfootprintcalculator.R;
import com.sti.carbonfootprintcalculator.adapter.HistoryAdapter;
import com.sti.carbonfootprintcalculator.model.HistoryItem;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

	Spinner spinFilter;
	ListView lvHistory;
	ArrayList<HistoryItem> item;
	ArrayList<HistoryItem> tempItem;
	HistoryAdapter adapter;
	DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		lvHistory = (ListView) findViewById(R.id.lvHistory);

		populateHistory();

		spinFilter = (Spinner) findViewById(R.id.spinFilter);
		spinFilter.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Log.d("HISTORy", "Position: " + position);
				newHistory(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void populateHistory() {
		// TODO Auto-generated method stub
		item = new ArrayList<HistoryItem>();
		adapter = new HistoryAdapter(getApplicationContext(), item);
		lvHistory.setAdapter(adapter);

		dbHelper = DatabaseHelper.getInstance(getApplicationContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("tbl_emission_history", null, null, null,
				null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String id = cursor.getString(0);
				int type = cursor.getInt(1);
				double value = cursor.getDouble(2);
				String area = cursor.getString(3);

				item.add(new HistoryItem(id, type, value, area));
				// tempItem.add(new HistoryItem(id, type, value, area));
			}
		}

		adapter.notifyDataSetChanged();
	}

	private void newHistory(int position) {
		item = new ArrayList<HistoryItem>();
		adapter = new HistoryAdapter(HistoryActivity.this, item);
		lvHistory.setAdapter(adapter);

		dbHelper = DatabaseHelper.getInstance(getApplicationContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("tbl_emission_history", null, null, null,
				null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String id = cursor.getString(0);
				int type = cursor.getInt(1);
				double value = cursor.getDouble(2);
				String area = cursor.getString(3);

				if (position == 0) {
					item.add(new HistoryItem(id, type, value, area));
				} else if (position == 1 && type == 0) {
					item.add(new HistoryItem(id, type, value, area));

				} else if (position == 2 && type == 1) {
					item.add(new HistoryItem(id, type, value, area));
				}
				// tempItem.add(new HistoryItem(id, type, value, area));
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
