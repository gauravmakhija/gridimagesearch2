package com.yahoo.makhija.gridimagesearch.models;

import java.io.Serializable;

public class Settings implements Serializable{

	private static final long serialVersionUID = 5490415765812104437L;
	private String imageSize;
	private String imageColorFilter;
	private String imageType;
	private String imageSiteFilter;
	
	public String getImageSize() {
		return imageSize;
	}
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
	
	public String getColorFilter() {
		return imageColorFilter;
	}
	public void setColorFilter(String colorFilter) {
		this.imageColorFilter = colorFilter;
	}
	
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getSiteFilter() {
		return imageSiteFilter;
	}
	public void setSiteFilter(String siteFilter) {
		this.imageSiteFilter = siteFilter;
	}

}
