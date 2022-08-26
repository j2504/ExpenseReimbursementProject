package com.revature.expense.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class Reimbursement {
	private int reimbursementId;
	private String accepted;
	private double amount;
	private String description;
	private String reimbursementType;
	private Date resolveDate;
	private Time resolveTime;
	private String resolved;
	private Date submitDate;
	private Time submitTime;
	private int authorID;
	private int resolved_id;
	
	
	
	
	public Reimbursement() {
		super();
	}
	
	
	
	
	
	public Reimbursement(String accepted, Date resolveDate, Time resolveTime, String resolved) {
		super();
		this.accepted = accepted;
		this.resolveDate = resolveDate;
		this.resolveTime = resolveTime;
		this.resolved = resolved;
		this.resolved_id = resolved_id;
	}





	public Reimbursement(int reimbursementId, String accepted, double amount, String description,
			String reimbursementType, Date resolveDate, Time resolveTime, String resolved, Date submitDate,
			Time submitTime, int authorID, int resolved_id) {
		super();
		this.reimbursementId = reimbursementId;
		this.accepted = accepted;
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.resolveDate = resolveDate;
		this.resolveTime = resolveTime;
		this.resolved = resolved;
		this.submitDate = submitDate;
		this.submitTime = submitTime;
		this.authorID = authorID;
		this.resolved_id = resolved_id;
	}

	public Reimbursement(double amount, String description, String reimbursementType, Date submitDate, Time submitTime) {
		super();
		this.submitDate = submitDate;
		this.submitTime = submitTime;
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReimbursementType() {
		return reimbursementType;
	}
	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}
	
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getResolved_id() {
		return resolved_id;
	}
	public void setResolved_id(int resolved_id) {
		this.resolved_id = resolved_id;
	}
	public Date getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public void setResolveTime(Time resolveTime) {
		this.resolveTime = resolveTime;
	}
	public Time getResolveTime() {
		return resolveTime;
	}
	public void setSubmitTime(Time submitTime) {
		this.submitTime = submitTime;
	}
	
	public Time getSubmitTime() {
		return submitTime;
	}



	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", accepted=" + accepted + ", amount=" + amount
				+ ", description=" + description + ", reimbursementType=" + reimbursementType + ", resolveDate="
				+ resolveDate + ", resolveTime=" + resolveTime + ", resolved=" + resolved + ", submitDate=" + submitDate
				+ ", submitTime=" + submitTime + ", authorID=" + authorID + ", resolved_id=" + resolved_id + "]";
	}
	
	
}
