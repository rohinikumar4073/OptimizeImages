package com.optimize.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.optimize.Config;
import com.optimize.Beans.Devices;
import com.optimize.Beans.ImageArray;


public class OptimizeDataManger {


public void saveData(ImageArray data){
	   try{
		    Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);
			PreparedStatement psmnt = con.prepareStatement
					("INSERT INTO heroku_b71e4549731517b.imagesforsprites"
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
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);
			PreparedStatement psmnt = con.prepareStatement("Select * from heroku_b71e4549731517b.imagesforsprites where sessionId=?");
			psmnt.setString(1, sessionId);
			boolean s = psmnt.execute();
			ResultSet resultSet=psmnt.getResultSet();
			while(resultSet.next()){
				ImageArray data=new ImageArray();
				data.setHeight(Integer.parseInt(resultSet.getString(3)));
				data.setWidth(Integer.parseInt(resultSet.getString(4)));
				data.setImageName(resultSet.getString(5));
				data.setImageId(resultSet.getString(2));
				imageArrayList.add(data);
			}
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
		    
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);
			PreparedStatement psmnt = con.prepareStatement("Select p.platform_name,m.device_name,m.width,m.height from heroku_b71e4549731517b.mobile_devices m,"
					+ "heroku_b71e4549731517b.mobile_platforms p where p.idmobile_platforms = m.platform");
			boolean s = psmnt.execute();
			ResultSet resultSet=psmnt.getResultSet();
			while(resultSet.next()){
				Devices device=new Devices();
				device.setHeight(resultSet.getInt(4));
				device.setWidth(resultSet.getInt(3));
                String platform=resultSet.getString(1);
                device.setDeviceName(resultSet.getString(2));
				if (platfromMap.containsKey(platform))				{
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
public void deleteData(ImageArray data) {
	 try{
		 
		    Class.forName("com.mysql.jdbc.Driver");
		    
			
			Connection con = DriverManager.getConnection(Config.databaseUrl,Config.userName,Config.passWord);
			PreparedStatement psmnt = con.prepareStatement("Delete from "
					+ "heroku_b71e4549731517b.imagesforsprites where imageName='"+data.getImageName()+"' AND sessionId='"+data.getImageId()+"'");

		
		
		

			boolean s = psmnt.execute();
		
			//Close the Statement & connection
			psmnt.close();
			con.close();
			} catch (Exception e) {
			System.out.println("Error Connecting : "+e.getMessage());
		}
}

}

