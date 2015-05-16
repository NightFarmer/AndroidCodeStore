import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Filetest {
	public static void main(String[] args) {
		File file = new File("/area.txt");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str=bufferedReader.readLine())!=null) {
				stringBuffer.append(str);
			}
			String string = stringBuffer.toString();
			System.out.println(string);
			Gson gson = new Gson();
			List<Province> fromJson = gson.fromJson(string, new TypeToken<List<Province>>(){}.getType());
			System.out.println(fromJson);
			fileInputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
