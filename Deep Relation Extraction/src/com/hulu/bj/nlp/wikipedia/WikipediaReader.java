package com.hulu.bj.nlp.wikipedia;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.*;


public class WikipediaReader {
	FileInputStream fin;
	BufferedInputStream bi;
	
	BufferedReader reader;
	
	public WikipediaReader(String filePath)
	{
		try {
		fin = new FileInputStream(filePath);
		
		bi = new BufferedInputStream(fin);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public BufferedReader getBufferedReader()
	{
		try {
			reader = new BufferedReader(new InputStreamReader(bi,"utf-8"),5*1024*1024);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
	
	public static void main(String[] args) throws IOException
	{
		WikipediaReader wikipediaReader = new WikipediaReader("dataset\\test\\enwiki-20130503-pages-articles-multistream.xml");
		BufferedReader bufferedReader = wikipediaReader.getBufferedReader();
		String line = null;
		int count = 0;
		while((line = bufferedReader.readLine()) != null && count <= 200)
		{
			System.out.println(line);
			count ++;
		}
	}
}
