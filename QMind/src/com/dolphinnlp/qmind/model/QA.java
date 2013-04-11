package com.dolphinnlp.qmind.model;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QA {
	private Question question;
	private ArrayList<Answer> answers;

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();

        obj.put("question", question.toJSON());
        JSONArray answers = new JSONArray();
        for (Answer answer : this.answers) {
            answers.add(answer.toJSON());
        }
        obj.put("answers", answers);

        return obj;
    }

    public static QA fromJSON(String json) {
        JSONObject obj = JSON.parseObject(json);
        QA res = new QA();

        JSONObject question = obj.getJSONObject("question");
        res.question = Question.fromJSON(question);

        JSONArray answers = obj.getJSONArray("answers");
        int length = answers.size();
        for (int i = 0; i < length; i++) {
            JSONObject answer = answers.getJSONObject(i);
            res.answers.add(Answer.fromJSON(answer));
        }
        return res;
    }
}
