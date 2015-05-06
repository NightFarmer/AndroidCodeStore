package com.nightfarmer.eip_x.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.adapter.TxlListViewAdapter;
import com.nightfarmer.eip_x.base.FragmentWithTitle;
import com.nightfarmer.eip_x.beans.Person;
import com.nightfarmer.eip_x.utils.CharacterParser;
import com.nightfarmer.eip_x.utils.TitleBar;

public class FragmentTXL extends FragmentWithTitle{

	public FragmentTXL() {
		super(R.layout.layout_txl, "通讯录");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTitleBar(TitleBar titleBar) {
		// TODO Auto-generated method stub
		super.initTitleBar(titleBar);
//		titleBar.showBackButton();
		titleBar.showOpitonButton("呵呵");
	}
	
	@Override
	protected void initMainBodyContent(View layoutMainbodyContent) {
		// TODO Auto-generated method stub
		ListView listView = (ListView) findViewById(R.id.txl_listView);
		List<Person> personList = new ArrayList<Person>();
		Person person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		
		Person person5 = new Person();
		person5.setName("宝宝");
		personList.add(person5);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		
		Person person2 = new Person();
		person2.setName("李四");
		personList.add(person2);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		
		Person person3 = new Person();
		person3.setName("王五");
		personList.add(person3);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		
		Person person4 = new Person();
		person4.setName("赵四");
		personList.add(person4);
		person = new Person();
		person.setName("张三");
		personList.add(person);
		
		for (Person person1 : personList) {
			String name = person1.getName();
			CharacterParser instance = CharacterParser.getInstance();
			String selling = instance.getSelling(name);
			person1.setSortLetters(selling.toUpperCase());
		}
		Collections.sort(personList, new Comparator<Person>() {

			@Override
			public int compare(Person lhs, Person rhs) {
				String sortLetters1 = lhs.getSortLetters();
				String sortLetters2 = rhs.getSortLetters();
				return sortLetters1.compareTo(sortLetters2);
			}
		});
		TxlListViewAdapter adapter = new TxlListViewAdapter(getContext(), personList);
		listView.setAdapter(adapter);
	}
}
