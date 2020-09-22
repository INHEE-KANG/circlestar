package com.nbbang.customer.model.vo;

import java.sql.Date;
import java.util.Arrays;

public class CustomerCenter {

	private int csNo;
	private String csWriter;
	private String csType;
	private String csTitle;
	private String csContent;
	private Date csDate;
	private Boolean csIscheck;
	private String csNickname;
	private String csAnswer;
	
	public CustomerCenter() {
		// TODO Auto-generated constructor stub
	}

	public CustomerCenter(int csNo, String csWriter, String csType, String csTitle, String csContent, Date csDate,
			Boolean csIscheck, String csNickname, String csAnswer) {
		super();
		this.csNo = csNo;
		this.csWriter = csWriter;
		this.csType = csType;
		this.csTitle = csTitle;
		this.csContent = csContent;
		this.csDate = csDate;
		this.csIscheck = csIscheck;
		this.csNickname = csNickname;
		this.csAnswer = csAnswer;
	}

	public int getCsNo() {
		return csNo;
	}

	public void setCsNo(int csNo) {
		this.csNo = csNo;
	}

	public String getCsWriter() {
		return csWriter;
	}

	public void setCsWriter(String csWriter) {
		this.csWriter = csWriter;
	}

	public String getCsType() {
		return csType;
	}

	public void setCsType(String csType) {
		this.csType = csType;
	}

	public String getCsTitle() {
		return csTitle;
	}

	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}

	public String getCsContent() {
		return csContent;
	}

	public void setCsContent(String csContent) {
		this.csContent = csContent;
	}

	public Date getCsDate() {
		return csDate;
	}

	public void setCsDate(Date csDate) {
		this.csDate = csDate;
	}

	public Boolean getCsIscheck() {
		return csIscheck;
	}

	public void setCsIscheck(Boolean csIscheck) {
		this.csIscheck = csIscheck;
	}

	public String getCsNickname() {
		return csNickname;
	}

	public void setCsNickname(String csNickname) {
		this.csNickname = csNickname;
	}

	public String getCsAnswer() {
		return csAnswer;
	}

	public void setCsAnswer(String csAnswer) {
		this.csAnswer = csAnswer;
	}

	@Override
	public String toString() {
		return "CustomerCenter [csNo=" + csNo + ", csWriter=" + csWriter + ", csType=" + csType + ", csTitle=" + csTitle
				+ ", csContent=" + csContent + ", csDate=" + csDate + ", csIscheck=" + csIscheck + ", csNickname="
				+ csNickname + ", csAnswer=" + csAnswer + "]";
	}



	
	
	
}
