package com.jp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("txtname");
				String password = request.getParameter("txtPassword");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.write("<html><h1>hello</h1></html>");						
				DAL dal= new DAL();
				if(dal.ValidateUser(username,password)){
					out.write(username + "<h1> Your authentication is successfull<h1>");
					response.sendRedirect("/DBProject/DBTableServlet");
				}
				else				
					out.write("<h1>InValid user<h1>");
				
				out.flush();
		
	}
	
	


}
