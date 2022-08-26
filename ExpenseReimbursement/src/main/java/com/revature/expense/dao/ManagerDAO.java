package com.revature.expense.dao;

import java.util.List;

import com.revature.expense.model.Reimbursement;
import com.revature.expense.model.Users;

public interface ManagerDAO {
	
	/*READ*/
	//Get All pending requests from the database
	public List<Reimbursement>getPendingRequests();
	
	//Get All requests from database
	public List<Reimbursement>getAllRequests();
	
	//Get All Users from the database
	public List<Users> getAllUsers();
	
	
	/*Update*/
	//Manager process Ticket
	public void proccessTicket(Reimbursement r, String userName, int rem_id);
	
	//Manager change user role
	public void changeUserRole(String userRole,int user_id);
	
}
