package com.ncet.coursemgt.reports;

public class CourseReport {
	private int courId;
	private String courseTitle;
	private String facultyName;
	private String studentName;

	public int getCourId() {
		return courId;
	}

	public void setCourId(int courId) {
		this.courId = courId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		return "CourseReport [courId=" + courId + ", courseTitle=" + courseTitle + ", facultyName=" + facultyName
				+ ", studentName=" + studentName + "]";
	}

}
