package com.example.foolbirdgame.frg;

import java.util.HashMap;
import java.util.List;

import com.anjoyo.foolbirdgame.R;
import com.example.foolbirdgame.constant.Constant;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RankedFrg extends  Fragment{
	private  List<HashMap<String, String>> rankeView;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View  view=LayoutInflater.from(getActivity()).inflate(R.layout.frg_ranked, null);
	return view;
}
@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		rankeView();
	}

  private   void rankeView(){
	  String path=Constant.RANKE_AD_URL;
	  new HttpUtils().send(HttpMethod.GET, path, new RequestCallBack<String>() {

		@Override
		public void onFailure(HttpException arg0, String arg1) {
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			System.out.println(arg0.result+"~~~~~~~~~~~`");
		}
	});
  }
}
