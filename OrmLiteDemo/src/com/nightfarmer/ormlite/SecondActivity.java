package com.nightfarmer.ormlite;

import android.os.Bundle;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.nightfarmer.ormlite.entity.Dept;
import com.nightfarmer.ormlite.utile.DatabaseHelper;

public class SecondActivity extends OrmLiteBaseActivity<DatabaseHelper>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DatabaseHelper helper2 = getHelper();
		RuntimeExceptionDao<Dept, Integer> deptDao = helper2.getDataDao(Dept.class);
//		deptDao.
		for (int i = 0; i < 5; i++) {
			Dept user = new Dept();
			user.setName("name" + i);
			deptDao.createIfNotExists(user);
		}
//		List<Dept> users = deptDao.queryForAll();
//		for (Dept user : users) {
//			mTextView.append(user.toString());
//		}
	}
	
	
}
