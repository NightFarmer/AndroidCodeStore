package src.com.rcp.photo;

import rcp.com.src.imperface.OnIndex;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Index extends View {

	/** 索引数据 */
	private String text[] = new String[26];

	/** 画笔样式对象 */
	private Paint paint;

	/** 索引字体间的间距 */
	private int separation;

	/* 索引滑动监听 */
	private OnIndex onIndex;

	public Index(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Index(Context context) {
		super(context);
		init();

	}

	public void init() {
		for (int i = 0; i < text.length; i++) {
			text[i] = (char) (65 + i) + "";
		}

		paint = new Paint();

		paint.setTextSize(20.0f);
		paint.setColor(0xff000000);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:

			float y = event.getY();

			int index = (int) (y / separation);

			if (index >= text.length) {
				index = text.length - 1;
			}

			String str = text[index];

			// System.out.println("---->"+str);
			if (onIndex != null) {
				onIndex.OnIndexSelect(str);
			}

			break;
		case MotionEvent.ACTION_UP:

			if (onIndex != null) {
				onIndex.OnIndexUp();
			}
			break;
		}
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		separation = this.getHeight() / 26;

		int width = getWidth();

		for (int i = 0; i < text.length; i++) {
			canvas.drawText(text[i], width - getFontWidth(text[i]) >> 1,
					separation * (i + 1), paint);
		}
	}

	/**
	 * 
	 * 得到字体的宽度
	 * 
	 * @param str
	 * @return
	 */
	public int getFontWidth(String str) {
		Rect rect = new Rect();

		paint.getTextBounds(str, 0, str.length(), rect);

		return rect.width();
	}

	public void setOnIndex(OnIndex onIndex) {
		this.onIndex = onIndex;
	}

}
