package com.nightfarmer.nfweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nightfarmer.nfweather.jsonbean.City;
import com.nightfarmer.nfweather.jsonbean.Country;
import com.nightfarmer.nfweather.jsonbean.Province;

public class MainActivity extends Activity {
	private static final String[] pd1 = {};
	private static final String[] pd2 = {};
	private static final String[] dp3 = {};
	private Spinner sp1;
	private Spinner sp2;
	private Spinner sp3;
	private List<Province> provinceList;
	private List<City> cityList;
	private List<Country> countryList;
	private Handler handler = new Handler();
	private TextView tv;
	private boolean canGetWeather = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AssetManager assets = getAssets();
		Gson gson = new Gson();
		try {
			InputStream open = assets.open("area.txt");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(open));
			String lineReaded = "";
			StringBuffer stringBuffer = new StringBuffer();
			while ((lineReaded = bufferedReader.readLine()) != null) {
				stringBuffer.append(lineReaded);
			}
//			TextView tv = (TextView) findViewById(R.id.heheda);
			// tv.setText(stringBuffer);
			provinceList = gson.fromJson(stringBuffer.toString(), new TypeToken<List<Province>>(){}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}

		sp1 = (Spinner) findViewById(R.id.Spinner01);
		sp2 = (Spinner) findViewById(R.id.Spinner02);
		sp3 = (Spinner) findViewById(R.id.Spinner03);
		ArrayList<String> arrayList = new ArrayList<String>();
		for (Province pro : provinceList) {
			arrayList.add(pro.title);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arrayList.toArray(new String[]{}));
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		sp1.setAdapter(adapter);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Province province = provinceList.get(position);
				cityList = province.cityList;
				ArrayList<String> arrayList = new ArrayList<String>();
				for (City city : cityList) {
					arrayList.add(city.title);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_item, arrayList.toArray(new String[]{}));
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp2.setAdapter(adapter);
				canGetWeather = false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				City city = cityList.get(position);
				countryList = city.contrylist;
				ArrayList<String> arrayList = new ArrayList<String>();
				for (Country country : countryList) {
					arrayList.add(country.title);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_item, arrayList.toArray(new String[]{}));
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				sp3.setAdapter(adapter);
				canGetWeather = false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				canGetWeather = false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		tv = (TextView) findViewById(R.id.heheda);
		Button bt = (Button) findViewById(R.id.getWeatherBT);
		bt.setOnClickListener(new OnClickListener() {
			private Runnable runnable;

			@Override
			public void onClick(View v) {
				canGetWeather = true;
				runnable = new Runnable() {
					
					@Override
					public void run() {
						if (!canGetWeather) {
							return;
						}
						URL url;
						try {
							String title = null;
							String code = null;
							if (countryList!=null && countryList.size()!=0) {
								Country country = countryList.get(sp3.getSelectedItemPosition());
								title = country.title;
								code = country.url;
							}else {
								City city = cityList.get(sp2.getSelectedItemPosition());
								title = city.title;
								code = city.url;
							}
							final String code_url = code; 
							final String str = title;
							handler.post(new Runnable() {
								@Override
								public void run() {
//									Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
									tv.setText("正在获取"+str+"的天气信息...");
								}
							});
							
							url = new URL("http://m.weather.com.cn/atad/"+code_url+".html");
							URLConnection connection = url.openConnection();
							InputStream inputStream = connection.getInputStream();
							BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
							String readLine = "";
							final StringBuffer stringBuffer = new StringBuffer();
							while ((readLine = bufferedReader.readLine())!=null) {
								stringBuffer.append(readLine);
							}
							Gson gson2 = new Gson();
							final WeatherBean fromJson = gson2.fromJson(stringBuffer.toString(), WeatherBean.class);
							handler.post(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									tv.setText(fromJson.toString());
//									tv.setText(stringBuffer.toString());
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							new Thread(runnable).start();
						}
					}
				};
				Thread getWeather = new Thread(runnable);
				getWeather.start();
			}
		});
		
	}

	@Override
	public void onBackPressed() {
		canGetWeather = false;
		super.onBackPressed();
	}
}
