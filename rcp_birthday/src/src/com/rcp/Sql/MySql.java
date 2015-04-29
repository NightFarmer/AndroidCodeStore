package src.com.rcp.Sql;

import rcp.com.src.volues.sqlVolue;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySql extends SQLiteOpenHelper {

	private static MySql mInstance = null;

	// 姓名 年龄 性别 头像 年 月 日 电话 生肖 星坐AUTOINCREMENT
	private static final String create = "Create Table "
			+ sqlVolue.TABLE_brith_name + "( _id integer primary key,"
			+ sqlVolue.brithPer_name + " text," + sqlVolue.brithPer_age
			+ " integer," + sqlVolue.brithPer_sex + " text,"
			+ sqlVolue.brithPer_photo + " text," + sqlVolue.gregorianYear
			+ " integer," + sqlVolue.gregorianMouth + " integer,"
			+ sqlVolue.gregorianDate + " integer," + sqlVolue.brithPer_phone
			+ " text," + sqlVolue.brithPer_animals + " text,"
			+ sqlVolue.brithPer_constellation + " text,"
			+ sqlVolue.brithPer_beizhuInfo + " text)";

	private static final String create1 = "Create Table "
			+ sqlVolue.TABLE_SMS_name + "( _id integer primary key,"
			+ sqlVolue.SMS_info + " text)";

	public MySql(Context context) {
		super(context, "rcpBrithday.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	/** 单例模式 **/
	static synchronized MySql getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new MySql(context);
		}
		return mInstance;
	}

	public void onCreate(SQLiteDatabase db) {

		db.execSQL(create);
		db.execSQL(create1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
