package com.nightfarmer.dragformore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class DragMainLayout extends RelativeLayout implements Dragable {
	private State state = State.NORMAL;
	private LoadedListener listener;
	private ListView listview;
	private Context context;

	public DragMainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public DragMainLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public DragMainLayout(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		listview = (ListView) findViewById(R.id.mylistview);
		String[] from = { "Text", "Button" };
		int[] to = { R.id.text, R.id.button };
		List<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		for (int i = 0; i < 20; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("Text", "Text" + i);
			m.put("Button", "Button" + i);
			list.add(m);
		}
		listview.setAdapter(new SimpleAdapter(context, list, R.layout.listitem,
				from, to));
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		state = Dragable.State.LOADING;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (State.LOADING == state) {
					state = Dragable.State.NORMAL;
					listener.loaded(true);
				}
			}
		}).start();
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setLoadedListener(LoadedListener l) {
		listener = l;
	}

	@Override
	public void cancle() {
		state = State.NORMAL;
	}

	@Override
	public boolean canDragDown() {
		return true;
	}

	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
//		return super.onInterceptTouchEvent(ev);
//		return true;
//		if (listview.getCount() == 0)
//		{
//			// 没有item的时候也可以下拉刷新
//			return true;
//		} else if (listview.getFirstVisiblePosition() == 0
//				&& getChildAt(0).getTop() >= 0)
//		{
//			// 滑到ListView的顶部了
//			return true;
//		} else
			return super.onInterceptTouchEvent(ev);
	}
}
