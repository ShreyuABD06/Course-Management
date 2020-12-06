package com.ncet.coursemgt.service;

import java.util.List;

import com.ncet.coursemgt.domain.Course;
import com.ncet.coursemgt.domain.Instructor;
import com.ncet.coursemgt.domain.Student;
import com.ncet.coursemgt.reports.CourseReport;
public interface CourseMgtService {
	public String addInstructor(Instructor instructor); // Return instructor name after successful adding of instructor   
	public Instructor findInstructor(String name,String email);// search and return instructor if not found should return null 
	public List<Instructor> getActiveInstrctors(); // Return all instructors, whose status is active 
	public Instructor updateInstructor(Instructor updatedValue); // Only status can be updated, provided there shouldn't any ongoing sessions 
	public String addCourse(Course course); // Return newly added course name(title)
	public Course updateCourse(Course course); // Course status should change, before update ensure course is completed
	public List<Course> getAllActiveCourses(); // Course witch are ongoing 
	public String registerStudent(Student student); // Return new registered student name
	public Student updateStudent(Student student);  // Can update only email 
	public List<Student> getAllStudents();
	public List<Student> getAllStudents(int courseId);// Return students who are registered for given course id
	public boolean registerStudentForCourse(int stuId,int courId); // Ensure student and course both exists then only add to db
	public List<CourseReport> getAllActiveCourseDetails(); // Report get all course active and with the student details
	public Instructor getInstructor(int Id);
	public Student getStudentById(int Id);
	public Course getCourseById(int Id);
}
