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
import com.revature.expense.model.Users;

/**
 * Servlet implementation class ManagerEmployeeView
 */
public class ManagerEmployeeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerEmployeeView() {
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
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String userN = (String) session.getAttribute("userName");
		pageHeader(response);
		List<Users> u = new ArrayList<Users>();
		ManagerDAOImpl mDI = new ManagerDAOImpl();
		u = mDI.getAllUsers();
		PrintWriter out = response.getWriter();
		if(userN != null) {
		out.print("<title>Submitted Requests</title>");
		out.print("<div class='formed'><fieldset>");
		out.print("<table>");
		printHeadOfTable(response);
		for(Users us: u ) {
			displayTable(us, response);
		}
		out.print("</table></br>");
		out.print("<button onclick=\"window.location.href='PR'\">Show Pending Requests</button> <button onclick=\"window.location.href='SR'\">Show All Requests</button>");
		out.print("<br><br><form action='MCUR'> <fieldSet>");
		changeEmployeeRole(response);
		out.print("</fieldSet></form></div>");
		}else {
			out.close();
		}
	}

	public void printHeadOfTable(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
	
		out.print("<tr><th>User_id</th>"+ " "+"<th >user_role</th>"+" "+"<th> email</th>"+" "+"<th>firstname</th><th>lastname</th><th>password</th>"
				+"<th>username</th>");
		out.print("</tr>");	
	}
	
	public void displayTable(Users u,HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		out.print("<tr>");
		out.print("<td>" + u.getUser_id()+ "</td>");
		out.print("<td>" + u.getUser_role()+ "</td>");
		out.print("<td>" + u.getEmail()+ "</td>");
		out.print("<td>" + u.getFirstName()+ "</td>");
		out.print("<td>" + u.getLastName()+ "</td>");
		out.print("<td>" + u.getPassword()+ "</td>");
		out.print("<td>" + u.getUsername()+ "</td>");

	}
	
	protected void pageHeader(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<link rel='stylesheet' href='Header.css'>");
		out.print("<link rel='stylesheet' href='Request.css'>");
		out.print("<div class=\"header\">\r\n" + "        <a href=\"EA\" class=\"logo\">Revature</a>\r\n"
				+ "        <div class=\"header-right\">\r\n"
				+ "        <a href=\"MRR\" >Employees</a> \r\n"
				+ "        <a href=\"SR\" >Submitted Requests</a> \r\n" + "        <a href=\"MA\" >Account</a>\r\n"
				+ "          \r\n" + "        </div>\r\n" + "    </div>");

	
	}
	
	protected void changeEmployeeRole(HttpServletResponse resp) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print("<label for=\"userid\">Input the user_id of the Employee:</label>\r\n"
				+ "    <input type=\"number\" name=\"userid\" placeholder=\"Enter Emloyee user_id\" min=\"1\" required>\r\n"
				+ "    <br><br>\r\n"
				+ "    <label for=\"role\">Choose a role:</label>\r\n"
				+ "    <input type=\"radio\" name=\"role\" value='Employee' default >Employee\r\n"
				+ "    <input type=\"radio\" name=\"role\" value='Manager'>Manager\r\n"
				+ "    <br><br>\r\n"
				+ "    <button type=\"submit\">Change Employee Role</button> <button onclick=\"window.location.href='MA'\">No</button>");
		
	}

}
