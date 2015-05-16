package test1;

import test1.TestClass.Person;

public class Test2 {
	public static void main(String[] args) {
		
		TestClass.asList(TestClass.class, 2, "");
		 Person find = TestClass.find(Person.class);
		 System.out.println(find.getName());
		 
		
	}
}
