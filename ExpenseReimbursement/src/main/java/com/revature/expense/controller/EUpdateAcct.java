package com.revature.expense.controller;

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
 * Servlet implementation class UpdateAcct
 */
public class EUpdateAcct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EUpdateAcct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String userName = request.getParameter("username");
		String psw = request.getParameter("password");
		String ouserName = (String) session.getAttribute("userName");
		
		erDI.editUser(new Users(email,fname,lname,psw,userName), ouserName);
		
		RequestDispatcher reqD;
		PrintWriter out = response.getWriter();
		if(ouserName != null) {
			session.removeAttribute(ouserName);
			session.setAttribute("userName",userName );
			reqD = request.getRequestDispatcher("EA");
			response.setStatus(200);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You have successfully updated your Account!');");
			out.println("</script>");
			
			reqD.include(request, response);
		}else {
			response.setStatus(400);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User name already exist');");
			out.println("location='EUpdateAcct.html';");
			out.println("</script>");
		}

	
	}

}
