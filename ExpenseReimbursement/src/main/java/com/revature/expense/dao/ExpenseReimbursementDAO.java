package com.revature.expense.dao;

import java.util.List;

import com.revature.expense.model.Reimbursement;
import com.revature.expense.model.Users;

public interface ExpenseReimbursementDAO {
	
	/*CREATE*/
	//User Create New Account
	public int registerNewAcct(Users u);
	
	//Employee submit Reimbursement
	public void submitReTicket(Reimbursement r,String userName);
	
	/*READ*/
	// User Login 
	public Users login(String UserName, String password);

	//Get all User info by user name
	public List<Users> getUserbyUserName(String userName);
	
	//Retrieve user password
	public Users retrivePsw(String email);
	
	//Employee get all sumbitted requests
	public List<Reimbursement> getAllSubmittedRequests(String userName);
	
	//Employee get all pending requests
	public List<Reimbursement> getPendingSubmittedRequests(String userName);
	
	
	/*UPDATE*/
	//User Edit account
	public void editUser(Users u, String userName);
	
	/*DELETE*/
	//User Delete Account 
	public void deleteUser(String userName);
	
}