package com.optimize;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.optimize.Beans.ImageArray;
import com.optimize.database.OptimizeDataManger;



public class RemoveImage 
	extends HttpServlet {
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	    	// Create a factory for disk-based file items
	    	DiskFileItemFactory factory = new DiskFileItemFactory();
	    	//String sessionid=request.getParameter("uniqueid");
	    	// Configure a repository (to ensure a secure temp location is used)
	   

	    	// Create a new file upload handler
	    	ServletFileUpload upload = new ServletFileUpload(factory);
	    	// Parse the request
	    	try {
				String sessionid=request.getParameter("temp");
				String fileName=request.getParameter("fileName");

		   
				
				
				File file =new File(Config.fileLocation+fileName);
				ImageArray data=new ImageArray();
		
				//data.setImageId(sessionid);
				file.delete();				
				//data.setFile(file);
				data.setImageName(fileName);
				//data.processImage(file);
				data.setImageId(sessionid);
				new OptimizeDataManger().deleteData(data)
;					
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	}

