package com.dolphinnlp.qmind.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Answer {
	private String aid;
	private String acontent;
	private String words;
	private String isbest;

	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getIsbest() {
		return isbest;
	}
	public void setIsbest(String isbest) {
		this.isbest = isbest;
	}

	public JSONObject toJSON() {
	    JSONObject obj = new JSONObject();

	    obj.put("aid", aid);
	    obj.put("acontent", acontent);
	    obj.put("isbest", isbest);
	    obj.put("words", words);

	    return obj;
	}

	public static Answer fromJSON(String json) {
	    JSONObject obj = JSON.parseObject(json);
	    return fromJSON(obj);
	}
	public static Answer fromJSON(JSONObject obj) {
        Answer res = new Answer();

        res.aid = obj.getString("aid");
        res.acontent = obj.getString("acontent");
        res.isbest = obj.getString("isbest");
        res.words = obj.getString("words");

        return res;
    }
}
