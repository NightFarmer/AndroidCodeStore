package org.highcharts;

import org.highcharts.container.HC_Container;

import com.google.gson.Gson;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class HighChartsJSInterface {

	private Context context;
	private HC_Container container;

	public HighChartsJSInterface(Context context, HC_Container container) {
		this.context = context;
		this.container = container;
	}
	
	@JavascriptInterface
	public String getContainerData(){
		Gson gson = new Gson();
		String json = gson.toJson(container);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println(json);
		return json;
	}
}
