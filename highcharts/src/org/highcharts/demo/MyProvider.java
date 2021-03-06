package org.highcharts.demo;

import org.highcharts.container.Align;
import org.highcharts.container.axis.AxisTitle;
import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.chart.Chart;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.legend.Legend.Layout;
import org.highcharts.container.plotOptions.Line;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;
import org.highcharts.intf.HighChartsProviderInterface;

public class MyProvider implements HighChartsProviderInterface{

	@Override
	public Series[] getSeries() {
		Series series1 = new Series();
		series1.setName("Tokyo");
		series1.setData(new double[]{7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6});
		Series series2 = new Series();
		series2.setName("New York");
		series2.setData(new double[]{-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5});
		Series series3 = new Series();
		series3.setName("Berlin");
		series3.setData(new double[]{-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0});
		Series series4 = new Series();
		series4.setName("London");
		series4.setData(new double[]{3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8});
		return new Series[]{series1, series2, series3, series4};
	}

	@Override
	public Chart getChart() {
		Chart chart = new Chart();
		chart.setType(new Line().simple());
		return chart;
	}

	@Override
	public Title getTitle() {
		Title title = new Title();
		title.setText("大标题");
		return title;
	}

	@Override
	public SubTitle getSubTitle() {
		SubTitle subTitle = new SubTitle();
		subTitle.setText("副标题");
		return subTitle;
	}

	@Override
	public XAxis getXAxis() {
		XAxis xAxis = new XAxis();
		xAxis.setCategories(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
		return xAxis;
	}

	@Override
	public YAxis getYAxis() {
		YAxis yAxis = new YAxis();
		AxisTitle title = new AxisTitle();
		title.setText("Y轴描述");
		yAxis.setTitle(title);
		return yAxis;
	}

	@Override
	public Tooltip getTooltip() {
		Tooltip tooltip = new Tooltip();
		tooltip.setValueSuffix("好像是单位");
		return tooltip;
	}

	@Override
	public Legend getLegend() {
		Legend legend = new Legend();
		legend.setLayout(Layout.Vertical);
		legend.setAlign(Align.Center);
		legend.setVerticalAlign(Align.Bottom);
		legend.setBorderWidth(2);
		return legend;
	}



	
}
