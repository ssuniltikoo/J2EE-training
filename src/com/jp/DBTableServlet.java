package com.jp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBTableServlet
 */
@WebServlet("/DBTableServlet")
public class DBTableServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DAL data = new DAL();
		ArrayList<String> list =data.getDbTables();
		System.out.println(list);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.write("<html>");
		for (Object tables : list) {
			out.write("<a href=/DBProject/DBTableDatServlet?tablename="+tables+">"+tables+"<a>");
			out.write("<br>");
		}
		out.write("</html>");
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
