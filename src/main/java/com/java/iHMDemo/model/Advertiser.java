package com.java.iHMDemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Advertiser {
	
	@Id
	private String advContactName;
	private String advName;
	private Long advCreditLimit;

	
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
