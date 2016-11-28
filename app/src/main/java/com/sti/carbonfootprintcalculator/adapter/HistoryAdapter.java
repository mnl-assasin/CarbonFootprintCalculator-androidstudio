package com.sti.carbonfootprintcalculator.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sti.carbonfootprintcalculator.R;
import com.sti.carbonfootprintcalculator.activity.HistoryAppliances;
import com.sti.carbonfootprintcalculator.activity.HistoryVehicles;
import com.sti.carbonfootprintcalculator.model.HistoryItem;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<HistoryItem> item;
	LayoutInflater inflater;

	public HistoryAdapter(Context mContext, ArrayList<HistoryItem> item) {
		super();
		this.mContext = mContext;
		this.item = item;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return item.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View arg1, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.listitem_history, parent, false);
		LinearLayout container = (LinearLayout) v.findViewById(R.id.container);
		container.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (item.get(position).getType() == 0)
					mContext.startActivity(new Intent(mContext,
							HistoryAppliances.class).putExtra("id",
							item.get(position).getId()));
				else
					mContext.startActivity(new Intent(mContext,
							HistoryVehicles.class).putExtra("id",
							item.get(position).getId()));

			}
		});
		TextView tvType = (TextView) v.findViewById(R.id.tvType);
		if (item.get(position).getType() == 0)
			tvType.setText("Household");
		else
			tvType.setText("Vehicle");

		TextView tvEmission = (TextView) v.findViewById(R.id.tvEmission);
		double emission = item.get(position).getValue();
		String emi;
		if (emission > 1000) {
			emi = String.format("%.2f", emission / 1000) + " Ton";
		} else
			emi = String.format("%.2f", emission) + " Kg";
		tvEmission.setText(emi);
		// tvEmission.setText(String.valueOf(Math.round(item.get(position)
		// .getValue())) + " Kg");

		TextView tvArea = (TextView) v.findViewById(R.id.tvArea);
		tvArea.setText(String.valueOf(item.get(position).getArea()));

		TextView tvDate = (TextView) v.findViewById(R.id.tvDate);
		tvDate.setText(String.valueOf(item.get(position).getId()));

		return v;
	}
}
