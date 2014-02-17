package com.amibee.vishu.scrapbook.dao.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Test {

	public static Properties readPropertiesFile(String fileName){
		Properties properties = null;
		try {
				properties = new Properties();
				InputStream inStream = Test.class.getResourceAsStream(fileName);
				if(inStream!=null){
					properties.load(inStream);
				}else{
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return properties;
		
	}
}
