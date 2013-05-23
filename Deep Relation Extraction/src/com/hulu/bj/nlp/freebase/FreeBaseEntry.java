package com.hulu.bj.nlp.freebase;

import java.util.PriorityQueue;

public class FreeBaseEntry {
	private String subject;
	private PriorityQueue<PreObjPair> relationList;
	
	public FreeBaseEntry() {
		super();
		this.subject = null;
		this.relationList = new PriorityQueue<PreObjPair>();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void addRelation(String predicate, String object)
	{
		relationList.add(new PreObjPair(predicate, object));
		
	}
	public PriorityQueue<PreObjPair> getRelationList() {
		return relationList;
	}
	public void setRelationList(PriorityQueue<PreObjPair> relationList) {
		this.relationList = relationList;
	}	
}


