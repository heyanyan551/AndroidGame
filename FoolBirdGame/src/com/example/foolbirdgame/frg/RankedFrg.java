package com.example.foolbirdgame.frg;

import com.anjoyo.foolbirdgame.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RankedFrg extends  Fragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View  view=LayoutInflater.from(getActivity()).inflate(R.layout.frg_ranked, null);
	return view;
}
}
