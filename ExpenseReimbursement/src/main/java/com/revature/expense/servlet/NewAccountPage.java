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
 * Servlet implementation class NewAccountPage
 */
public class NewAccountPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String userName = request.getParameter("username");
		String psw = request.getParameter("password");
		String role = request.getParameter("role");
		
		int userId= erDI.registerNewAcct(new Users(role,email,fname,lname,psw,userName));
		
		RequestDispatcher reqD;
		PrintWriter out = response.getWriter();
		if(userId > 0) {
			reqD = request.getRequestDispatcher("/Home.html");
			response.setStatus(200);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You have successfully created an Account!');");
			out.println("</script>");
			
			reqD.include(request, response);
		}else {
			response.setStatus(400);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username Already Exists!');");
			out.println("location='Signup.html';");
			out.println("</script>");
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}