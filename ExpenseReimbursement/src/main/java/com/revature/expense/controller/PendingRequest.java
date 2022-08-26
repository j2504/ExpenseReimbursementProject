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

import com.revature.expense.dao.ManagerDAOImpl;
import com.revature.expense.model.Reimbursement;

/**
 * Servlet implementation class PendingRequest
 */
public class PendingRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingRequest() {
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
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String userN = (String) session.getAttribute("userName");
		pageHeader(response);
		List<Reimbursement> r = new ArrayList<Reimbursement>();
		ManagerDAOImpl mDI = new ManagerDAOImpl();
		r = mDI.getPendingRequests();
		PrintWriter out = response.getWriter();
		if(userN != null) {
		response.setStatus(200);
		out.print("<title>Submitted Requests</title>");
		out.print("<div class='formed'><fieldset>");
		out.print("<table>");
		printHeadOfTable(response);
		for(Reimbursement re: r ) {
			displayTable(re, response);
		}
		out.print("</table></br>");
		out.print("<button onclick=\\\"window.location.href='PR'\\\">Show Pending Requests</button> <a href='SR' class='all'>Show All Requests</a>");
		out.print("<br><br><form action='pF'> <fieldSet>");
		managerForm(response);
		out.print("</fieldSet></form></div>");
		}else {
			out.close();
		}
	}

	public void printHeadOfTable(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
	
		out.print("<tr><th>Reim_id</th>"+ " "+"<th >Accepted</th>"+" "+"<th> Amount</th>"+" "+"<th> Descr</th><th> Reim_type</th><th> Resolved_date</th>"
				+"<th> Resolved_time</th><th> Resolved</th><th> Submit_Date</th><th>Submit_Time</th><th> Author_id</th><th> Resolved_id</th>");
		out.print("</tr>");	
	}
	
	public void displayTable(Reimbursement r,HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		out.print("<tr>");
		out.print("<td>" + r.getReimbursementId()+ "</td>");
		out.print("<td>" + r.getAccepted()+ "</td>");
		out.print("<td>" + r.getAmount()+ "</td>");
		out.print("<td>" + r.getDescription()+ "</td>");
		out.print("<td>" + r.getReimbursementType()+ "</td>");
		out.print("<td>" + r.getResolveDate()+ "</td>");
		out.print("<td>" + r.getResolveTime()+ "</td>");
		out.print("<td>" + r.getResolved()+ "</td>");
		out.print("<td>" + r.getSubmitDate()+ "</td>");
		out.print("<td>" + r.getSubmitTime()+ "</td>");
		out.print("<td>" + r.getAuthorID()+ "</td>");
		out.print("<td>" + r.getResolved_id()+ "</td>");

	}
	
	protected void pageHeader(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<link rel='stylesheet' href='Header.css'>");
		out.print("<link rel='stylesheet' href='Request.css'>");
		out.print("<div class=\"header\">\r\n" + "        <a href=\"EA\" class=\"logo\">Revature</a>\r\n"
				+ "        <div class=\"header-right\">\r\n"
				+ "        <a href=\"MRR\" >Reimbursement Request</a> \r\n"
				+ "        <a href=\"SR\" >Submitted Requests</a> \r\n" + "        <a href=\"MA\" >Account</a>\r\n"
				+ "          \r\n" + "        </div>\r\n" + "    </div>");

	
	}

	protected void managerForm(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<label for=\"rem_id\">Input Rem_id for the Employee Request:</label>\r\n"
				+ "    <input type=\"number\" name=\"remId\" placeholder=\"Enter Rem_id\" min=\"1\" required>\r\n"
				+ "    <br><br>\r\n"
				+ "    <label for=\"accept\">Accept Request:</label>\r\n"
				+ "    <input type=\"radio\" name=\"accept\" value=\"Yes\" checked/>Yes\r\n"
				+ "    <input type=\"radio\" name=\"accept\" value=\"No\"/>No\r\n"
				+ "    <br><br>\r\n"
				+ "    <label for=\"resolved\">Mark as Resolved</label>\r\n"
				+ "    <input type=\"radio\" name=\"resolved\" value=\"Resolved\" required/>Yes\r\n"
				+ "    <input type=\"radio\" name=\"resolved\" value=\"Pending\" required/>No\r\n"
				+ "    <br><br>\r\n"
				+ "    <button type=\"submit\">Process Request</button>");
		
	}
}
