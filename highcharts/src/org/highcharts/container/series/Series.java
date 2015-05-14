package org.highcharts.container.series;


public class Series {
	private String name;
	private Object data;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setData(String data) {
		this.data = data;
	}
	public void setData(double[] data){
		this.data = data;
	}
}
