package com.yahoo.makhija.gridimagesearch.activity;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.makhija.gridimagesearch.R;
import com.yahoo.makhija.gridimagesearch.adapters.ImageResultArrayAdapter;
import com.yahoo.makhija.gridimagesearch.listeners.EndlessScrollListener;
import com.yahoo.makhija.gridimagesearch.models.ImageResult;
import com.yahoo.makhija.gridimagesearch.models.Settings;

public class SearchActivity extends Activity {

    private static final String SETTINGS = "settings";
	private static final String QUERY = "&q=";
	private static final String START = "&start=";
	private static final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0";
	private static final int CHANGE_SETTINGS = 1;
	
	private Settings settings;
	private EditText etSearchQuery;
	private GridView gvImages;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        settings = new Settings();
        etSearchQuery = (EditText)findViewById(R.id.etSearchQuery);
        gvImages = (GridView)findViewById(R.id.gvImages);
        imageAdapter = new ImageResultArrayAdapter(this, R.layout.item_image_result, imageResults);
        gvImages.setAdapter(imageAdapter);
        setupItemClickListener();
        setupOnScrollListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    public void onSearch(View v){
		fetchAndPopulateResults(0,true);
	}
	
    public void onChangeSettings(MenuItem mi){
    	Intent i = new Intent(this,ChangeSettingsActivity.class);
    	i.putExtra(SETTINGS, settings);
       	startActivityForResult(i, CHANGE_SETTINGS);
    }

	private void setupOnScrollListener() {
		gvImages.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page); 
            }
            });
	}

	private void setupItemClickListener() {
		gvImages.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult image = imageResults.get(position);
				i.putExtra("result", image);
				startActivity(i);
			}
		});
	}
	
    public void customLoadMoreDataFromApi(int page) {
    	fetchAndPopulateResults(page,false);
    }

	private void fetchAndPopulateResults(int page, final boolean clearResults) {
		String query = etSearchQuery.getText().toString();
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get(constructUrl(page, query), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				try{
					JSONArray imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
					if(clearResults)
						imageResults.clear();
					imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
					imageAdapter.notifyDataSetChanged();
					Log.d("DEBUG", imageResults.toString());
				}catch(JSONException e){
					e.printStackTrace();
				}
			}
		});
	}

	private String constructUrl(int page, String query) {
		StringBuilder url = new StringBuilder(BASE_URL);
		url.append(START);
		url.append(page*8);
		url.append(QUERY);
		url.append(Uri.encode(query));
		if(settings.getImageSize()!=null)
			url.append("&imgsz=").append(settings.getImageSize());
		if(settings.getColorFilter()!=null)
			url.append("&imgc=").append(settings.getColorFilter());
		if(settings.getImageType()!=null)
			url.append("&imgtype=").append(settings.getImageType());
		if(settings.getSiteFilter()!=null)
			url.append("&as_sitesearch=").append(settings.getSiteFilter());
		return url.toString();
	}
        
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == CHANGE_SETTINGS){
			if(resultCode==RESULT_OK){
				Settings settings = (Settings)data.getSerializableExtra(SETTINGS);
				this.settings = settings;
			}
		}
	}
   
}
