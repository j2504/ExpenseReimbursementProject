package com.revature.expense.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.revature.expense.model.Reimbursement;
import com.revature.expense.model.Users;
import com.revature.expense.utils.ConnectionUtils;


public class ExpenseReimbursementDAOImpl implements ExpenseReimbursementDAO {

	//User login
	@Override
	public Users login(String userName , String password) {
		// TODO Auto-generated method stub
		Users u = new Users();
		
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement userLogin = conn.prepareStatement("Select * from users where username = ? and password=?");
			userLogin.setString(1, userName);
			userLogin.setString(2, password);
			ResultSet rs = userLogin.executeQuery();
			if(rs.next()) {
				u.setUser_id(rs.getInt(1));
				u.setUser_role(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setUsername(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int registerNewAcct(Users u) {
		// TODO Auto-generated method stub
		int newAcctId = 0;
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement userRegister = conn.prepareStatement("insert into users (user_role, email, firstname, lastname, password, username) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			userRegister.setString(1, u.getUser_role());
			userRegister.setString(2, u.getEmail());
			userRegister.setString(3, u.getFirstName());
			userRegister.setString(4, u.getLastName());
			userRegister.setString(5, u.getPassword());
			userRegister.setString(6, u.getUsername());
			userRegister.execute();
			
			ResultSet rs = userRegister.getGeneratedKeys();
			if(rs.next()) {
				newAcctId = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAcctId;
		
	}

	@Override
	public void submitReTicket( Reimbursement r, String userName) {
		
		// TODO Auto-generated method stub
		
		
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();

			PreparedStatement getId = conn.prepareStatement("select user_id from users where username=?");
			getId.setString(1, userName);
			getId.executeQuery();
			ResultSet rs = getId.getResultSet();
			if(rs.next()) {
			r.setAuthorID(rs.getInt(1));
			}
			PreparedStatement userSubT = conn.prepareStatement("insert into reimbursement(amount , description, reimbursement_type,author_id, submit_time, submit_date) values (?,?,?,?,?,?)");
			userSubT.setDouble(1, r.getAmount());
			userSubT.setString(2, r.getDescription());
			userSubT.setString(3, r.getReimbursementType());
			userSubT.setInt(4, r.getAuthorID());
			userSubT.setTime(5, r.getSubmitTime());
			userSubT.setDate(6, r.getSubmitDate());
			
			userSubT.executeQuery();
			
	
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	@Override
	public void deleteUser( String userName) {
		// TODO Auto-generated method stub
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement userDelete = conn.prepareStatement("delete from users where username = ?");
			userDelete.setString(1, userName);
			userDelete.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editUser(Users u , String userName) {
		// TODO Auto-generated method stub
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement userUpdate = conn.prepareStatement("update users set firstName = ?, lastName = ?, email=?, password=?,username=?  where username = ?");
			userUpdate.setString(1, u.getFirstName());
			userUpdate.setString(2, u.getLastName());
			userUpdate.setString(3, u.getEmail());
			userUpdate.setString(4, u.getPassword());
			userUpdate.setString(5, u.getUsername());
			userUpdate.setString(6, userName);
			userUpdate.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Users> getUserbyUserName(String userName) {
		// TODO Auto-generated method stub
		List<Users> ul = new ArrayList<Users>();
		Users u = null;

		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select * from users where userName = ? ") ;
			fetchUser.setString(1, userName);
			
			
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
	public Users retrivePsw(String email) {
		// TODO Auto-generated method stub
		Users u = new Users();
		try {
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select password from users where email = ? ") ;
			fetchUser.setString(1, email);
			
			
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
				u.setPassword(rs.getString(1));
				u.setEmail(email);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<Reimbursement> getPendingSubmittedRequests(String userName) {
		// TODO Auto-generated method stub
		List<Reimbursement> pReq = new ArrayList<Reimbursement>();
		Reimbursement r = null;
		
		try {
			int id = 0;
			String p = "Pending";
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("select user_id from users where username = ? ") ;
			fetchUser.setString(1, userName);
			
			
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
			 id = rs.getInt(1);
			}
			
			PreparedStatement fetchUserReq = conn.prepareStatement("select * from reimbursement where author_id=? and resolved=?") ;
			fetchUserReq.setInt(1, id);
			fetchUserReq.setString(2,p);
			
			ResultSet rst = fetchUserReq.executeQuery();
			while(rst.next()) {
				r = new Reimbursement(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getString(4), rst.getString(5), rst.getDate(6), rst.getTime(7),
						rst.getString(8), rst.getDate(9), rst.getTime(10), rst.getInt(11), rst.getInt(12));
				pReq.add(r);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pReq;
	}

	@Override
	public List<Reimbursement> getAllSubmittedRequests(String userName) {
		// TODO Auto-generated method stub
		List<Reimbursement> pReq = new ArrayList<Reimbursement>();
		Reimbursement r = null;
		
		try {
			int id = 0;
			Connection conn = ConnectionUtils.getInstance().getConnection();
			PreparedStatement fetchUser = conn.prepareStatement("Select user_id from users where username = ? ") ;
			fetchUser.setString(1, userName);
			
			
			ResultSet rs = fetchUser.executeQuery();
			while(rs.next()) {
				 id = rs.getInt(1);
			}
			
			PreparedStatement fetchUserReq = conn.prepareStatement("Select * from reimbursement where author_id = ? ") ;
			fetchUserReq.setInt(1, id);
			
			
			ResultSet rs2 = fetchUserReq.executeQuery();
			while(rs2.next()) {
				r = new Reimbursement(rs2.getInt(1), rs2.getString(2), rs2.getDouble(3), rs2.getString(4), rs2.getString(5), rs2.getDate(6), rs2.getTime(7),
						rs2.getString(8), rs2.getDate(9), rs2.getTime(10), rs2.getInt(11), rs2.getInt(12));
				pReq.add(r);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pReq;
		
	}

	
	
}
