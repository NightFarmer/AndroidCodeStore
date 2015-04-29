package cn.com.jdsc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class MainActivity extends FragmentActivity implements OnClickListener{
	public static final int TAB_HOME = 0;
	public static final int TAB_CATAGORY = 1;
	public static final int TAB_CAR = 2;
	public static final int TAB_BUY = 3;
	public static final int TAB_MORE = 4;

	private ViewPager viewPager;
	private RadioButton main_tab_home, main_tab_catagory, main_tab_car,
			main_tab_buy, main_tab_more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentmain);
		initView();
		addListener();
	}

	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		main_tab_home = (RadioButton) findViewById(R.id.main_tab_home);
		main_tab_catagory = (RadioButton) findViewById(R.id.main_tab_catagory);
		main_tab_car = (RadioButton) findViewById(R.id.main_tab_car);
		main_tab_buy = (RadioButton) findViewById(R.id.main_tab_buy);
		main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);
		main_tab_home.setOnClickListener(this);
		main_tab_catagory.setOnClickListener(this);
		main_tab_car.setOnClickListener(this);
		main_tab_buy.setOnClickListener(this);
		main_tab_more.setOnClickListener(this);
		
		FragmentAdapter adapter = new FragmentAdapter(
				getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}

	private void addListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int id) {
				switch (id) {
				case TAB_HOME:
					main_tab_home.setChecked(true);
					break;
				case TAB_CATAGORY:
					main_tab_catagory.setChecked(true);
					break;
				case TAB_CAR:
					main_tab_car.setChecked(true);
					break;
				case TAB_BUY:
					main_tab_buy.setChecked(true);
					break;
				case TAB_MORE:
					main_tab_more.setChecked(true);
					break;

				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_tab_home:
			viewPager.setCurrentItem(TAB_HOME);
			break;
		case R.id.main_tab_catagory:
			viewPager.setCurrentItem(TAB_CATAGORY);
			break;
		case R.id.main_tab_car:
			viewPager.setCurrentItem(TAB_CAR);
			break;
		case R.id.main_tab_buy:
			viewPager.setCurrentItem(TAB_BUY);
			break;
		case R.id.main_tab_more:
			viewPager.setCurrentItem(TAB_MORE);
			break;

		default:
			break;
		}		
	}
}
