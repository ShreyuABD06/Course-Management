package com.ncet.coursemgt.domain;

public class Student {
	private int stuId;
	private String name;
	private String qualification;
	private String email;
	private String contry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", name=" + name + ", qualification=" + qualification + ", email=" + email
				+ ", contry=" + contry + "]";
	}
	
	
}
