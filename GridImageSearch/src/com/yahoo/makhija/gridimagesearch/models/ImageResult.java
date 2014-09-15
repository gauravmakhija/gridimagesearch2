package com.yahoo.makhija.gridimagesearch.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult implements Serializable{
	
	private static final long serialVersionUID = 1967820096880313098L;
	private static final String TB_URL = "tbUrl";
	private static final String URL = "url";
	private String fullUrl;
	private String thumbnailUrl;
	
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
	ImageResult(JSONObject json){
		try{
			this.fullUrl = json.getString(URL);
			this.thumbnailUrl = json.getString(TB_URL);
		}catch(JSONException e){
			this.fullUrl = null;
			this.thumbnailUrl = null;
		}
	}
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray imageJsonResults) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int x=0 ; x<imageJsonResults.length();x++){
			try{
				results.add(new ImageResult(imageJsonResults.getJSONObject(x)));
				
			}catch(JSONException e){
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public String toString() {
		return thumbnailUrl;
	}
}
