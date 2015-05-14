package org.highcharts;

import org.highcharts.container.Align;
import org.highcharts.container.HC_Container;
import org.highcharts.container.axis.AxisTitle;
import org.highcharts.container.axis.PlotLine;
import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.legend.Legend.Layout;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView wb = (WebView) findViewById(R.id.wb);
		WebSettings settings = wb.getSettings();
		settings.setJavaScriptEnabled(true);
		wb.loadUrl("file:///android_asset/highcharts.html");
		HC_Container hc_Container = new HC_Container();
		Title title = new Title();
		title.setText("大标题");
		title.setX(-20);
		hc_Container.setTitle(title);
		
		SubTitle subTitle = new SubTitle();
		subTitle.setText("副标题");
		subTitle.setX(-20);
		hc_Container.setSubtitle(subTitle);
		
		XAxis xAxis = new XAxis();
		xAxis.setCategories(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
		hc_Container.setxAxis(xAxis);
		
		YAxis yAxis = new YAxis();
		AxisTitle axisTitle = new AxisTitle();
		axisTitle.setText("Temperature (°C)");
		yAxis.setTitle(axisTitle);
		PlotLine plotLine = new PlotLine();
		plotLine.setValue(0);
		plotLine.setWidth(1);
		plotLine.setColor("#808080");
		yAxis.setPlotLines(new PlotLine[]{plotLine});
		hc_Container.setyAxis(yAxis);
		
		Tooltip tooltip = new Tooltip();
		tooltip.setValueSuffix("°C");
		hc_Container.setTooltip(tooltip);
		
		Legend legend = new Legend();
		legend.setLayout(Layout.Vertical);
		legend.setAlign(Align.Right);
		legend.setVerticalAlign(Align.Middle);
		legend.setBorderWidth(0);
		hc_Container.setLegend(legend);
		
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
		hc_Container.setSeries(new Series[]{series1, series2, series3, series4});
		
		
		wb.addJavascriptInterface(new HighChartsJSInterface(this, hc_Container), "HighChartsJSInterface");
	}

}
