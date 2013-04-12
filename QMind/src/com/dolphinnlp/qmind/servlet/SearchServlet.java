package com.dolphinnlp.qmind.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dolphinnlp.qmind.config.Config;
import com.dolphinnlp.qmind.config.Config.VAR;
import com.dolphinnlp.qmind.lucene.LuceneSearcher;
import com.dolphinnlp.qmind.model.QA;

public class SearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private LuceneSearcher searcher;
	public SearchServlet() {
		super();
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
			String query = request.getParameter("searchTextField");
	        //LuceneSearcher s = new LuceneSearcher(Config.getValue(VAR.INDEX_PATH_STRING));
	        ArrayList<QA> ans = searcher.queryByQTitle(query);

	        
			session.setAttribute("ansList", ans);
			response.sendRedirect("rank.jsp");
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
