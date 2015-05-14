package org.highcharts.container;

public enum Align {
	Left("left"), Center("center"), Right("right"),

	Top("top"), Middle("middle"), Bottom("bottom");

	private String str;

	Align(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}
}