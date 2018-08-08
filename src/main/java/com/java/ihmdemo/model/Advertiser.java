package com.java.ihmdemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * 	Entity for API Demo
 * 	@author Pravesh Jaiswal <praveshjaiswal@gmail.com>
 * 	Date: 08/06/2018
 * 
 */


@Entity
public class Advertiser {
	
	@Id
	private String advContactName;
	private String advName;
	private Long advCreditLimit;

	public Advertiser(){
		this.advContactName="";
		this.advName="";
		this.advCreditLimit=(long)0;
		
	}
	
	public Advertiser(String advContactName, String advName, long advCreditLimit) {
		// TODO Auto-generated constructor stub
		this.advContactName=advContactName;
		this.advName=advName;
		this.advCreditLimit=advCreditLimit;
	}
	public String getAdvContactName() {
		return advContactName;
	}
	public void setAdvContactName(String advContactName) {
		this.advContactName = advContactName;
	}
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public Long getAdvCreditLimit() {
		return advCreditLimit;
	}
	public void setAdvCreditLimit(Long advCreditLimit) {
		this.advCreditLimit = advCreditLimit;
	}
	
}
