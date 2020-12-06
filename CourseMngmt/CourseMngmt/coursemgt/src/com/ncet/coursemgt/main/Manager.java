package com.ncet.coursemgt.main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.ncet.coursemgt.dao.CourseMgtDao;
import com.ncet.coursemgt.dao.CourseMgtDaoImpl;
import com.ncet.coursemgt.domain.Course;
import com.ncet.coursemgt.domain.Instructor;
import com.ncet.coursemgt.domain.STATUS;
import com.ncet.coursemgt.domain.Student;
import com.ncet.coursemgt.domain.StudentCourse;
import com.ncet.coursemgt.reports.CourseReport;
import com.ncet.coursemgt.service.CourseMgtServiceImpl;

public class Manager {

	public static void main(String[] args) {
		Manager m = new Manager();
		m.start();
	}

	private void start() {
		while (true) {
			System.out.println("1:ADD Instructor");
			System.out.println("2:Get Active Instructors");
			System.out.println("3:Update Instructor");
			System.out.println("4:ADD Course");
			System.out.println("5GEt Active Courses:");
			System.out.println("6:Update Course");
			System.out.println("7:Register Student");
			System.out.println("8:GET all Students");
			System.out.println("9:Update Student");
			System.out.println("10:Get Students by CourseId");
			System.out.println("11:Register Student For Course");
			System.out.println("12:Get All Active Course Detais(COURSE REPORT ");
			System.out.println("13:Find Instructor(ByNAME,ByEmail)");
			System.out.println("14:Get Instructor By Id");
			System.out.println("15:GEt Course By Id");
			System.out.println("16:Get Student By Id");
			System.out.println("17:Exit");
			Scanner sc = new Scanner(System.in);
			CourseMgtServiceImpl c = new CourseMgtServiceImpl();
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Instructor instructor = new Instructor();
				String s = c.addInstructor(instructor);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(s!=null){
				System.out.println(
						"------------------------------------****Instructor Added****-----------------------------------");
				System.out.println("Instructor : " + s);
				}else{
					System.out.println("Instructor could nOt Be Added");
				}
				break;
			case 2:
				List<Instructor> list = c.getActiveInstrctors();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list != null) {
					System.out.println(
							"------------------------------------****Active Instructors****-----------------------------------");
					Iterator<Instructor> itr = list.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				}else{
					System.out.println("No Active Instructors");
				}
				break;
			case 3:
				Instructor instructor1 = new Instructor();
				instructor1 = c.updateInstructor(instructor1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println(
						"------------------------------------****Instructor Updated****-----------------------------------");
				System.out.println(instructor1);
				break;
			case 4:
				Course course = new Course();
				String cr = c.addCourse(course);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(cr!=null){
				System.out.println(
						"------------------------------------****Course Added****-----------------------------------");
				System.out.println("Course = " + cr);
				}else{
					System.out.println("Course Not Added");
				}
				break;
			case 5:
				List<Course> st = c.getAllActiveCourses();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (st != null) {
					System.out.println(
							"------------------------------------****Active Courses****-----------------------------------");
					Iterator<Course> itr = st.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				}else{
					System.out.println("No Active Courses to Display");
				}
				break;
			case 6:
				Course course1 = new Course();
				course1 = c.updateCourse(course1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}if(course1!=null){
				System.out.println(
						"------------------------------------****Course Updated****-----------------------------------");
				System.out.println(course1);
				}else{
					System.out.println("Could NOt Update Course");
				}
				break;
			case 7:
				Student student = new Student();
				String stu = c.registerStudent(student);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(stu!=null){
				System.out.println(
						"------------------------------------******Student Registered******-----------------------------------");
				System.out.println("Student : " + stu);
				}else{
					System.out.println("Could nOt Register The Student....Try Again:)");
				}
				break;
			case 8:
				List<Student> list1 = c.getAllStudents();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list1 != null) {
					System.out.println(
							"------------------------------------*******Students*******-----------------------------------");
					Iterator<Student> itr = list1.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Students to Display");
				}
				break;
			case 9:
				Student student1 = new Student();
				student1 = c.updateStudent(student1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(student1!=null){
					System.out.println(
							"------------------------------------****Updated Details****-----------------------------------");
				System.out.println(student1);
				}else{
					System.out.println("Could not Update Student email");
				}
				break;
			case 10:
				System.out.println("Enter the Course Id To Get Students ");
				int cId = sc.nextInt();
				List<Student> st1 = c.getAllStudents(cId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (st1 != null) {
					Iterator<Student> itr = st1.iterator();
					System.out.println(
							"------------------------------------****Students Registered****-----------------------------------");
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Student Registered for Course " + cId);
				}
				break;
			case 11:
				StudentCourse stud = new StudentCourse();
				System.out.println("Enter Student Id to Register for a course");
				int studId = sc.nextInt();
				stud.setCourId(studId);
				System.out.println("Enter course Id to register");
				int coursId = sc.nextInt();
				stud.setCourId(coursId);
				boolean isRegister = c.registerStudentForCourse(studId, coursId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (isRegister) {
					System.out.println("Registered student to Course Successfully");
				} else {
					System.out.println("Sorry...!  Could Not Register Student");
				}
				break;
			case 12:
				List<CourseReport> cR = c.getAllActiveCourseDetails();
				if (cR != null) {
					Iterator<CourseReport> itr = cR.iterator();
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(
							"------------------------------------****Course Report****-----------------------------------");
					while (itr.hasNext()) {

						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Courses Active to Report");
				}
				break;
			case 13:
				Instructor instr = new Instructor();
				System.out.println("Enter name of the Instructor to Be Searched");
				String iName = sc.next();
				System.out.println("Enter Email of the Instructor to Be Searched");
				String email = sc.next();
				instr = c.findInstructor(iName, email);
				if(instr!=null){
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println(instr);
				}else{
					System.out.println("Instructor Not Found");
				}
				break;
			case 14:
				Instructor instructor3 = new Instructor();
				System.out.println("Enter Instructor Id to get Instructor");
				int instId = sc.nextInt();
				instructor3 = c.getInstructor(instId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(instructor3!=null){
				System.out.println(instructor3);
				}else{
					System.out.println("Instructor with id " +instId+ " Not Found");
				}
				break;

			case 15:
				Course cou = new Course();
				System.out.println("Enter Course Id to get Course");
				int courseId = sc.nextInt();
				cou = c.getCourseById(courseId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}if(cou!=null){
				System.out.println(cou);
				}else{
					System.out.println("Course with id " +courseId+ " notFound");
				}
				break;
			case 16:
				Student stud1 = new Student();
				System.out.println("Enter Student Id to get Student");
				int sId = sc.nextInt();
				stud1 = c.getStudentById(sId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(stud1!=null){
				System.out.println(stud1);
				}else{
					System.out.println("Student with Id "+sId+" Not Found");
				}
				break;
			case 17:
				System.out.println("!!................Thank You................!!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
			}

		}

	}
}
