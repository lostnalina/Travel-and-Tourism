package com.tourismMySQL.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.management.RuntimeErrorException;


public class DatabaseUtil {
  static Connection conn=null;
  public static Connection Connect() {
	  try {
		  
		  conn=DriverManager.getConnection("jdbc:mysql://localhost/projem" , "root","");
		  return conn;
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage().toString());
		return null;
	}
  }
   
  public static  String MD5Sifrele(String icerik) {
	  try {
		  MessageDigest md=MessageDigest.getInstance("MD5");
		  byte [] passw=md.digest(icerik.getBytes());//getirilen içeriği byte byte okumamızı sağlar.
		  
		  BigInteger no= new BigInteger(1,passw);
		  //hex değerini hesapla 
	
	      String hshcontents=no.toString(16);
	      while(hshcontents.length()<32) {
	    	  hshcontents="0"+hshcontents;
	      }
	  return hshcontents;
	  
	  
	  } catch (NoSuchAlgorithmException e) {
		// TODO: handle exception
		  throw new RuntimeException(e);
	}
	
	  
	  

	  
  }
  
  
  
}
