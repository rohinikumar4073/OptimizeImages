package com.optimize.spritegenerator;
import java.util.ArrayList;
import java.util.Iterator;

import com.optimize.Beans.ImageArray;

public class ImageOutput {
	public static int imageHeight=0;
	public static int imageWidth=0;

	public static int maxheight=1000;
	ArrayList<ImageArray>  imageArrayList=new ArrayList<ImageArray>();
    public static ImageArray currentImage;
	public static Integer fixedHeight=0;
	private ArrayList<ImageArray>  outputimageArrayList;

	public ArrayList<ImageArray> getOutputimageArrayList() {
		return outputimageArrayList;
	}


	public void setOutputimageArrayList(ArrayList<ImageArray> outputimageArrayList) {
		this.outputimageArrayList = outputimageArrayList;
	}


	/**
	 * 
	 * @return boolean
	 * It returns whether the given image can be placed at a particular point in a plane of placed images.
	 * 
	 */
    public void placeTheImage(ImageArray inputImage){
  
    	initializePoint(inputImage);
    	boolean temp=true;
    	while(temp){
    		
    		
    			if(isFixablePoint(inputImage)){
    		    	this.getOutputimageArrayList().add(inputImage);
    				temp=false; }else{
    				
    				shiftToDown(inputImage);
    				
    				if(isCrossedHeight(inputImage)){
    					shiftLeft(inputImage);
    				}}
    			rearrangeImageDimensions(inputImage);
    			}

    	setTopLeftRightBottom(inputImage);
    	
    	
    }
    
    
    private void setTopLeftRightBottom(ImageArray inputImage) {

    	int top=0;
    	int bottom=0;
    	int left=0;
    	int right=0;
    	top=(int) (inputImage.getPositon().getyPosition()-inputImage.getHeight()/2);
    	bottom=(int) (inputImage.getPositon().getyPosition()+inputImage.getHeight()/2);
    	left=(int) (inputImage.getPositon().getxPosition()-inputImage.getWidth()/2);
    	right=(int) (inputImage.getPositon().getxPosition()+inputImage.getWidth()/2);
    	inputImage.setLeft(left);
    	inputImage.setBottom(bottom);
    	inputImage.setRight(right);
    	inputImage.setTop(top);


	}


	public static int getImageHeight() {
		return imageHeight;
	}


	public static void setImageHeight(int imageHeight) {
		ImageOutput.imageHeight = imageHeight;
	}


	public static int getImageWidth() {
		return imageWidth;
	}


	public static void setImageWidth(int imageWidth) {
		ImageOutput.imageWidth = imageWidth;
	}


	public static int getMaxheight() {
		return maxheight;
	}


	public static void setMaxheight(int maxheight) {
		ImageOutput.maxheight = maxheight;
	}


	public static ImageArray getCurrentImage() {
		return currentImage;
	}


	public static void setCurrentImage(ImageArray currentImage) {
		ImageOutput.currentImage = currentImage;
	}


	private void rearrangeImageDimensions(ImageArray inputImage) {
    	if((inputImage.getPositon().getyPosition()+inputImage.getHeight()/2)>imageHeight){
			imageHeight=(int) (inputImage.getPositon().getyPosition()+inputImage.getHeight()/2);
		}	
    	if((inputImage.getPositon().getxPosition()+inputImage.getWidth()/2)>imageWidth){
    		imageWidth=(int) (inputImage.getPositon().getxPosition()+inputImage.getWidth()/2);
		}	
	}


	private boolean isCrossedHeight(ImageArray inputImage) {
    return maxheight<inputImage.getPositon().getyPosition()+inputImage.getHeight()/2;
	}


	private void shiftToDown(ImageArray inputImage) {
    
       float targetImageyPosition=inputImage.getPositon().getyPosition()+1;
       inputImage.getPositon().setyPosition(targetImageyPosition);
	}

	private void shiftLeft(ImageArray inputImage) {
	    
	       float targetImagexPosition=inputImage.getPositon().getxPosition()+1;
	       inputImage.getPositon().setxPosition(targetImagexPosition);
	       inputImage.getPositon().setyPosition(inputImage.getHeight()/2);
		}
	public void initializePoint(ImageArray inputImage){
     int imageHeight=	inputImage.getHeight();           
     int imageWidth=	inputImage.getWidth();
     inputImage.setPositon(new Point(imageWidth/2,imageHeight/2));
    }

    
	public boolean isFixablePoint(ImageArray inputImage){
		for (Iterator iterator = outputimageArrayList.iterator(); iterator.hasNext();) {
			ImageArray image = (ImageArray) iterator.next();
			float imgXPosition=image.getPositon().getxPosition();
			float imgYPosition=image.getPositon().getyPosition();
			int getHeight=image.getHeight();
			int imgWidht=image.getWidth();
			float inputImgXPosition=inputImage.getPositon().getxPosition();
			float inputImgYPosition=inputImage.getPositon().getyPosition();
		  /*  boolean xhigher=	inputImgXPosition<=(imgXPosition+imgWidht/2);
		    boolean xlower=	inputImgXPosition>=(imgXPosition-imgWidht/2);
		    boolean yhigher=	inputImgYPosition<=(imgYPosition+imgHeight/2);
		    boolean ylower=	inputImgYPosition>=+(imgYPosition-imgHeight/2);*/
			boolean xTemp=	getPositivie(imgXPosition-inputImgXPosition)<=(imgWidht/2+inputImage.getWidth()/2);
			boolean yTemp=	getPositivie(imgYPosition-inputImgYPosition)<=(getHeight/2+inputImage.getHeight()/2);
		    if((xTemp  )& (yTemp))  {
				return false;
			}
		}		
			return true;
	}
	private float getPositivie(float f) {
		// TODO Auto-generated method stub
	if(f<0){
		return -f;
	}else
		return f;
	}


	public ArrayList<ImageArray> getImageArrayList() {
		return imageArrayList;
	}
	public void setImageArrayList(ArrayList<ImageArray> imageArrayList) {
		this.imageArrayList = imageArrayList;
	}
	public static Integer getFixedHeight() {
		return fixedHeight;
	}
	public static void setFixedHeight(Integer fixedHeight) {
		ImageOutput.fixedHeight = fixedHeight;
	}
	public static void main(String args[]){
		ImageOutput imageOutput=new ImageOutput();
        imageOutput.setImageArrayList(Util.getImages());
      /*  ImageArray inputImage=new ImageArray();
        inputImage.setHeight(100);
        inputImage.setWidth(50);*/
        for (Iterator iterator = imageOutput.getImageArrayList().iterator(); iterator.hasNext();) {
        	ImageArray inputImage = (ImageArray) iterator.next();
        	 imageOutput.placeTheImage(inputImage);
         	System.out.println("The image name is "+inputImage.getImageName()+"is placed at "+inputImage.getPositon().getxPosition()+" and " +inputImage.getPositon().getyPosition());

		}
        //Point position=new Point(64,3);
       // inputImage.setPositon(position);
        //inputImage.getPositon().setyPosition(20);
        
       
//System.out.println("The point is "+imageOutput.isFixablePoint(inputImage));
	}
	
}
