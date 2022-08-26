package com.revature.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.expense.model.Reimbursement;
import com.revature.expense.model.Users;
import com.revature.expense.utils.ConnectionUtils;

public class ManagerDAOImpl implements ManagerDAO {


	@Override
	public List<Reimbursement> getPendingRequests() {
		// TODO Auto-generated method stub
		List<Reimbursement> pReq = new ArrayList<Reimbursement>();
		Reimbursement r = null;
		String pending ="Pending";
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select * from reimbursement where resolved = ? ") ;
			fetchUser.setString(1, pending);
			
			
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
				r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getTime(7),
						rs.getString(8), rs.getDate(9), rs.getTime(10), rs.getInt(11), rs.getInt(12));
				pReq.add(r);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pReq;

		
	}

	
	@Override
	public List<Reimbursement> getAllRequests() {
		// TODO Auto-generated method stub
		List<Reimbursement> pReq = new ArrayList<Reimbursement>();
		Reimbursement r = null;

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select * from reimbursement") ;
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
				r = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getTime(7),
						rs.getString(8), rs.getDate(9), rs.getTime(10), rs.getInt(11), rs.getInt(12));
				pReq.add(r);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return pReq;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> ul = new ArrayList<Users>();
		Users u = null;

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select * from users") ;
			
			
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
				u = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				ul.add(u);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ul;
	}

	@Override
	public void proccessTicket(Reimbursement r, String userName, int rem_id) {
		// TODO Auto-generated method stub
		ExpenseReimbursementDAOImpl er = new ExpenseReimbursementDAOImpl();
		
		int subId = 0;
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUserId = conn.prepareStatement("Select user_id from users where userName = ? ") ;
			fetchUserId.setString(1, userName);
			ResultSet rd = fetchUserId.executeQuery();
			if(rd.next()) {
				r.setResolved_id(rd.getInt(1));
			}
			
			PreparedStatement userSubT = conn.prepareStatement("Update reimbursement set accepted=?,resolved_date=?,resolvetime=?,resolved=?,resolved_id=? where reimbursement_id = ?");
			userSubT.setString(1, r.getAccepted());
			userSubT.setDate(2, r.getResolveDate());
			userSubT.setTime(3, r.getResolveTime());
			userSubT.setString(4, r.getResolved());
			userSubT.setInt(5, r.getResolved_id());
			userSubT.setInt(6, rem_id);
			userSubT.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	@Override
	public void changeUserRole(String userRole,int user_id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement changeRole = conn.prepareStatement("Update users set user_role=? where user_id = ?");
			changeRole.setString(1,userRole);
			changeRole.setInt(2,user_id);
			changeRole.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
