package com.sti.carbonfootprintcalculator.model;

public class HistoryItem {

	String id;
	int type;
	double value;
	String area;

	public HistoryItem(String id, int type, double value, String area) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
		this.area = area;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getArea() {
		return area;
	}

}
