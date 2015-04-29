//package rcp.com.Item;
//
//import java.util.List;
//
//import rcp.src.com.adapter.brithPerlistItem;
//import rcp.src.com.adapter.brithPerlistviewAdapter;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//import android.view.animation.Animation;
//import android.view.animation.Animation.AnimationListener;
//import android.view.animation.AnimationUtils;
//import android.widget.ListView;
//
//
///**
// * @version 创建时间：2012-11-20 下午3:43:09 类说明
// */
//public class deleteItem implements OnTouchListener {
//	private List<brithPerlistItem> list;// 传进来的list数据
//	private brithPerlistviewAdapter adapter;// 传进来的列表适配器
//
//	public deleteItem(List<brithPerlistItem> list,
//			brithPerlistviewAdapter adapter) {
//		this.list = list;
//		this.adapter = adapter;
//	}
//
//	float x = 0, y = 0;
//	int sum;
//	int temp;
//	boolean mFlag = false;
//	float alpha = 1.0f;
//
//	public boolean onTouch(View view, MotionEvent event) {
//		float upx = 0, upy = 0, movex = 0;
//		int position = 0;
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			x = event.getX();
//			y = event.getY();
//
//		}
//		position = ((ListView) view).pointToPosition((int) x, (int) y);
//		if (event.getAction() == MotionEvent.ACTION_MOVE) {
//			movex = event.getX();
//			if (mFlag) {
//				if (temp > movex) {
//					((ListView) view).getChildAt(position).scrollBy((int) (3),
//							0);
//					alpha += 0.01f;
//					((ListView) view).getChildAt(position);
//					sum -= 3;
//				}
//			}
//			if ((x - movex) < 0) {
//				if (Math.abs(x - movex) > ((ListView) view)
//						.getChildAt(position).getWidth() * 0.8) {
//					View v = ((ListView) view).getChildAt(position);
//					deleteListItem(v, position);
//				}
//				((ListView) view).getChildAt(position).scrollBy((int) (-3), 0);
//				alpha -= 0.01f;
//				((ListView) view).getChildAt(position).onSetAlpha(alpha);
//				sum += 3;
//				temp = (int) movex;
//				mFlag = true;
//			}
//		}
//		if (event.getAction() == MotionEvent.ACTION_UP) {
//			upx = event.getX();
//			upy = event.getY();
//			int position1 = ((ListView) view).pointToPosition((int) x, (int) y);
//			int position2 = ((ListView) view).pointToPosition((int) upx,
//					(int) upy);
//			if (position1 == position2 && Math.abs(x - upx) > 200) {
//				if (x < upx) {
//					View v = ((ListView) view).getChildAt(position);
//					deleteListItem(v, position);
//					alpha = 1f;
//					((ListView) view).getChildAt(position).scrollBy((int) sum,
//							0);
//					((ListView) view).getChildAt(position1).
//				}
//			} else {
//				alpha = 1f;
//				((ListView) view).getChildAt(position).onSetAlpha(alpha);
//				((ListView) view).getChildAt(position).clearFocus();
//				((ListView) view).getChildAt(position).scrollBy((int) sum, 0);
//				sum = 0;
//			}
//			position = 0;
//			sum = 0;
//		}
//		return false;
//	}
//
//	protected void deleteListItem(View rowView, final int positon) {
//		Animation animation = (Animation) AnimationUtils.loadAnimation(
//				rowView.getContext(),Animation.RESTART );
//		animation.setAnimationListener(new AnimationListener() {
//			public void onAnimationStart(Animation animation) {
//			}
//
//			public void onAnimationRepeat(Animation animation) {
//			}
//
//			public void onAnimationEnd(Animation animation) {
//				System.out.println("list.size--->>>" + list.size());
//				list.remove(positon);
//				adapter.notifyDataSetChanged();
//				animation.cancel();
//			}
//		});
//		rowView.startAnimation(animation);
//	}
//}
