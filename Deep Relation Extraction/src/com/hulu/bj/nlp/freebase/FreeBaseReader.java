package com.hulu.bj.nlp.freebase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class FreeBaseReader {
	FileInputStream fin;
	GZIPInputStream gzin; 
	BufferedInputStream bi;
	
	BufferedReader reader;
	
	public FreeBaseReader(String filePath)
	{
		try {
		fin = new FileInputStream(filePath);
		gzin = new GZIPInputStream(fin);
		bi = new BufferedInputStream(gzin);
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
		ArrayList<FreeBaseEntry> aList = new ArrayList<FreeBaseEntry>();
		FreeBaseReader freeBaseReader = new FreeBaseReader("dataset\\train\\freebase-rdf-2013-05-19-00-00.gz");
		BufferedReader bufferedReader = freeBaseReader.getBufferedReader();
		String line = null;
		String tmp = "";
		int count = 0;
		FreeBaseEntry currentEntry = null;
		while((line = bufferedReader.readLine()) != null && count <= 200)
		{
			//System.out.println(line);
			String[] parts = line.split("\t");	
			if(parts.length != 3)
				continue;
			String subject = parts[0];
			//System.out.println(subject);
			String predicate = parts[1];
			//System.out.println(predicate);
			String object = parts[2];
			//System.out.println(object);

			if(subject.equals(tmp))
			{
				currentEntry.addRelation(predicate, object);
			}
			else
			{
				tmp = String.copyValueOf(subject.toCharArray());
				//System.out.println(tmp);
				if(currentEntry != null)
				{
					aList.add(currentEntry);
					
					currentEntry = new FreeBaseEntry();
					currentEntry.setSubject(subject);
					//System.out.println(subject);
					currentEntry.addRelation(predicate, object);
				}
				else {
					currentEntry = new FreeBaseEntry();
					currentEntry.setSubject(subject);
					//System.out.println(subject);
					currentEntry.addRelation(predicate, object);
				}
				count ++;

			}			
		}
		
		for(int i = 0; i < count; i++)
		{
			//System.out.println(aList.get(i).getSubject());
			//System.out.println("************************************************");
			for(PreObjPair pair : aList.get(i).getRelationList())
			{
				System.out.println(aList.get(i).getSubject() + "\t" + pair.getPredicate() + "\t" + pair.getObject());
			}
			Scanner scanner = new Scanner(System.in);
			scanner.next();
		}
	}
}
