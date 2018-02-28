package com.optimize.controller;

import com.optimize.DownloadImage;
import com.optimize.DownloadImages;
import com.optimize.UploadImage;
import com.optimize.database.OptimizeDataManger;
import com.optimize.mobileimagecovertor.Resize;

public class OptimizerController {
private OptimizeDataManger optimizeDataManager;
private DownloadImage downloadImage;
private DownloadImages downloadImages;
private UploadImage uploadImages;
private Resize resize;

public void setOptimizeDataManager(OptimizeDataManger optimizeDataManager) {
	this.optimizeDataManager = optimizeDataManager;
}
public OptimizeDataManger getOptimizeDataManager() {
	if(this.optimizeDataManager== null){
		return new OptimizeDataManger();
	}
	return optimizeDataManager;
}
public void setDownloadImage(DownloadImage downloadImage) {
	this.downloadImage = downloadImage;
}
public DownloadImage getDownloadImage() {
	if(this.downloadImage==null){
		return new DownloadImage();
	}
	return downloadImage;
}
public void setDownloadImages(DownloadImages downloadImages) {
	this.downloadImages = downloadImages;
}
public DownloadImages getDownloadImages() {
	if(this.downloadImages==null){
		return new DownloadImages();
	}
	return downloadImages;
}
public void setUploadImages(UploadImage uploadImages) {
	this.uploadImages = uploadImages;
}
public UploadImage getUploadImages() {
	if(this.uploadImages==null){
		return new UploadImage();
	}
	return uploadImages;
}
public void setResize(Resize resize) {
	this.resize = resize;
}
public Resize getResize() {
	if(this.resize==null){
		return new Resize();
	}
	return resize;
}

 
}
