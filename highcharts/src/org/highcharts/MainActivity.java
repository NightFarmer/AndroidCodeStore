package org.highcharts;

import org.highcharts.demo.MyProvider;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyProvider myProvider = new MyProvider();
		HighChartsView hc = (HighChartsView) findViewById(R.id.highchart);
		hc.setProvider(myProvider);
		hc.init();
	}

}
