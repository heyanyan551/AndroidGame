package com.example.foolbirdgame.frg;

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

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RankedFrg extends Fragment {
	private List<HashMap<String, String>> rankeAdView;
	private HashMap<String, String> map;
    TextView  ad_left;
    TextView  ad_right;
	private View view;
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
		rankeView();
		init();
	}

	
	private void init() {
		
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
			System.out.println(arrryad);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
