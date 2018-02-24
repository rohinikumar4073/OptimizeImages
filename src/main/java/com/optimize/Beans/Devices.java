package com.optimize.Beans;

public class Devices {
private Platforms platform;
private int deviceId;
private String deviceHeight;
private int screenResolution;
private int width;
private int height;
private String deviceName;
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public String getDeviceName() {
	return deviceName;
}
public void setHeight(int height) {
	this.height = height;
}
public int getHeight() {
	return height;
}
public void setWidth(int width) {
	this.width = width;
}
public int getWidth() {
	return width;
}
public void setScreenResolution(int screenResolution) {
	this.screenResolution = screenResolution;
}
public int getScreenResolution() {
	return screenResolution;
}
public void setDeviceHeight(String deviceHeight) {
	this.deviceHeight = deviceHeight;
}
public String getDeviceHeight() {
	return deviceHeight;
}
public void setDeviceId(int deviceId) {
	this.deviceId = deviceId;
}
public int getDeviceId() {
	return deviceId;
}
public void setPlatform(Platforms platform) {
	this.platform = platform;
}
public Platforms getPlatform() {
	return platform;
}
}
