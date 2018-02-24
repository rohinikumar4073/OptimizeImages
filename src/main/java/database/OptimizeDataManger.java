package com.optimize.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.optimize.Config;
import com.optimize.Beans.Devices;
import com.optimize.Beans.ImageArray;


public class OptimizeDataManger {


public void saveData(ImageArray data){
	   try{
		    Class.forName("com.mysql.jdbc.Driver");
		    
			//Connecting to MYSQL Database
			//SQL Database name is java
			//SQL server is localhost, username:root, password:nopassword 
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);

			//Using the Connection Object now Create a Statement


			//Save The Data Into the Database Table
			//Any String Typed Instead of data1, data2 will be saved to the database.

			PreparedStatement psmnt = con.prepareStatement
					("INSERT INTO optimize.imagesforsprites"
							+ "(sessionId,height,width,imageName)VALUES(?,?,?,?)");

			psmnt.setString(1, data.getImageId());
			psmnt.setInt(2, data.getHeight());
			psmnt.setInt(3, data.getWidth());
			psmnt.setString(4, data.getImageName());

			boolean s = psmnt.execute();

			//Close the Statement & connection
			psmnt.close();
			con.close();
			} catch (Exception e) {
			System.out.println("Error Connecting : "+e.getMessage());
		}
		}
public ArrayList<ImageArray> getImageListForId(String sessionId){
	 ArrayList<ImageArray> imageArrayList=new ArrayList<ImageArray>();
	 
	   try{
		 
		    Class.forName("com.mysql.jdbc.Driver");
		    
			//Connecting to MYSQL Database
			//SQL Database name is java
			//SQL server is localhost, username:root, password:nopassword 
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);

			//Using the Connection Object now Create a Statement


			//Save The Data Into the Database Table
			//Any String Typed Instead of data1, data2 will be saved to the database.

			PreparedStatement psmnt = con.prepareStatement("Select * from optimize.imagesforsprites where sessionId=?");

		
			psmnt.setString(1, sessionId);
		

			boolean s = psmnt.execute();
			ResultSet resultSet=psmnt.getResultSet();
			while(resultSet.next()){
				ImageArray data=new ImageArray();
				data.setHeight(Integer.parseInt(resultSet.getString(3)));
				data.setWidth(Integer.parseInt(resultSet.getString(4)));
				data.setImageName(resultSet.getString(5));
				data.setImageId(resultSet.getString(2));
	        /*	InputStream inputStream=resultSet.getBinaryStream(2);*/
			/*	File file =new File("C:/Users/rohinikumar_Svv/Documents/Optimize/"+data.getImageName());
				data.setFile(file);
				FileOutputStream fileoutputstream=new FileOutputStream(data.getFile());
				int read = 0;
				byte[] bytes = new byte[1024];
		 
				while ((read = inputStream.read(bytes)) != -1) {
					fileoutputstream.write(bytes, 0, read);
				}*/
				imageArrayList.add(data);
				
			}

			//Close the Statement & connection
			psmnt.close();
			con.close();
			} catch (Exception e) {
			System.out.println("Error Connecting : "+e.getMessage());
		}
			return imageArrayList;
		}

public HashMap<String, ArrayList<Devices>> getDeviceList(){
	 HashMap<String, ArrayList<Devices>> platfromMap=new HashMap<String, ArrayList<Devices>> ();
	 try{
		 
		    Class.forName("com.mysql.jdbc.Driver");
		    
			//Connecting to MYSQL Database
			//SQL Database name is java
			//SQL server is localhost, username:root, password:nopassword 
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);

			//Using the Connection Object now Create a Statement


			//Save The Data Into the Database Table
			//Any String Typed Instead of data1, data2 will be saved to the database.

			PreparedStatement psmnt = con.prepareStatement("Select p.platform_name,m.device_name,m.width,m.height from optimize.mobile_devices m,optimize.mobile_platforms p where p.idmobile_platforms = m.platform");

		
		
		

			boolean s = psmnt.execute();
			ResultSet resultSet=psmnt.getResultSet();
			while(resultSet.next()){
				Devices device=new Devices();
				device.setHeight(resultSet.getInt(4));
				device.setWidth(resultSet.getInt(3));
                String platform=resultSet.getString(1);
                device.setDeviceName(resultSet.getString(2));

	        /*	InputStream inputStream=resultSet.getBinaryStream(2);*/
			/*	File file =new File("C:/Users/rohinikumar_Svv/Documents/Optimize/"+data.getImageName());
				data.setFile(file);
				FileOutputStream fileoutputstream=new FileOutputStream(data.getFile());
				int read = 0;
				byte[] bytes = new byte[1024];
		 
				while ((read = inputStream.read(bytes)) != -1) {
					fileoutputstream.write(bytes, 0, read);
				}*/
				if(platfromMap.containsKey(platform))
				{
					platfromMap.get(platform).add(device);
					
				}else{
					 ArrayList<Devices> devices=new ArrayList<Devices>();
                     devices.add(device);
				     platfromMap.put(platform, devices);
				}
			}
			
			//Close the Statement & connection
			psmnt.close();
			con.close();
			} catch (Exception e) {
			System.out.println("Error Connecting : "+e.getMessage());
		}
	return platfromMap;

}

}

