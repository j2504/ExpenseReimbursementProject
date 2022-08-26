package com.revature.expense.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.expense.dao.ExpenseReimbursementDAOImpl;
import com.revature.expense.model.Users;

/**
 * Servlet implementation class LoginPage
 */
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String userName = request.getParameter("username");
		String psw = request.getParameter("password");
		
		session.setAttribute("userName",userName);
		
		Users u = erDI.login(userName, psw);
		RequestDispatcher reqD;
		if(psw.equals(u.getPassword()) && u.getUser_role().equalsIgnoreCase("Employee")) {
			reqD = request.getRequestDispatcher("/EA");
			response.setStatus(200);
			reqD.forward(request, response);
			
		}else if(psw.equals(u.getPassword()) && u.getUser_role().equalsIgnoreCase("Manager")) {
			reqD = request.getRequestDispatcher("/MA");
			response.setStatus(200);
			reqD.forward(request, response);
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Incorrect Username or Password...');");
			out.println("location='Home.html';");
			out.println("</script>");
			
		}
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request, response);
	}
}
