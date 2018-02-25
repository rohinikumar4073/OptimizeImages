package com.optimize;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.optimize.Beans.ImageArray;
import com.optimize.database.OptimizeDataManger;



public class UploadImage 
	extends HttpServlet {
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	    	// Create a factory for disk-based file items
	    	DiskFileItemFactory factory = new DiskFileItemFactory();
	    

	    	// Create a new file upload handler
	    	ServletFileUpload upload = new ServletFileUpload(factory);
            String imageId=null;
	    	// Parse the request
	    	try {
				List<FileItem> items = upload.parseRequest(request);
				String sessionid=request.getParameter("temp");
		       for (Iterator iterator = items.iterator(); iterator.hasNext();) {
		    	   
				FileItem fileItem = (FileItem) iterator.next();
				if(fileItem.isFormField()){
					imageId=fileItem.getFieldName();
					
					continue;
				}
				
				String name=fileItem.getName();
				
				File file =new File(Config.fileLocation+name);
				ImageArray data=new ImageArray();
		
				//data.setImageId(sessionid);
				fileItem.write(file);
				
				data.setFile(file);
				data.setImageName(name);
				data.processImage(file);
				data.setImageId(imageId);
				new OptimizeDataManger().saveData(data)
;			}		
			
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    }
	}

