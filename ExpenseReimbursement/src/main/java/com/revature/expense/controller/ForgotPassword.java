package com.revature.expense.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.expense.dao.ExpenseReimbursementDAOImpl;
import com.revature.expense.model.Users;

/**
 * Servlet implementation class ForgotPassword
 */
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processedRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processedRequest(request, response);
	}
	
	protected void processedRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		Users u = erDI.retrivePsw(email);
		String psw = u.getPassword();
		if(u.getEmail() != null) {
			out.print("<script> alert('The password associated with "+email+": "+psw+"'); </script>");
			rd = request.getRequestDispatcher("Home.html");
			response.setStatus(200);
			rd.include(request, response);
			
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Email Not Found..');");
			out.println("location='ForgotPsw.html';");
			out.println("</script>");
		}
	}

}
