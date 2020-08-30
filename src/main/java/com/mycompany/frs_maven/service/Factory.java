package com.mycompany.frs_maven.service;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import com.mycompany.frs_maven.exceptions.ServiceLoadException;

public class Factory {
	
	/* Singleton Design Pattern */
	private static Factory factory = new Factory();
	private Factory() {};
	public static Factory getInstance() { 
		return factory; 
	}
	
	public IService getService(String name) throws ServiceLoadException {
		try {
			Class<?> cls = Class.forName(getImplName(name));
			Constructor<?> constructor = cls.getConstructor();
			IService obj = (IService) constructor.newInstance();
			return obj;
		}
		catch (Exception e) {
			throw new ServiceLoadException(name + " not loaded");
		}
	}
	
	private String getImplName(String name) throws Exception {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/com/mycompany/frs_maven/service/properties.txt");
		props.load(fis);
		fis.close();
		return props.getProperty(name);
	}
}
