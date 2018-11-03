package com.ab.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class ReadXml {
	public static void main(String[] args) {
		String path = "";
		readXml(path);
	}
	private static void readXml(String path){
		SAXReader reader = new SAXReader();
		try {
			Document read = reader.read(new File(path));
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
