package com.yahoo.makhija.gridimagesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.yahoo.makhija.gridimagesearch.R;
import com.yahoo.makhija.gridimagesearch.listeners.CustomOnColorSelectedListener;
import com.yahoo.makhija.gridimagesearch.listeners.CustomOnSizeSelectedListener;
import com.yahoo.makhija.gridimagesearch.listeners.CustomOnTypeSelectedListener;
import com.yahoo.makhija.gridimagesearch.models.Settings;

public class ChangeSettingsActivity extends Activity {

	private Settings settings;
	private ArrayAdapter<CharSequence> sizeAdapter ;
	private Spinner sizeSpinner;
	private ArrayAdapter<CharSequence> colorAdapter ;
	private Spinner colorSpinner;
	private ArrayAdapter<CharSequence> typeAdapter ;
	private Spinner typeSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_settings);
		settings = (Settings) getIntent().getSerializableExtra("settings");
		createSize();
		createColor();
		createType();
		((EditText) findViewById(R.id.etSiteFilter)).setText(settings.getSiteFilter());
	}

	private void createSize() {
		sizeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.size_array, android.R.layout.simple_spinner_item);
		sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		addListenerOnSizeSelection();
	}
	
	private void createColor() {
		colorAdapter = ArrayAdapter.createFromResource(this,
		        R.array.color_array, android.R.layout.simple_spinner_item);
		colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		addListenerOnColorSelection();
	}
	
	private void createType() {
		typeAdapter = ArrayAdapter.createFromResource(this,
		        R.array.type_array, android.R.layout.simple_spinner_item);
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		addListenerOnTypeSelection();
	}
	
	private void addListenerOnSizeSelection() {
		sizeSpinner = (Spinner) findViewById(R.id.spImageSize);
		sizeSpinner.setOnItemSelectedListener(new CustomOnSizeSelectedListener());	
		sizeSpinner.setAdapter(sizeAdapter);
		int index=0;
		for(int i=0;i<sizeAdapter.getCount();i++){
			if (sizeAdapter.getItem(i).equals(settings.getImageSize())) {
				index=i;
				break;
			}
		}
		sizeSpinner.setSelection(index);
	}
	
	private void addListenerOnColorSelection() {
		colorSpinner = (Spinner) findViewById(R.id.spColorFilter);
		colorSpinner.setOnItemSelectedListener(new CustomOnColorSelectedListener());
		colorSpinner.setAdapter(colorAdapter);
		int index=0;
		for(int i=0;i<colorAdapter.getCount();i++){
			if (colorAdapter.getItem(i).equals(settings.getColorFilter())) {
				index=i;
				break;
			}
		}
		colorSpinner.setSelection(index);
	}
	
	private void addListenerOnTypeSelection() {
		typeSpinner = (Spinner) findViewById(R.id.spImageType);
		typeSpinner.setOnItemSelectedListener(new CustomOnTypeSelectedListener());	
		typeSpinner.setAdapter(typeAdapter);
		int index=0;
		for(int i=0;i<typeAdapter.getCount();i++){
			if (typeAdapter.getItem(i).equals(settings.getImageType())) {
				index=i;
				break;
			}
		}
		typeSpinner.setSelection(index);
	}

	public void onSave(View v){
		settings.setImageSize(sizeSpinner.getSelectedItem().toString());
		settings.setColorFilter(colorSpinner.getSelectedItem().toString());
		settings.setImageType(typeSpinner.getSelectedItem().toString());
		settings.setSiteFilter(((EditText) findViewById(R.id.etSiteFilter)).getText().toString());
		Intent i = new Intent();
		i.putExtra("settings", settings);
		setResult(RESULT_OK, i);
		finish();
	}
	
}
