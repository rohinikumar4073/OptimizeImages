package com.optimize;

import java.io.File;
import java.io.IOException;

public class Config {
	public static File temp ;
static {
	 try {
		temp = File.createTempFile("temp-file-name", ".tmp");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
public static String databaseUrl="jdbc:mysql://us-cdbr-iron-east-05.cleardb.net";
public static String userName="be22c0d42b69d9";
public static String passWord="1719164f";
public static String fileLocation=temp.getAbsolutePath();
}

