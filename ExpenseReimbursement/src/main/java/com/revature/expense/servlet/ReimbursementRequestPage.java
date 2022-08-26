package com.revature.expense.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.expense.dao.ExpenseReimbursementDAOImpl;
import com.revature.expense.model.Reimbursement;

/**
 * Servlet implementation class ReimbursementRequestPage
 */
public class ReimbursementRequestPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ExpenseReimbursementDAOImpl e = new ExpenseReimbursementDAOImpl();
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		
		String amount = request.getParameter("amount");
		String desc = request.getParameter("description");
		String type = request.getParameter("type");
		Date d = new Date();
		java.sql.Date submitDate = new java.sql.Date(d.getTime());
		java.sql.Time submitTime = new java.sql.Time(d.getTime());
		
		String username = (String) session.getAttribute("userName");
		e.submitReTicket(new Reimbursement(Double.parseDouble(amount), desc, type, submitDate, submitTime),username);
		RequestDispatcher reqD;
		if(username != null) {
			
			response.setStatus(200);
			reqD = request.getRequestDispatcher("/EA");
			reqD.forward(request, response);
		}else {
			response.setStatus(400);
			response.getWriter().write("Username not found");
			reqD = request.getRequestDispatcher("Reimbursement.html");
			reqD.forward(request, response);
		}
		
	
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
