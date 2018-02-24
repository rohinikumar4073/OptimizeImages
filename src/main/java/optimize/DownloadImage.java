package com.optimize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DownloadImage 
	extends HttpServlet {
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		  response.setContentType("image/PNG"); 
			 String sessionid=request.getParameter("temp"); 
	        response.setHeader("Content-Disposition", "attachment; filename="+sessionid+".png");
		 InputStream fileIn = new FileInputStream(new File(Config.fileLocation+sessionid+".png"));
	        ServletOutputStream outstream = response.getOutputStream();
	     
	        byte[] outputByte = new byte[40096];

	        while(fileIn.read(outputByte, 0, 40096) != -1)
	        {
	            outstream.write(outputByte, 0, 40096);
	        }
	        fileIn.close();
	        outstream.flush();
	        outstream.close();
	 }
       
	    	// Create a factory for disk-based file items
	   
	  


	}

