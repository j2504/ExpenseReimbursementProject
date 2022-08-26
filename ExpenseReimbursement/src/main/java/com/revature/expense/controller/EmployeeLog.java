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
import com.revature.expense.dao.ManagerDAOImpl;
import com.revature.expense.model.Reimbursement;

/**
 * Servlet implementation class EmployeeLog
 */
public class EmployeeLog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeLog() {
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
		HttpSession session = request.getSession();
		String userN = (String) session.getAttribute("userName");
		pageHeader(response);
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		ExpenseReimbursementDAOImpl erDI = new ExpenseReimbursementDAOImpl();
		r = erDI.getAllSubmittedRequests(userN);
		PrintWriter out = response.getWriter();
		if (userN != null) {
			out.print("<title>Submitted Requests</title>");
			out.print("<div class='formed'><fieldset>");
			out.print("<table>");
			printHeadOfTable(response);
			for (Reimbursement re : r) {
				displayTable(re, response);
			}
			out.print("</table></br>");
			out.print(
					"<button onclick=\"window.location.href='EPR'\">Show Pending Requests</button> <button onclick=\"window.location.href='ELog'\">Show All Requests</button>");

		} else {
			out.close();
		}

	}

	public void printHeadOfTable(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();

		out.print("<tr><th>Reim_id</th>" + " " + "<th >Accepted</th>" + " " + "<th> Amount</th>" + " "
				+ "<th> Descr</th><th> Reim_type</th><th> Resolved_date</th>"
				+ "<th> Resolved_time</th><th> Resolved</th><th> Submit_Date</th><th>Submit_Time</th><th> Author_id</th><th> Resolved_id</th>");
		out.print("</tr>");
	}

	public void displayTable(Reimbursement r, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		out.print("<tr>");
		out.print("<td>" + r.getReimbursementId() + "</td>");
		out.print("<td>" + r.getAccepted() + "</td>");
		out.print("<td>" + r.getAmount() + "</td>");
		out.print("<td>" + r.getDescription() + "</td>");
		out.print("<td>" + r.getReimbursementType() + "</td>");
		out.print("<td>" + r.getResolveDate() + "</td>");
		out.print("<td>" + r.getResolveTime() + "</td>");
		out.print("<td>" + r.getResolved() + "</td>");
		out.print("<td>" + r.getSubmitDate() + "</td>");
		out.print("<td>" + r.getSubmitTime() + "</td>");
		out.print("<td>" + r.getAuthorID() + "</td>");
		out.print("<td>" + r.getResolved_id() + "</td>");

	}

	protected void pageHeader(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.print("<link rel='stylesheet' href='Header.css'>");
		out.print("<link rel='stylesheet' href='Request.css'>");
		out.print("<div class=\"header\">\r\n" + "        <a href=\"EA\" class=\"logo\">Revature</a>\r\n"
				+ "        <div class=\"header-right\">\r\n"
				+ "        <a href=\"Reimbursement.html\" >Request Form</a> \r\n"
				+ "        <a href=\"ELog\" > View Submitted Requests</a> \r\n"
				+ "        <a href=\"EA\" >Account</a>\r\n" + "          \r\n" + "        </div>\r\n" + "    </div>");

	}

}
