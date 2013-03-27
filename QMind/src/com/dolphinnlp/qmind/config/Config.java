package com.dolphinnlp.qmind.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	//public static final String DATASET_PATH_STRING = "D:\\DolphinNLP_Group\\应用\\QMindRepository\\dataset\\";
	//public static final String DOCS_PATH_STRING = "D:\\DolphinNLP_Group\\应用\\QMindRepository\\docs\\";
	//public static final String INDEX_PATH_STRING = "D:\\DolphinNLP_Group\\应用\\QMindRepository\\index\\";
	public static enum VAR {
		DATASET_PATH_STRING("DATASET_PATH_STRING"),
		DOCS_PATH_STRING("DOCS_PATH_STRING"),
		INDEX_PATH_STRING("INDEX_PATH_STRING");

		private String name;
		VAR(String name) {
			this.name = name;
		}
	}

	public Config(){}
	private static Properties props = new Properties();
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

	public static String getValue(VAR var) {
		return props.getProperty(var.name);
	}

	public static void updateProperties(String key,String value) {
		props.setProperty(key, value);
	}

	public static void main(String args[]) {
		System.out.println(getValue(VAR.DATASET_PATH_STRING));
	}
}
