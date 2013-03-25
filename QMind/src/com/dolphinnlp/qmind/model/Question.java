package com.dolphinnlp.qmind.model;

public class Question {
	private String qid;
	private String qtitle;
	private String qcontent;
	private String category;
	private String tword;
	private String cword;
	
	public String getTword() {
		return tword;
	}
	public void setTword(String tword) {
		this.tword = tword;
	}
	public String getCword() {
		return cword;
	}
	public void setCword(String cword) {
		this.cword = cword;
	}
	
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
