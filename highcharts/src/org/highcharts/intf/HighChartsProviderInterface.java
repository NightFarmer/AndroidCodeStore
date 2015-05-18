package org.highcharts.intf;

import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.chart.Chart;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;

public interface HighChartsProviderInterface {
	/**
	 * @return 图表样式
	 */
	Chart getChart();

	/**
	 * @return 大标题
	 */
	Title getTitle();

	/**
	 * @return 副标题
	 */
	SubTitle getSubTitle();

	/**
	 * @return X轴指标
	 */
	XAxis getXAxis();

	/**
	 * @return Y轴指标
	 */
	YAxis getYAxis();

	/**
	 * @return 图表数据
	 */
	Series[] getSeries();

	Tooltip getTooltip();

	/**
	 * @return 图例
	 */
	Legend getLegend();

}
