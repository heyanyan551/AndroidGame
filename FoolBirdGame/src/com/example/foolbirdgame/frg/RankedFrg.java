package com.example.foolbirdgame.frg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.anjoyo.foolbirdgame.R;
import com.example.foolbirdgame.constant.Constant;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RankedFrg extends Fragment {
	private List<HashMap<String, String>> rankeAdView;
	private HashMap<String, String> map;
    ImageView  ad_left;
    ImageView  ad_right;
    LinearLayout  ad;
	private View view;
	private int screenWidth;
	private int screenHeigt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = LayoutInflater.from(getActivity()).inflate(
				R.layout.frg_ranked, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		WindowManager manager = (WindowManager) getActivity()
				.getSystemService(getActivity().WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(dm);
		screenHeigt=(dm.heightPixels);
		rankeView();
		initad();
	}

	
	private void initad() {
		ad=(LinearLayout) view.findViewById(R.id.ranke_ad);
		LayoutParams params = (LayoutParams) ad.getLayoutParams();
		params.height =screenHeigt *3/10;
		ad.setLayoutParams(params);
		ad_left= (ImageView) view.findViewById(R.id.adleft);
		ad_right=(ImageView) view.findViewById(R.id.adlight);
	}


	private void rankeView() {
		String path = Constant.RANKE_AD_URL;
		new HttpUtils().send(HttpMethod.GET, path,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						jsonRandead(arg0.result);
					}
				});
	}

	private void jsonRandead(String date) {
		rankeAdView=new ArrayList<HashMap<String,String>>();
         try {
			JSONObject   rankeads=new JSONObject(date);
			JSONArray   arrryad=rankeads.getJSONArray("items");
			for (int i = 0; i < arrryad.length(); i++) {
				JSONObject  rankeadsitem=arrryad.getJSONObject(i);
				map=new HashMap<String, String>();
				map.put("id", rankeadsitem.getString("id"));
				map.put("titlepic", rankeadsitem.getString("titlepic"));
				map.put("titleurl", rankeadsitem.getString("titleurl"));
				map.put("gameid", rankeadsitem.getString("gameid"));
				rankeAdView.add(map);
			}
			Picasso.with(getActivity()).load(Constant.BOOT_URL+rankeAdView.get(0).get("titlepic")).into(ad_left);
			Picasso.with(getActivity()).load(Constant.BOOT_URL+rankeAdView.get(1).get("titlepic")).into(ad_right);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
