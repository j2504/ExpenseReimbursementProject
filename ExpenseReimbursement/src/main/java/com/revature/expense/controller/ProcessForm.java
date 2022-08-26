package com.revature.expense.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.expense.dao.ManagerDAOImpl;
import com.revature.expense.model.Reimbursement;

/**
 * Servlet implementation class processForm
 */
public class ProcessForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessForm() {
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
		ManagerDAOImpl mDI = new ManagerDAOImpl();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String resolvedName = (String) session.getAttribute("userName");
		String rem_id = request.getParameter("remId");
		String accept = request.getParameter("accept");
		String resolved = request.getParameter("resolved");
		Date d = new Date();
		java.sql.Date resolvedDate = new java.sql.Date(d.getTime());
		java.sql.Time resolvedTime = new java.sql.Time(d.getTime());
		
		mDI.proccessTicket(new Reimbursement(accept, resolvedDate, resolvedTime,resolved), resolvedName, Integer.parseInt(rem_id));
		RequestDispatcher reqD;
		if(resolvedName != null) {
			response.setStatus(200);
			reqD = request.getRequestDispatcher("/SR");
			reqD.forward(request, response);
			
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User Session invalid!');");
			out.println("location='Home.html';");
			out.println("</script>");	
				
			}
		
	}

}
