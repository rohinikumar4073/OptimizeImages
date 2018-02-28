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



public class DownloadZipFiles 
	extends HttpServlet {
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		  response.setContentType("application/zip"); 
			 String sessionid=request.getParameter("temp"); 
	        response.setHeader("Content-Disposition", "attachment; filename="+sessionid+".zip");
		 InputStream fileIn = new FileInputStream(new File(Config.fileLocation+sessionid+".zip"));
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

