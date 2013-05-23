package com.hulu.bj.nlp.freebase;

public class PreObjPair implements Comparable<PreObjPair>
{
	private String predicate;
	private String object;
	
	public PreObjPair() {
		super();
		this.predicate = null;
		this.object = null;
	}
	

	public PreObjPair(String predicate, String object) {
		super();
		this.predicate = predicate;
		this.object = object;
	}

	public String getPredicate() {
		return predicate;
	}
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}

	@Override
	public int compareTo(PreObjPair o) {
		// TODO Auto-generated method stub
		return this.predicate.compareTo(o.getPredicate());
	}
}
