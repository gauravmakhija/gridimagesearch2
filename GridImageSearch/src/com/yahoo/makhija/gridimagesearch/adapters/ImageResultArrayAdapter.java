package com.yahoo.makhija.gridimagesearch.adapters;

import java.util.List;

import com.loopj.android.image.SmartImageView;
import com.yahoo.makhija.gridimagesearch.R;
import com.yahoo.makhija.gridimagesearch.R.layout;
import com.yahoo.makhija.gridimagesearch.models.ImageResult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, int resource,
			List<ImageResult> objects) {
		super(context, R.layout.item_image_result, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflater.inflate(R.layout.item_image_result, parent, false);
		}else{
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbnailUrl());
		return ivImage;
	}

}
