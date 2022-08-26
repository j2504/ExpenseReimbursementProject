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

/**
 * Servlet implementation class DeleteAcct
 */
public class EDeleteAcct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EDeleteAcct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequestt(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequestt(request, response);
	}
	
	protected void processRequestt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		String userName = (String) session.getAttribute("userName");
		erDI.deleteUser(userName);
		RequestDispatcher reqD;
		if(userName != null) {
			
			session.invalidate();
			reqD = request.getRequestDispatcher("Home.html");
			response.setStatus(200);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('You have Account has been deleted!');");
			out.println("</script>");
			
			reqD.include(request, response);
			
		}else {
			response.setStatus(400);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error. Try Again!');");
			out.println("location='EDeleteAcct.html';");
			out.println("</script>");
		}
		
	}

}
