package com.nightfarmer.ormlite;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.nightfarmer.ormlite.entity.User;
import com.nightfarmer.ormlite.utile.DatabaseHelper;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private TextView mTextView;
	private RuntimeExceptionDao<User, Integer> mUserDAO;

	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mUserDAO = getHelper().getUserDataDao();

		mTextView = (TextView) findViewById(R.id.message);

		deleteAll();
		mTextView.append("\n#######Begin to Insert#########\n");
		insertTest();
		display();
		mTextView.append("\n#######Begin to Update#########\n");
		user.setUsername("update");
		update(user);
		display();
		mTextView.append("\n#######Begin to Delete#########\n");
		delete("name2");
		display();
		mTextView.append("\n#######Begin to Search#########\n");
		mTextView.append(search("name1").toString());
		
		//如不想继承ROM的Activity则可以如此获得helper对象
		//OpenHelperManager中如果如果存在helper的实例化对象，且类型匹配，则返回，实例计数器+1，
		//若类型不匹配，则抛异常，
		//若不存在则创建一个，并实例计数器+1
//		DatabaseHelper helper2 = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		//OpenHelperManager中的实例计数器-1
		//若-1之后计数器为0，则把OpenHelperManager的helper实例化对象置null
		//若-1之后计数器大于0，则不处理
		//若-1之后小于0，则抛异常
//		OpenHelperManager.releaseHelper();
	}

	/**
	 * 插入值测试
	 */
	private void insertTest() {
		for (int i = 0; i < 5; i++) {
			user = new User();
			user.setUsername("name" + i);
			user.setPassword("test_pass " + i);
			mUserDAO.createIfNotExists(user);
		}
	}

	/**
	 * 更新
	 * 
	 * @param user
	 *            待更新的user
	 */
	private void update(User user) {
		mUserDAO.createOrUpdate(user);
		// mUserDAO.update(user);
	}

	/**
	 * 按照指定的id 与 username 删除一项
	 * 
	 * @param id
	 * @param username
	 * @return 删除成功返回true ，失败返回false
	 */
	private int delete(String username) {
		try {
			// 删除指定的信息，类似delete User where 'id' = id ;
			DeleteBuilder<User, Integer> deleteBuilder = mUserDAO
					.deleteBuilder();
			deleteBuilder.where().eq("username", username);

			return deleteBuilder.delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 按照id查询user
	 * 
	 * @param id
	 * @return
	 */
	private User search(String username) {
		try {
			// 查询的query 返回值是一个列表
			// 类似 select * from User where 'username' = username;
			List<User> users = mUserDAO.queryBuilder().where()
					.eq("username", username).query();
			if (users.size() > 0)
				return users.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除全部
	 */
	private void deleteAll() {
		mUserDAO.delete(queryAll());
	}

	/**
	 * 查询所有的
	 */
	private List<User> queryAll() {
		List<User> users = mUserDAO.queryForAll();
		return users;
	}

	/**
	 * 显示所有的
	 */
	private void display() {
		List<User> users = queryAll();
		for (User user : users) {
			mTextView.append(user.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
