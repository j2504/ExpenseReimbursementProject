package com.revature.expense.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.expense.dao.ExpenseReimbursementDAOImpl;
import com.revature.expense.model.Users;

/**
 * Servlet implementation class EAccount
 */
public class EAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		pageHeader(response);
		
		printAccountInfo(request, response);
	}

	
	//Print Employee Info
	protected void printAccountInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userN = (String) session.getAttribute("userName");
		ExpenseReimbursementDAOImpl e = new ExpenseReimbursementDAOImpl();
		List<Users> userInfo = new ArrayList<Users>();
		userInfo = e.getUserbyUserName(userN);
		if(userN != null) {
		out.print("<h1 style='text-align:center'>Welcome, " + userN + "</h1>");
		out.print("<form action='ELogOut.html' method='post'><fieldSet><h3 style='text-align:center'>Account Info</h3>");
		for (Users u : userInfo) {
			out.print("<h4>First Name : " + u.getFirstName() + "</h4>");
			out.print("<h4>Last Name : " + u.getLastName() + "</h4> ");
			out.print("<h4>Email : " + u.getEmail() + "</h4> ");
			out.print("<h4>Role : " + u.getUser_role() + "</h4> ");
			out.print("<h4>UserName : " + u.getUsername() + "</h4> ");
			out.print("<button type=\"button\" onclick=\"window.location.href='EUpdateAcct.html'\">Update</button>  <button type=\"button\" onclick=\"window.location.href='EDeleteAcct.html'\">Delete</button>");
			out.print("<button type='Submit' style='float:right'>LogOut</button></fieldSet> </form>");
		
		}
		session.setMaxInactiveInterval(60*5);
		}else {
			out.print("<form action='Home.html' method='post'><fieldSet><h3 style='text-align:center'>Account Info</h3>");
			out.print("<button type='Submit' >LogOut</button></fieldSet> </form>");
		}
	}
	
	//Page Header
	protected void pageHeader(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<link rel='stylesheet' href='Header.css'>");
		out.print("<link rel='stylesheet' href='Account.css'>");
		out.print("<div class=\"header\">\r\n" + "        <a href=\"EA\" class=\"logo\">Revature</a>\r\n"
				+ "        <div class=\"header-right\">\r\n"
				+ "        <a href=\"Reimbursement.html\" >Request Form</a> \r\n"
				+ "        <a href=\"ELog\" > View Submitted Requests</a> \r\n" + "        <a href=\"EA\" >Account</a>\r\n"
				+ "          \r\n" + "        </div>\r\n" + "    </div>");

	
	}

}
