package org.highcharts;

import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.chart.Chart;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;

public interface HighChartsProvider {
	Chart getChart();

	Title getTitle();

	SubTitle getSubTitle();

	XAxis getXAxis();

	YAxis getYAxis();

	Tooltip getTooltip();

	Legend getLegend();

	Series[] getSeries();
}
