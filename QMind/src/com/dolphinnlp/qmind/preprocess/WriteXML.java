package com.dolphinnlp.qmind.preprocess;

import java.io.File;
import java.util.ArrayList;   

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text; 

import com.dolphinnlp.qmind.config.Config;
import com.dolphinnlp.qmind.model.Answer;
import com.dolphinnlp.qmind.model.QA;
import com.dolphinnlp.qmind.model.Question;

//<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//<root>
//<name id="2">zs</name>
//</root>

public class WriteXML {
	public static void createXML(QA qa) throws Exception {
	  //实例化解析器
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder builder = factory.newDocumentBuilder();
	  //创建Document对象
	   Document doc = builder.newDocument();
	  //创建XML文件所需的各种对象并序列化
	   Question question = qa.getQuestion();
	   ArrayList<Answer> answerList = qa.getAnswers();
	   Element q = doc.createElement("question");
	   
	   Element qid = doc.createElement(Tag.QIDSTAG.replaceAll("<|>", ""));
	   Text qidText = doc.createTextNode(question.getQid());
	   qid.appendChild(qidText);
	   q.appendChild(qid);
	
	   Element qtitle = doc.createElement(Tag.QTITLESTAG.replaceAll("<|>", ""));
	   Text qtitleText = doc.createTextNode(question.getQtitle());
	   qtitle.appendChild(qtitleText);
	   q.appendChild(qtitle);
	   
	   Element qcontent = doc.createElement(Tag.QCONTENTSTAG.replaceAll("<|>", ""));
	   Text qcontentText = doc.createTextNode(question.getQcontent());
	   qcontent.appendChild(qcontentText);
	   q.appendChild(qcontent);
	   
	   Element qcategory = doc.createElement(Tag.QCATEGORYSTAG.replaceAll("<|>", ""));
	   Text qcategoryText = doc.createTextNode(question.getCategory());
	   qcategory.appendChild(qcategoryText);
	   q.appendChild(qcategory);
	   
	   Element tword = doc.createElement(Tag.TWORDSTAG.replaceAll("<|>", ""));
	   Text twordText = doc.createTextNode(question.getTword());
	   tword.appendChild(twordText);
	   q.appendChild(tword);
	   
	   Element cword = doc.createElement(Tag.CWORDSTAG.replaceAll("<|>", ""));
	   Text cwordText = doc.createTextNode(question.getCword());
	   cword.appendChild(cwordText);
	   q.appendChild(cword);
	   
	   Element answers = doc.createElement(Tag.ANSWERSSTAG.replaceAll("<|>", ""));
	   q.appendChild(answers);
	   
	   for(int i=0;i<answerList.size();i++)
	   {
		   Element answer = doc.createElement(Tag.ANSWERSTAG.replaceAll("<|>", ""));
		   answers.appendChild(answer);
		   
		   Element aid = doc.createElement(Tag.AIDSTAG.replaceAll("<|>", ""));
		   Text aidText = doc.createTextNode(answerList.get(i).getAid());
		   aid.appendChild(aidText);
		   answer.appendChild(aid);
		   
		   Element acontent = doc.createElement(Tag.ACONTENTSTAG.replaceAll("<|>", ""));
		   Text acontentText = doc.createTextNode(answerList.get(i).getAcontent());
		   acontent.appendChild(acontentText);
		   answer.appendChild(acontent);
		   
		   Element words = doc.createElement(Tag.WORDSSTAG.replaceAll("<|>", ""));
		   Text wordsText = doc.createTextNode(answerList.get(i).getWords());
		   words.appendChild(wordsText);
		   answer.appendChild(words);
		   
		   Element isbest = doc.createElement(Tag.ISBESTSTAG.replaceAll("<|>", ""));
		   Text isbestText = doc.createTextNode(answerList.get(i).getIsbest());
		   isbest.appendChild(isbestText);
		   answer.appendChild(isbest);
	   }
	   doc.appendChild(q);
	  
	   //name.setAttribute("id", "2");
	
	   doc2XmlFile(doc,question.getQid()+".xml");
	}

	public static boolean doc2XmlFile(Document document, String filename)
	{
		boolean flag = true;
		try {
		    TransformerFactory tFactory = TransformerFactory.newInstance();
		    Transformer transformer = tFactory.newTransformer();
		    /** 编码 */
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    DOMSource source = new DOMSource(document);
		    //StreamResult result = new StreamResult(new File("Docs\\"+filename));
		    StreamResult result = new StreamResult(new File(Config.DOCS_PATH_STRING + filename));
		    transformer.transform(source, result);
		    }catch (Exception ex) {
		    flag = false;
		    ex.printStackTrace();
		   }
	   return flag;
	}
}