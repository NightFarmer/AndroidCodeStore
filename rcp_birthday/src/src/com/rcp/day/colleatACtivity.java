package src.com.rcp.day;

import java.util.List;

import rcp.com.Item.colleatItem;
import rcp.com.other.ActivityMeg;
import rcp.src.com.adapter.mycolleatAdapter;
import src.com.rcp.Sql.sql_brith;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 *  ’≤ÿ
 * 
 * @author toshiba
 * 
 */
public class colleatACtivity extends Activity {

	/**  ’≤ÿ */
	private ListView colleat;

	private List<colleatItem> info;

	private sql_brith db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.smslist);
		ActivityMeg.getInstance().addActivity(this);
		datainit();
		Log.e("iiiiiiiiiiii", info.size() + "");
		viewinit();
	}

	public void viewinit() {
		colleat = (ListView) this.findViewById(R.id.colleat_list);
		colleat.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Intent intent = new Intent(colleatACtivity.this,
						rcp_msg_wish.class);
				intent.putExtra("colleatInfo", info.get(position).getInfo());
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//				colleatACtivity.this.startActivity(intent);
				colleatACtivity.this.setResult(rcp_msg_wish.SEND_SEARCH_SMS, intent);
				colleatACtivity.this.finish();
			}
		});
		mycolleatAdapter colleatAdapter = new mycolleatAdapter(info, this, db);
		colleat.setAdapter(colleatAdapter);
	}

	public void datainit() {
		db = new sql_brith(this);

		info = db.qure();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		finish();
	}
	
	

}
