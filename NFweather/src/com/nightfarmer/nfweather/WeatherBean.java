package com.nightfarmer.nfweather;

public class WeatherBean {
	public Weatherinfo weatherinfo;

	public Weatherinfo getWeatherinfo() {
		return weatherinfo;
	}

	public void setWeatherinfo(Weatherinfo weatherinfo) {
		this.weatherinfo = weatherinfo;
	}
	
	@Override
	public String toString() {
		return "天气信息：\n"+weatherinfo;
	}
}
