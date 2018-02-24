package com.optimize.spritegenerator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.spi.Invoker;

import com.optimize.Beans.ImageArray;

public class Util {
private static String afileName="D:/Idea/input_data.csv";

public static ArrayList<ImageArray>getImages(){
	
	ArrayList<ImageArray> imageArrayList=new ArrayList<ImageArray>();
	try {
		FileInputStream fstream = new FileInputStream(afileName);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {	
	     String[] inputData=strLine.split(",");
         ImageArray imageArray=new ImageArray();
         imageArray.setImageName(inputData[0]);
        imageArray.setWidth(Integer.parseInt(inputData[1]));
         imageArray.setHeight(Integer.parseInt(inputData[2]));
         //imageArray.setPositon(new Point(Float.parseFloat(inputData[3]),Float.parseFloat(inputData[4])));
         imageArrayList.add( imageArray);
		}
		// Close the input stream
		in.close();
	} catch (Exception e) {// Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
	Collections.sort(imageArrayList);
	
	return imageArrayList;
	
}
}
