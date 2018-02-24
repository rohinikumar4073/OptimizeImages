package com.optimize;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.optimize.Beans.ImageArray;
import com.optimize.database.OptimizeDataManger;
import com.optimize.spritegenerator.ImageOutput;



public class DownloadImages 
 implements ImageObserver{
	   private BufferedImage img;
	   private String imageId; 
	   private OptimizeDataManger optimizeDataManger; 

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	private ImageOutput imageOutput=new ImageOutput();
	    
	   public ImageOutput getImageOutput() {
		return imageOutput;
	}

	public void setImageOutput(ImageOutput imageOutput) {
		this.imageOutput = imageOutput;
	}

	public void service(HttpServletRequest request, HttpServletResponse response,OptimizeDataManger optimizeDataManger)
		throws ServletException, IOException {
		   this. optimizeDataManger=optimizeDataManger;

	// TODO Auto-generated method stub
	    //	String sessionid=request.getSession().getId();
	    	String sessionid=request.getParameter("temp");
	    	setImageId(sessionid);
	       orderTheImage(sessionid);


    }



	
	    public void orderTheImage(String sessionId){
	    	
	    ArrayList<ImageArray> imageData= optimizeDataManger.getImageListForId(sessionId);
	    try {
			this.processImageData(imageData,sessionId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

	

		public void processImageData(ArrayList<ImageArray> imageData, String sessionId) throws IOException {
			

	        imageOutput.setImageArrayList(imageData);
	        File outputfile = new File(Config.fileLocation+sessionId+".png");	
	        imageOutput.setOutputimageArrayList(new ArrayList<ImageArray>());
	        Collections.sort(imageOutput.getImageArrayList());
			for (Iterator iterator = imageOutput.getImageArrayList().iterator(); iterator.hasNext();) {
	        	ImageArray inputImage = (ImageArray) iterator.next();
			//	BufferedImage img = ImageIO.read(inputImage.getFile());
	        	imageOutput.placeTheImage(inputImage);
			}
			
			
            BufferedImage bufferedImage=new BufferedImage(imageOutput.getImageWidth(), imageOutput.getImageHeight(),BufferedImage.TYPE_INT_ARGB );
        	for (Iterator iterator = imageOutput.getImageArrayList().iterator(); iterator.hasNext();) {
	        	ImageArray inputImage = (ImageArray) iterator.next();
			//	BufferedImage img = ImageIO.read(inputImage.getFile());
				img = ImageIO.read(new File(Config.fileLocation+inputImage.getImageName()));
	            int height=(int) (inputImage.getPositon().getyPosition()-inputImage.getHeight()/2);
	            int width=(int) (inputImage.getPositon().getxPosition()-inputImage.getWidth()/2);
	           bufferedImage.createGraphics().drawImage(img,width,height,this);	          	
	           ImageIO.write(bufferedImage, "png", outputfile);	         	

			}
			
		}

		@Override
		public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
				int arg4, int arg5) {
			// TODO Auto-generated method stub
			return false;
		}
	}

