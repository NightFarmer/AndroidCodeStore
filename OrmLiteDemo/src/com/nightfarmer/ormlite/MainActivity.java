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
		
		//�粻��̳�ROM��Activity�������˻��helper����
		//OpenHelperManager������������helper��ʵ��������������ƥ�䣬�򷵻أ�ʵ��������+1��
		//�����Ͳ�ƥ�䣬�����쳣��
		//���������򴴽�һ������ʵ��������+1
//		DatabaseHelper helper2 = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		//OpenHelperManager�е�ʵ��������-1
		//��-1֮�������Ϊ0�����OpenHelperManager��helperʵ����������null
		//��-1֮�����������0���򲻴���
		//��-1֮��С��0�������쳣
//		OpenHelperManager.releaseHelper();
	}

	/**
	 * ����ֵ����
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
	 * ����
	 * 
	 * @param user
	 *            �����µ�user
	 */
	private void update(User user) {
		mUserDAO.createOrUpdate(user);
		// mUserDAO.update(user);
	}

	/**
	 * ����ָ����id �� username ɾ��һ��
	 * 
	 * @param id
	 * @param username
	 * @return ɾ���ɹ�����true ��ʧ�ܷ���false
	 */
	private int delete(String username) {
		try {
			// ɾ��ָ������Ϣ������delete User where 'id' = id ;
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
	 * ����id��ѯuser
	 * 
	 * @param id
	 * @return
	 */
	private User search(String username) {
		try {
			// ��ѯ��query ����ֵ��һ���б�
			// ���� select * from User where 'username' = username;
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
	 * ɾ��ȫ��
	 */
	private void deleteAll() {
		mUserDAO.delete(queryAll());
	}

	/**
	 * ��ѯ���е�
	 */
	private List<User> queryAll() {
		List<User> users = mUserDAO.queryForAll();
		return users;
	}

	/**
	 * ��ʾ���е�
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
