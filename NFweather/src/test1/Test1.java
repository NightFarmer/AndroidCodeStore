package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		Item[] strarray = new Item[]{new Item(9),new Item(19),new Item(11),new Item(2)};
		Item[] strarray2 = new Item[]{new Item(9),new Item(19),new Item(11),new Item(2)};
		System.out.println(Arrays.hashCode(strarray));
		System.out.println(Arrays.hashCode(strarray2));
		System.out.println(Arrays.equals(strarray, strarray2));
		//Arrays.sort(strarray,0,4);
//		Arrays.sort(strarray, new Comparator<Item>() {
//
//			@Override
//			public int compare(Item o1, Item o2) {
//				return o1.num-o2.num;
//			}
//		});
//		int binarySearch = Arrays.binarySearch(strarray, new Item(11), new Comparator<Item>() {
//
//			@Override
//			public int compare(Item o1, Item o2) {
//				// TODO Auto-generated method stub
//				return o1.num-o2.num;
//			}
//		});
//		System.out.println(binarySearch);
		Arrays.fill(strarray, new Item(1));
		strarray[2].num=2;
		Item[] copyOf = Arrays.copyOf(strarray, 5);
		copyOf[2]=new Item(4);
		List<Item> asList = Arrays.asList(strarray);
		List<Item> asList2 = Arrays.asList(copyOf);
		System.out.println(asList);
		System.out.println(asList2);
		System.out.println(Arrays.toString(copyOf));
	}
	
	static class Item /*implements Comparable<Item>*/{
		public int num;

		public Item(int num) {
			this.num = num;
		}

//		@Override
//		public int compareTo(Item o) {
//			return this.num-o.num;
//		}
		
		@Override
		public String toString() {
			return String.valueOf(num);
		}
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return this.num==((Item)obj).num;
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return num;
		}
	}
	
	
}
