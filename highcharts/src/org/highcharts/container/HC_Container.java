package org.highcharts.container;

import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.chart.Chart;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;

public class HC_Container {
	private Chart chart;
	private Title title;
	private SubTitle subtitle;
	private XAxis xAxis;
	private YAxis yAxis;
	private Tooltip tooltip;
	private Legend legend;
	private Series[] series;

	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public SubTitle getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(SubTitle subtitle) {
		this.subtitle = subtitle;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public YAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public Series[] getSeries() {
		return series;
	}

	public void setSeries(Series[] series) {
		this.series = series;
	}


}
