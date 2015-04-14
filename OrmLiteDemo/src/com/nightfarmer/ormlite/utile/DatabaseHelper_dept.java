package com.nightfarmer.ormlite.utile;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nightfarmer.ormlite.entity.Dept;

public class DatabaseHelper_dept extends OrmLiteSqliteOpenHelper{

	private static final String TAG = "DatabaseHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "HelloOrmlite.db";
    // 数据库version
    private static final int DATABASE_VERSION = 1;

    private Dao<Dept, Integer> deptDao = null;
    private RuntimeExceptionDao<Dept, Integer> deptRuntimeDao = null;

    public DatabaseHelper_dept(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // 可以用配置文件来生成 数据表，有点繁琐，不喜欢用
       // super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * @param context
     * @param databaseName
     * @param factory
     * @param databaseVersion
     */
    public DatabaseHelper_dept(Context context, String databaseName, CursorFactory factory, int databaseVersion)
    {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource)
    {
        try
        {
            //建立User表
            TableUtils.createTable(connectionSource, Dept.class);
            //初始化DAO
//            userDao = getUserDao();
//            userRuntimeDao = getUserDataDao();
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Dept.class, true);
        }
        catch (SQLException e)
        {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    private Dao<Dept, Integer> getDeptDao() throws SQLException
    {
        if (deptDao == null)
            deptDao = getDao(Dept.class);
        return deptDao;
    }

    public RuntimeExceptionDao<Dept, Integer> getDeptDataDao()
    {
        if (deptRuntimeDao == null)
        {
            deptRuntimeDao = getRuntimeExceptionDao(Dept.class);
        }
        return deptRuntimeDao;
    }
    
    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
        deptRuntimeDao = null;
    }
}
