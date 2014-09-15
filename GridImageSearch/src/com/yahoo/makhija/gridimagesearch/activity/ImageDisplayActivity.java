package com.yahoo.makhija.gridimagesearch.activity;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;
import com.yahoo.makhija.gridimagesearch.R;
import com.yahoo.makhija.gridimagesearch.models.ImageResult;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		
		ImageResult image = (ImageResult) getIntent().getSerializableExtra("result");
		SmartImageView ivImage = (SmartImageView)findViewById(R.id.ivResult);
		ivImage.setImageUrl(image.getFullUrl());
	}
}
