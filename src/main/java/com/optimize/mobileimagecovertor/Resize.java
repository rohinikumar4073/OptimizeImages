package com.optimize.mobileimagecovertor;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import com.optimize.Config;
import com.optimize.Beans.Devices;
import com.optimize.Beans.ImageArray;
import com.optimize.database.OptimizeDataManger;
import com.optimize.util.ZipFiles;

public class Resize {
	private String imageId;

	public void service(HttpServletRequest request,
			HttpServletResponse response, OptimizeDataManger optimizeDataManger)
			throws ServletException, IOException {

		Devices inputDevices = new Devices();
		ArrayList<Devices> outputDevices = new ArrayList<Devices>();
		String formInputDeviceType = request
				.getParameter("formInputDeviceType");
		String formOutputDeviceType = request
				.getParameter("formOutputDeviceType");
		String inputDeviceDetails = null;
		String outputDeviceDetails = null;
		if (formInputDeviceType.equals("2")) {
			String inputDeviceWidth = request
					.getParameter("formInputDeviceWidth");
			String inputDeviceHeight = request
					.getParameter("formInputDeviceHeight");

			inputDevices.setWidth(Integer.parseInt(inputDeviceWidth));
			inputDevices.setHeight(Integer.parseInt(inputDeviceHeight));
			inputDevices.setDeviceName("Other");

		} else {
			inputDeviceDetails = request.getParameter("inputDeviceDetails");
			setInputDeviceData(inputDevices, inputDeviceDetails);

		}
		if (formOutputDeviceType.equals("2")) {
			String outputDeviceWidth = request
					.getParameter("formOutputDeviceWidth");
			String outputDeviceHeight = request
					.getParameter("formOutputDeviceHeight");
			Devices outputDevice = new Devices();
			outputDevice.setWidth(Integer.parseInt(outputDeviceWidth));
			outputDevice.setHeight(Integer.parseInt(outputDeviceHeight));
			outputDevice.setDeviceName("Other");
			outputDevices.add(outputDevice);
		} else {
			outputDeviceDetails = request.getParameter("outputDeviceDetails");
			setOutputDeviceData(outputDevices, outputDeviceDetails);

		}

		String sessionid = request.getParameter("temp");
		this.setImageId(sessionid);
		ArrayList<ImageArray> imageData = optimizeDataManger
				.getImageListForId(sessionid);
		File theDir = new File(Config.fileLocation + sessionid);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			boolean result = theDir.mkdir();

		}
		for (Iterator iterator2 = outputDevices.iterator(); iterator2.hasNext();) {
			Devices devices = (Devices) iterator2.next();
			File outputDevi = new File(Config.fileLocation + sessionid + "/"
					+ devices.getDeviceName());
			if (!outputDevi.exists()) {
				outputDevi.mkdir();

			}
			for (Iterator iterator = imageData.iterator(); iterator.hasNext();) {

				ImageArray imageArray = (ImageArray) iterator.next();
				BufferedImage originalImage = ImageIO.read(new File(
						Config.fileLocation + imageArray.getImageName()));

				float ratio = calculateRatio(inputDevices, devices);
				int imageWidth =  Math.round(ratio * originalImage.getWidth());
				int imageHeight =  Math.round(ratio * originalImage.getHeight());
				if(imageWidth==0){
					imageWidth=1;
				}
				if(imageHeight==0){
					imageHeight=1;
				}
				File outputImage = new File(Config.fileLocation + sessionid
						+ "/" + devices.getDeviceName() + "/"
						+ imageArray.getImageName());
				String[] imageType = imageArray.getImageName().split("\\.");
				BufferedImage resizedImage=null;
				/*if(imageType[1].equals("png")){
				 resizedImage = new BufferedImage(imageWidth,
						imageHeight, BufferedImage.TYPE_INT_ARGB);
				 }else{
					 resizedImage = new BufferedImage(imageWidth,
								imageHeight, BufferedImage.TYPE_INT_RGB);
				 }
				*/
				//Graphics2D g = resizedImage.createGraphics();
				  //g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			//	g.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
                    resizedImage = Scalr.resize(originalImage, Method.ULTRA_QUALITY, imageHeight);

				//g.dispose();
				/*
				 * g.setComposite(AlphaComposite.Src);
				 * g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				 * g.setRenderingHint(RenderingHints.KEY_RENDERING,
				 * RenderingHints.VALUE_RENDER_QUALITY);
				 * g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				 * RenderingHints.VALUE_ANTIALIAS_ON);
				 */
				ImageIO.write(resizedImage, imageType[1], outputImage);

			}

		}

		String zipDirName = Config.fileLocation + sessionid + ".zip";

		File dir = new File(Config.fileLocation + sessionid);
		new ZipFiles().zipDirectory(dir, zipDirName);

	}

	private void setOutputDeviceData(ArrayList<Devices> outputDevices,
			String outputDeviceDetails) {
		String[] output = outputDeviceDetails.split(",");
		for (int i = 0; i < output.length; i++) {
			Devices devices = new Devices();
			setInputDeviceData(devices, output[i]);
			outputDevices.add(devices);
		}
	}

	private void setInputDeviceData(Devices devices, String inputDeviceDetails) {
		String[] str = inputDeviceDetails.split("\\(");

		devices.setDeviceName(str[0].trim());
		String[] str2 = str[1].split(" ");
		devices.setWidth(Integer.parseInt(str2[1]));
		devices.setHeight(Integer.parseInt(str2[3]));
	}

	private float calculateRatio(Devices inputDevices, Devices devices) {

		float ratio = (float) devices.getWidth() / inputDevices.getWidth();

		return ratio;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
