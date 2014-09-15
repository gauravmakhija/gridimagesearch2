package com.yahoo.makhija.gridimagesearch.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnSizeSelectedListener implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
//		Toast.makeText(parent.getContext(), 
//				"OnSizeSelectedListener : " + parent.getItemAtPosition(position).toString(),
//				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//do nothing
	}

}
