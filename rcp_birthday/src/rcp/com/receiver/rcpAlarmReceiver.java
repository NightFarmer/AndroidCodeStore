package rcp.com.receiver;

import src.com.rcp.day.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class rcpAlarmReceiver extends BroadcastReceiver {

	private Notification notification;

	private NotificationManager notificationManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("收到广播");
		// Intent it = new Intent(context, AlarmActivity.class);
		// it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// context.startActivity(it);
		Log.e("", "--------------------");

		// notificationManager = (NotificationManager) context
		// .getSystemService(Context.NOTIFICATION_SERVICE);
		//
		// notification = new Notification(R.drawable.ic_launcher, "广播",
		// System.currentTimeMillis());
		//
		// Intent intent2 = new Intent(context, ac.class);
		//
		// PendingIntent pi = PendingIntent.getActivity(context, 0, intent2, 0);
		//
		// notification.setLatestEventInfo(context, "你好", "0000000000", pi);
		//
		// notificationManager.notify(0, notification);

		MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.celesta2);
		Toast.makeText(context, "短信已经发送成功", Toast.LENGTH_LONG).show();
		mediaPlayer.start();// 开始 ;
		// sendMsg(intent.getStringExtra("phone"),intent.getStringExtra("sms"));

		// 收到广播后启动Activity,简单起见，直接就跳到了设置alarm的Activity
		// intent必须加上Intent.FLAG_ACTIVITY_NEW_TASK flag

	}

	private void sendMsg(String number, String message) {
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(number, null, message, null, null);

	}
}