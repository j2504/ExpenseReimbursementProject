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
import com.revature.expense.dao.ManagerDAOImpl;

/**
 * Servlet implementation class MChangeUserRole
 */
public class MChangeUserRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MChangeUserRole() {
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
		ManagerDAOImpl mdi = new ManagerDAOImpl();
		PrintWriter out = response.getWriter();
		String userName = (String) session.getAttribute("userName");
		String userID = request.getParameter("userid");
		String role = request.getParameter("role");
		
		mdi.changeUserRole(role, Integer.parseInt(userID));
		
		RequestDispatcher rd;
		if(userName != null) {
			response.setStatus(200);
			out.print("<script> alert('User: "+userID+" role has successfully been changed!'); </script>");
			rd = request.getRequestDispatcher("MRR");
			rd.include(request, response);
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Error');");
			out.println("location='MRR';");
			out.println("</script>");
		}
	}

}
