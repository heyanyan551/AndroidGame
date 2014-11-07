package com.example.foolbirdgame.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.anjoyo.foolbirdgame.R;
import com.example.foolbirdgame.adaper.ViewPagerAdapter;
import com.example.foolbirdgame.frg.CategoryFrg;
import com.example.foolbirdgame.frg.CommunityFrg;
import com.example.foolbirdgame.frg.RankedFrg;
import com.example.foolbirdgame.frg.RecommendFrg;
import com.example.foolbirdgame.frg.SpecialSubjectFrg;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends FragmentActivity {

	private int mSetViewWieth;
	@ViewInject(R.id.viewpager)
	ViewPager viewpager;
	TextView personal_center;
	TextView message_center;
	TextView sign_in;
	TextView integral_exchange;
	TextView photo_share;
	TextView manager;
	RadioGroup radiogroup;
	@ViewInject(R.id.recommend)
	TextView recommend;
	@ViewInject(R.id.ranked)
	TextView ranked;
	@ViewInject(R.id.community)
	TextView community;
	@ViewInject(R.id.category)
	TextView category;
	@ViewInject(R.id.specialsubject)
	TextView specialsubject;
	@ViewInject(R.id.image_view_set)
	ImageView image_view_set;
	@ViewInject(R.id.recommend_right)
	TextView   recommend_right;
	@ViewInject(R.id.ranked_right)
	TextView   ranked_right;
	@ViewInject(R.id.category_right)
	TextView   category_right;
	@ViewInject(R.id.specialsubjiect_right)
	TextView   specialsubjiect_right;
	View setview;
	SlidingMenu menu;
    View  indicator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		WindowManager manager = (WindowManager) this
				.getSystemService(this.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		screenWidth=(dm.widthPixels);
		mSetViewWieth = screenWidth * 2 / 5;
		menu = new SlidingMenu(this);
		setview = LayoutInflater.from(this).inflate(R.layout.sideslip_menu,
				null);

		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset(mSetViewWieth);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		menu.setMenu(setview);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		initset();
		initViewPager();
		initradiobutton();
		 initIndicator();
	}

	private void initset() {
		personal_center = (TextView) setview.findViewById(R.id.personal_center);
		message_center = (TextView) setview.findViewById(R.id.message_center);
		sign_in = (TextView) setview.findViewById(R.id.sign_in);
		integral_exchange = (TextView) setview
				.findViewById(R.id.integral_exchange);
		photo_share = (TextView) setview.findViewById(R.id.photo_share);
		manager = (TextView) setview.findViewById(R.id.manager);
		personal_center.setOnClickListener(onClickListener);
		message_center.setOnClickListener(onClickListener);
		sign_in.setOnClickListener(onClickListener);
		photo_share.setOnClickListener(onClickListener);
		manager.setOnClickListener(onClickListener);
		integral_exchange.setOnClickListener(onClickListener);
		image_view_set.setOnClickListener(onClickListener);
	}

	private void initradiobutton() {
		recommend.setOnClickListener(onClickListener);
		ranked.setOnClickListener(onClickListener);
		specialsubject.setOnClickListener(onClickListener);
		community.setOnClickListener(onClickListener);
		category.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.category:
				viewpager.setCurrentItem(2, false);
				break;
			case R.id.community:
				viewpager.setCurrentItem(4, false);
				break;
			case R.id.ranked:
				viewpager.setCurrentItem(1, false);
				break;
			case R.id.recommend:
				viewpager.setCurrentItem(0, false);
				break;
			case R.id.specialsubject:
				viewpager.setCurrentItem(3, false);
				break;
			case R.id.personal_center:
				startActivity(new Intent(MainActivity.this,
						MyCenterActivity.class));
				break;
			case R.id.message_center:
				startActivity(new Intent(MainActivity.this,
						MessageCenterActivity.class));
				break;
			case R.id.sign_in:
				startActivity(new Intent(MainActivity.this,
						SignInActivity.class));
				break;
			case R.id.integral_exchange:
				startActivity(new Intent(MainActivity.this,
						IntegralExchangeActivity.class));
				break;
			case R.id.photo_share:
				startActivity(new Intent(MainActivity.this,
						PhotoShareActivity.class));
				break;
			case R.id.manager:
				startActivity(new Intent(MainActivity.this,
						ManagerActivity.class));
				break;
			case R.id.image_view_set:
				menu.toggle();
				break;
			}
		}
	};
	private ViewPagerAdapter pageradapter;
	private List<Fragment> fragments;
	private int screenWidth;
	private int tabWidth;

	private void initViewPager() {
		fragments = new ArrayList<Fragment>();

		fragments.add(new RecommendFrg());
		fragments.add(new RankedFrg());
		fragments.add(new CategoryFrg());
		fragments.add(new SpecialSubjectFrg());
		fragments.add(new CommunityFrg());
		pageradapter = new ViewPagerAdapter(getSupportFragmentManager(),
				fragments);
		viewpager.setAdapter(pageradapter);
		viewpager.setCurrentItem(0);
		recommend.setTextColor(getResources().getColor(R.color.white));
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			private int currentPage;
			private int leftLayout;
			private int rightLayout;

			@Override
			public void onPageSelected(int arg0) {
				currentPage = arg0;
				switch (arg0) {
				case 0:
					recommend.setTextColor(getResources().getColor(
							R.color.white));
					ranked.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					category.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					specialsubject.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					community.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					recommend_right.setTextColor(getResources().getColor(R.color.grey));
					ranked_right.setTextColor(getResources().getColor(R.color.grey));
					category_right.setTextColor(getResources().getColor(R.color.grey));
					specialsubjiect_right.setTextColor(getResources().getColor(R.color.grey));
					break;
				case 1:
					recommend.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					ranked.setTextColor(getResources().getColor(R.color.white));
					category.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					specialsubject.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					community.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					recommend_right.setTextColor(getResources().getColor(R.color.black));
					ranked_right.setTextColor(getResources().getColor(R.color.black));
					category_right.setTextColor(getResources().getColor(R.color.grey));
					specialsubjiect_right.setTextColor(getResources().getColor(R.color.grey));
					break;
				case 2:
					recommend.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					ranked.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					category.setTextColor(getResources()
							.getColor(R.color.white));
					specialsubject.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					community.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					recommend_right.setTextColor(getResources().getColor(R.color.grey));
					ranked_right.setTextColor(getResources().getColor(R.color.black));
					category_right.setTextColor(getResources().getColor(R.color.black));
					specialsubjiect_right.setTextColor(getResources().getColor(R.color.grey));
					break;
				case 3:
					recommend.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					ranked.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					category.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					specialsubject.setTextColor(getResources().getColor(
							R.color.white));
					community.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					recommend_right.setTextColor(getResources().getColor(R.color.black));
					ranked_right.setTextColor(getResources().getColor(R.color.black));
					category_right.setTextColor(getResources().getColor(R.color.grey));
					specialsubjiect_right.setTextColor(getResources().getColor(R.color.grey));
					break;
				case 4:
					recommend.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					ranked.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					category.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					specialsubject.setTextColor(getResources().getColor(
							R.color.tab_text_color));
					community.setTextColor(getResources().getColor(
							R.color.white));
					recommend_right.setTextColor(getResources().getColor(R.color.grey));
					ranked_right.setTextColor(getResources().getColor(R.color.grey));
					category_right.setTextColor(getResources().getColor(R.color.grey));
					specialsubjiect_right.setTextColor(getResources().getColor(R.color.grey));
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				if(arg0 == currentPage){
					leftLayout = currentPage*tabWidth+arg2/4;
					rightLayout = leftLayout+tabWidth;
				}else{
					leftLayout = currentPage*tabWidth-(screenWidth-arg2)/4;
					rightLayout = leftLayout+tabWidth;
				}
				indicator.layout(leftLayout, indicator.getTop(), rightLayout, indicator.getBottom());
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	private void initIndicator() {
    	tabWidth = screenWidth/5;
		indicator = findViewById(R.id.indicator);
		LayoutParams params = (LayoutParams) indicator.getLayoutParams();
		params.width = tabWidth;
		indicator.setLayoutParams(params);
	}

}
