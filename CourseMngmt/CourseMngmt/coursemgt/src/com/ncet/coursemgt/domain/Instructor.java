package com.ncet.coursemgt.domain;

public class Instructor {
	private int instId;
	private String name;
	private String email;
	private int yearsExp;
	private STATUS status;

	public int getInstId() {
		return instId;
	}

	public void setInstId(int instId) {
		this.instId = instId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYearsExp() {
		return yearsExp;
	}

	public void setYearsExp(int yearsExp) {
		this.yearsExp = yearsExp;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Instructor [instId=" + instId + ", name=" + name + ", email=" + email + ", yearsExp=" + yearsExp
				+ ", status=" + status + "]";
	}

}
