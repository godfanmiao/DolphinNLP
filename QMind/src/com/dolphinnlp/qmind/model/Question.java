package com.dolphinnlp.qmind.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();

        obj.put("qid", qid);
        obj.put("qtitle", qtitle);
        obj.put("qcontent", qcontent);
        obj.put("category", category);
        obj.put("tword", tword);
        obj.put("cword", cword);

        return null;
    }

    public static Question fromJSON(String json) {
        JSONObject obj = JSON.parseObject(json);

        return fromJSON(obj);
    }

    public static Question fromJSON(JSONObject obj) {
        Question res = new Question();

        res.qid = obj.getString("qid");
        res.qtitle = obj.getString("qtitle");
        res.qcontent = obj.getString("qcontent");
        res.category = obj.getString("category");
        res.tword = obj.getString("tword");
        res.cword = obj.getString("cword");

        return res;
    }
}
