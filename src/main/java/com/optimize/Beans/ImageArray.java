package com.optimize.Beans;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.optimize.spritegenerator.Point;

public class ImageArray implements Comparable<ImageArray> {
    	private String imageId;
	    private File file;
		private int height;
		private int width;
		private String imageName;
       private int top;
       private int bottom;
       private int left;
       private int right;
		private Point positon;
		public int getHeight() {
			return height;
		}
	
		public int getTop() {
			return top;
		}

		public void setTop(int top) {
			this.top = top;
		}

		public int getBottom() {
			return bottom;
		}

		public void setBottom(int bottom) {
			this.bottom = bottom;
		}

		public int getLeft() {
			return left;
		}

		public void setLeft(int left) {
			this.left = left;
		}

		public int getRight() {
			return right;
		}

		public void setRight(int right) {
			this.right = right;
		}

		public void setHeight(int height) {
			this.height = height;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public String getImageName() {
			return imageName;
		}
		public void setImageName(String imageName) {
			this.imageName = imageName;
		}
		@Override
		public int compareTo(ImageArray imageArray) {
			 
			int imageHeight = imageArray.getHeight(); 
	 
			//ascending order
			return imageHeight-this.height;
	 
			//descending order
			//return compareQuantity - this.quantity;
	 
		}
	

		public Point getPositon() {
			return positon;
		}

		public void setPositon(Point positon) {
			this.positon = positon;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public File getFile() {
			return file;
		}

		public void setImageId(String imageId) {
			this.imageId = imageId;
		}

		public String getImageId() {
			return imageId;
		}	
		public void processImage(File file){
			BufferedImage readImage = null;

			try {
			    readImage = ImageIO.read(file);
			   this.height = readImage.getHeight();
			   this.width=readImage.getWidth();
			} catch (Exception e) {
			    readImage = null;
			}
		}
}
