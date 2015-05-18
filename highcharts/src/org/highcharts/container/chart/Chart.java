package org.highcharts.container.chart;

import org.highcharts.container.plotOptions.PlotOptions;

public class Chart {
	private Object type;

	public Object getType() {
		return type;
	}

	public void setType(PlotOptions type) {
		this.type = type;
	}
	
	/**
	 * @param type 可以调用PlotOptions实现类的simple方法
	 */
	public void setType(String type){
		this.type = type;
	}
}
