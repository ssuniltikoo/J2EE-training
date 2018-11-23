package com.jp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBTableDatServlet
 */
@WebServlet("/DBTableDatServlet")
public class DBTableDatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query =request.getParameter("tablename");		
		DAL data = new DAL();
		ArrayList<String[]> list = data.getTableData(query);	
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.write("<html>");
		out.write("<table border='2'>");
		for (String[] tablesdata : list) {
			out.write("<tr>");
			for(int i = 0 ; i <tablesdata.length;i++)
				out.write("<td>"+tablesdata[i]+"</td>");
			
			out.write("</tr>");
		}
		out.write("</table>");
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
