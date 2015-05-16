package test1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

 

public class  TestClass {
	public  static <T extends TestClass, E extends Number, G extends Object> T asList(Class<T> class1, E e, G g) {
	    return null;
	}
	
	public static <TFacade> TFacade find( 
	        Class<TFacade> facadeClass){
		try {
			TFacade newInstance = facadeClass.newInstance();
			Field[] declaredFields = facadeClass.getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				field.set(newInstance, "zhangsan");
			}
			return newInstance;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static class Person{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public Person() {
			System.out.println("1...");
		}
	}
}
