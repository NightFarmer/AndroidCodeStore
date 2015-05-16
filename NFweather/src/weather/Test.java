package weather;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

import com.google.gson.Gson;

public class Test {
	public static void main(String[] args) {
		try {
			while (true) {
				Thread.sleep(1000);
				URL url = new URL("http://m.weather.com.cn/atad/101180101.html");
				URLConnection connection = url.openConnection();
				InputStream inputStream = connection.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				String readLine = "";
				StringBuffer stringBuffer = new StringBuffer();
				while ((readLine = bufferedReader.readLine())!=null) {
					stringBuffer.append(readLine);
				}
				String string = stringBuffer.toString();
				Gson gson = new Gson();
				WeatherBean fromJson = null;
				try {
					fromJson = gson.fromJson(string, WeatherBean.class);
				} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
				}
				System.out.println(fromJson);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
