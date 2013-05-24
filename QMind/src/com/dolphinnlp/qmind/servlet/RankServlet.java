package com.dolphinnlp.qmind.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dolphinnlp.qmind.config.Config;
import com.dolphinnlp.qmind.config.Config.VAR;
import com.dolphinnlp.qmind.lucene.LuceneSearcher;
import com.dolphinnlp.qmind.model.QA;
import com.dolphinnlp.qmind.wordalignment.TranslateModel;

public class RankServlet extends HttpServlet {
    private TranslateModel model;
	/**
	 * Constructor of the object.
	 */
	private LuceneSearcher searcher;
	public RankServlet() {
		super();
		model = new TranslateModel();
		model.load(Config.getValue(Config.VAR.INDEX_PATH_STRING));
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		searcher.close();
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String qid = request.getParameter("qid").toString();
		String query = request.getParameter("query").toString();
		System.out.println("Search for QID :" + qid);
		ArrayList<QA> ansList = searcher.queryByQId(qid);
		QA ans = new QA();
		for (QA qa : ansList)
		{
			if (qa.getQuestion().getQid().equals(qid)){
				ans = qa;
				break;
			}
		}
		String words = ans.getAnswers().get(0).getWords();
		String query_new = "";
		int cnt = 0;
		for (String word : words.split("/n"))
		{
			String tmp = word.split(" ")[word.split(" ").length - 1];
			if (tmp.indexOf("/") >= 0) continue; 
			if (query_new.indexOf(tmp) < 0)
			{
			    if (tmp.matches("[0-9|A-Z|a-z]+")) {
			        continue;
			    }
				query_new += " " + tmp;
				cnt += 1;
				if (cnt == 5) break;
			}
		}
		query_new = query_new.substring(1);
		ArrayList<QA> ansList_new = searcher.queryByQTitle(query_new);
		for (QA qa : ansList_new) {
		    qa.setScore(qa.getScore() + model.getProb(words, qa.getQuestion().getTword()));
		}
		Collections.sort(ansList_new, new Comparator<QA>() {

		    public int compare(QA o1, QA o2) {
		        return Double.compare(o1.getScore(), o2.getScore());
		    }

		});
		session.setAttribute("ans", ans);
		session.setAttribute("ansList_new", ansList_new);
		session.setAttribute("query_new", query_new);
		response.sendRedirect("result.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		searcher = new LuceneSearcher(Config.getValue(VAR.INDEX_PATH_STRING));
		// Put your code here
	}

}
