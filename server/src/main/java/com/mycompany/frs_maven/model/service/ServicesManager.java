package com.mycompany.frs_maven.model.service;

import java.io.FileInputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ServicesManager {
	static private Logger logger = LogManager.getLogger();
	static private HashMap<String, String> servicesMap = new HashMap<String, String>();
	
	public static void loadServices(String xmlFileLocation) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder parser = factory.newDocumentBuilder();
			Document document = parser.parse(new FileInputStream(xmlFileLocation));
			Element root = document.getDocumentElement();
			NodeList nodes = root.getChildNodes();
			for(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if(node instanceof Element) {
					Element element = (Element) node;
					String interf = element.getAttribute("interface");
					String impl = element.getAttribute("implementation");
					servicesMap.put(interf, impl);
					
				}
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	static public String getService(String key) {
		String service = null;
		try {
			service = servicesMap.get(key);
		}
		catch(Exception e) {
			logger.error(e);
		}
		return service;
	}
}
