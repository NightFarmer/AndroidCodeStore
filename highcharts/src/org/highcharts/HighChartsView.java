package org.highcharts;

import org.highcharts.container.Align;
import org.highcharts.container.HC_Container;
import org.highcharts.container.axis.AxisTitle;
import org.highcharts.container.axis.PlotLine;
import org.highcharts.container.axis.XAxis;
import org.highcharts.container.axis.YAxis;
import org.highcharts.container.chart.Chart;
import org.highcharts.container.legend.Legend;
import org.highcharts.container.legend.Legend.Layout;
import org.highcharts.container.plotOptions.Column;
import org.highcharts.container.series.Series;
import org.highcharts.container.title.SubTitle;
import org.highcharts.container.title.Title;
import org.highcharts.container.tooltip.Tooltip;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HighChartsView extends FrameLayout{

	private Handler handler = new Handler();
	private TextView yoy;
	private HC_Container container;
	private WebView wb;

	public HighChartsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public HighChartsView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HighChartsView(Context context) {
		this(context, null);
	}

	@Override
	protected void onFinishInflate() {
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		View highchartsview = layoutInflater.inflate(R.layout.highchartsview, null);
		addView(highchartsview);
		wb = (WebView) findViewById(R.id.highchartview_wb);
		WebSettings settings = wb.getSettings();
		settings.setJavaScriptEnabled(true);
		wb.loadUrl("file:///android_asset/highcharts.html");
		
		yoy = (TextView)findViewById(R.id.highchartview_loadingtext);
		wb.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress==100) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							yoy.setVisibility(View.GONE);
						}
					});
				}
				super.onProgressChanged(view, newProgress);
			}
		});
		
	}
	
	public void setContainer(HC_Container container){
		this.container = container;
	}

	
	public void setProvider(HighChartsProvider provider){
		if (this.container == null) {
			this.container = new HC_Container();
		}
		container.setChart(provider.getChart());
		container.setLegend(provider.getLegend());
		container.setSeries(provider.getSeries());
		container.setSubtitle(provider.getSubTitle());
		container.setTitle(provider.getTitle());
		container.setTooltip(provider.getTooltip());
		container.setxAxis(provider.getXAxis());
		container.setyAxis(provider.getYAxis());
	}
	
	public void init(){
		wb.addJavascriptInterface(new HighChartsJSInterface(getContext(), container), "HighChartsJSInterface");
	}
	
}
