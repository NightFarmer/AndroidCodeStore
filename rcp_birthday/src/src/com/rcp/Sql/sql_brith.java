package src.com.rcp.Sql;

import java.util.ArrayList;
import java.util.List;

import rcp.com.Item.colleatItem;
import rcp.com.src.volues.sqlVolue;
import src.com.rcp.day.brith_ListItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * 
 * 
 * @author toshiba
 * 
 */
public class sql_brith {
	/** 数据库操作对象 **/
	public SQLiteDatabase db;

	private MySql mySql;

	public sql_brith(Context context) {
		mySql = MySql.getInstance(context);
		db = mySql.getWritableDatabase();
	}

	/***
	 * 
	 * 查询
	 * 
	 * @return
	 */
	public List<colleatItem> qure() {

		List<colleatItem> list = new ArrayList<colleatItem>();
		Cursor cursor = db.query(sqlVolue.TABLE_SMS_name, null, null, null,
				null, null, null);

		cursor.moveToPosition(0);
		while (true) {
			if (cursor.isAfterLast()) {
				break;
			}
			colleatItem item = new colleatItem();
			item.setId(cursor.getInt(0));
			item.setInfo(cursor.getString(1));
			list.add(item);
			cursor.moveToNext();
		}
		return list;
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {

		String sql = " delete from " + sqlVolue.TABLE_brith_name
				+ " where _id=" + id;
		// db.delete(sqlVolue.TABLE_brith_name, "_id=?", new String[]{id+""});
		db.execSQL(sql);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param id
	 */
	public void delate_sms(int id) {
		String sql = " delete from " + sqlVolue.TABLE_SMS_name + " where _id="
				+ id;
		// db.delete(sqlVolue.TABLE_brith_name, "_id=?", new String[]{id+""});
		db.execSQL(sql);
	}

	public int insert_sms(String str) {
		// String
		// insert="insert into "+sqlVolue.TABLE_SMS_name+"("+sqlVolue.TABLE_brith_name+")values('"+str+"')";
		// db.execSQL(insert);

		ContentValues contentValues = new ContentValues();
		contentValues.put(sqlVolue.SMS_info, str);
		long id = db.insert(sqlVolue.TABLE_SMS_name, null, contentValues);

		return (int) id;
	}


	/**
	 * 修改数据
	 * 
	 * @param data
	 * @param id
	 */
	public void update(brith_ListItem data, String id) {
		ContentValues values = new ContentValues();

		values.put(sqlVolue.brithPer_name, data.getBrithPer_name());
		values.put(sqlVolue.brithPer_age, data.getBrithPer_age());
		values.put(sqlVolue.brithPer_sex, data.getBrithPer_sex());
		values.put(sqlVolue.brithPer_photo, data.getBrithPer_photo());
		values.put(sqlVolue.gregorianYear, data.getGregorianYear());
		values.put(sqlVolue.gregorianMouth, data.getGregorianMouth());
		values.put(sqlVolue.gregorianDate, data.getGregorianDate());
		values.put(sqlVolue.brithPer_phone, data.getBrithPer_phone());
		values.put(sqlVolue.brithPer_constellation,
				data.getBrithPer_constellation());
		values.put(sqlVolue.brithPer_beizhuInfo, data.getBrithPer_beizhuInfo());
		values.put(sqlVolue.brithPer_animals, data.getBrithPer_animals());

		db.update(sqlVolue.TABLE_brith_name, values, "_id=?",
				new String[] { id });
	}

	/***
	 * 
	 * 插入数据
	 * 
	 * @param data
	 */
	public void insert(brith_ListItem data) {

		String name = data.getBrithPer_name();
		int age = data.getBrithPer_age();
		String sex = data.getBrithPer_sex();
		String photo = data.getBrithPer_photo();
		int year = data.getGregorianYear();
		int mouth = data.getGregorianMouth();
		int date = data.getGregorianDate();
		String animals = data.getBrithPer_animals();
		String phone = data.getBrithPer_phone();
		String constellation = data.getBrithPer_constellation();
		String beizhuInfo = data.getBrithPer_beizhuInfo();
		// mytable(name,address) values('xiaoming','wuhan')
		// 姓名 年龄 性别 头像 年 月 日 电话 生肖 星坐
		String sql = "insert into " + sqlVolue.TABLE_brith_name + "("
				+ sqlVolue.brithPer_name + "," + sqlVolue.brithPer_age + ","
				+ sqlVolue.brithPer_sex + "," + sqlVolue.brithPer_photo + ","
				+ sqlVolue.gregorianYear + "," + sqlVolue.gregorianMouth + ","
				+ sqlVolue.gregorianDate + "," + sqlVolue.brithPer_phone + ","
				+ sqlVolue.brithPer_animals + ","
				+ sqlVolue.brithPer_constellation + ","
				+ sqlVolue.brithPer_beizhuInfo + ")values('" + name + "','"
				+ age + "','" + sex + "','" + photo + "','" + year + "','"
				+ mouth + "','" + date + "','" + phone + "','" + animals
				+ "','" + constellation + "','" + beizhuInfo + "')";
		db.execSQL(sql);
		// ContentValues contentValues = new ContentValues();
		// contentValues.put(sqlVolue.brithPer_name, name);
		// contentValues.put(sqlVolue.brithPer_age, age);
		// contentValues.put(sqlVolue.brithPer_sex, sex);
		// contentValues.put(sqlVolue.brithPer_photo, photo);
		// contentValues.put(sqlVolue.gregorianYear, year);
		// contentValues.put(sqlVolue.gregorianMouth, mouth);
		// contentValues.put(sqlVolue.gregorianDate, date);
		// contentValues.put(sqlVolue.brithPer_phone, phone);
		// contentValues.put(sqlVolue.brithPer_animals, animals);
		// contentValues.put(sqlVolue.brithPer_constellation, constellation);
		// long id = sqLiteDatabase.insert(sqlVolue.TABLE_brith_name, null,
		// contentValues);

		System.out.println("成功=================");
	}

	public void closeSQl() {
		db.close();
	}

}
