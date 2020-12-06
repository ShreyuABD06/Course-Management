package com.ncet.coursemgt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.ncet.coursemgt.dao.CourseMgtDao;
import com.ncet.coursemgt.dao.CourseMgtDaoImpl;
import com.ncet.coursemgt.dao.util.DbUtil;
import com.ncet.coursemgt.domain.Course;
import com.ncet.coursemgt.domain.Instructor;
import com.ncet.coursemgt.domain.STATUS;
import com.ncet.coursemgt.domain.Student;
import com.ncet.coursemgt.domain.StudentCourse;
import com.ncet.coursemgt.reports.CourseReport;

public class CourseMgtServiceImpl implements CourseMgtService {

	private static CourseMgtService courseMgtService;
	private CourseMgtDao courseMgtDao;
	Scanner sc = new Scanner(System.in);

	public CourseMgtServiceImpl() {
		courseMgtDao = CourseMgtDaoImpl.getInstanceCourseDaoImpl();
	}

	public static CourseMgtService getInstanceCouserMgtService() {
		synchronized (CourseMgtServiceImpl.class) {
			if (courseMgtService == null)
				courseMgtService = new CourseMgtServiceImpl();
		}
		return courseMgtService;
	}

	@Override
	public Instructor findInstructor(String name, String email) {
		Instructor instructor = new Instructor();
		instructor = courseMgtDao.findInstructor(name, email);
		if (instructor != null) {
			return instructor;
		} else {
			return null;
		}
	}

	@Override
	public String addInstructor(Instructor instructor) {
		System.out.println("Enter Instructor Name");
		String name = sc.next();
		instructor.setName(name);
		System.out.println("Enter Email");
		String email = sc.next();
		instructor.setEmail(email);
		System.out.println("Enter Years of Experience");
		int yoe = sc.nextInt();
		instructor.setYearsExp(yoe);
		System.out.println("Enter Status");
		STATUS status = STATUS.valueOf(sc.next());
		instructor.setStatus(status);
		String s = courseMgtDao.addInstructor(instructor);
		if (s != null) {
			System.out.println("Instructor successfuly Added");
			return s;
		} else {
			return null;
		}
	}

	@Override
	public List<Instructor> getActiveInstrctors() {
		List<Instructor> instList = courseMgtDao.getActiveInstrctors();
		if (instList != null) {
			return instList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public Instructor updateInstructor(Instructor updatedValue) {
		System.out.println("Enter Id of Instructor to be Updated");
		int Id = sc.nextInt();
		updatedValue = getInstructor(Id);
		if (updatedValue.getStatus() != STATUS.ACTIVE) {
			System.out.println("Enter the status to be Updated");
			STATUS status = STATUS.valueOf(sc.next());
			updatedValue.setStatus(status);
			return updatedValue = courseMgtDao.updateInstructor(updatedValue);
		} else {
			System.out.println("Instructor is currently Buzy");
			return null;
		}
	}

	@Override
	public String addCourse(Course course) {
		System.out.println("Enter Course Title");
		String title = sc.next();
		course.setTitle(title);

		System.out.println("Enter Description");
		String desc = sc.next();
		course.setDescription(desc);

		System.out.println("Enter Start Date");
		Date startDate = java.sql.Date.valueOf(sc.next());
		// LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
		course.setStartDate(startDate);

		System.out.println("Enter end Date");
		Date endDate = java.sql.Date.valueOf(sc.next());
		// LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
		course.setEndDate(endDate);

		System.out.println("Enter Instructor Id");
		int instId = sc.nextInt();
		course.setInstId(instId);

		System.out.println("Enter Status");
		STATUS status = STATUS.valueOf(sc.next());
		course.setStatus(status);
		String s = courseMgtDao.addCourse(course);
		if (s != null) {
			System.out.println("Course successfuly Added");
			return s;
		} else {
			return null;
		}
	}

	@Override
	public Course updateCourse(Course course) {
		System.out.println("Enter Id of Course to be Updated");
		int Id = sc.nextInt();
		course = getCourseById(Id);
		if (course.getStatus() != STATUS.ACTIVE) {
			System.out.println("Enter the status to be Updated");
			STATUS status = STATUS.valueOf(sc.next());
			course.setStatus(status);
			return course = courseMgtDao.updateCourse(course);
		} else {
			System.out.println("Course is currently active");
			return null;
		}
	}

	@Override
	public List<Course> getAllActiveCourses() {
		List<Course> courseList = courseMgtDao.getAllActiveCourses();
		if (courseList != null) {
			return courseList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public String registerStudent(Student student) {
		System.out.println("Enter Student Name");
		String name = sc.next();
		student.setName(name);

		System.out.println("Enter Qualification");
		String qualification = sc.next();
		student.setQualification(qualification);

		System.out.println("Enter Student email");
		String email = sc.next();
		student.setEmail(email);

		System.out.println("Enter Student Country");
		String contry = sc.next();
		student.setContry(contry);

		String s = courseMgtDao.registerStudent(student);
		if (s != null) {
			System.out.println("Course successfuly Added");
			return s;
		} else {
			return null;
		}
	}

	@Override
	public Student updateStudent(Student student) {
		System.out.println("Enter Id of Student to be Updated");
		int Id = sc.nextInt();
		student = getStudentById(Id);
		System.out.println("Do you wish to update Student Mail......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")) {
			System.out.println("Enter email to be Updated");
			String email = sc.next();
			student.setEmail(email);
			return student = courseMgtDao.updateStudent(student);
		} else {
			return null;
		}
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> studentList = courseMgtDao.getAllStudents();
		if (studentList != null) {
			return studentList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public List<Student> getAllStudents(int courseId) {

		List<Student> stList = courseMgtDao.getAllStudents(courseId);
		// Iterator<Student> itr = stList.iterator();
		if (stList != null) {
			return stList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}

	}

	@Override
	public boolean registerStudentForCourse(int stuId, int courId) {
		boolean status = courseMgtDao.registerStudentForCourse(stuId, courId);
		return status;
	}

	@Override
	public List<CourseReport> getAllActiveCourseDetails() {
		List<CourseReport> report = courseMgtDao.getAllActiveCourseDetails();
		if (report != null) {
			return report;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public Instructor getInstructor(int Id) {
		Instructor inst = courseMgtDao.getInstructor(Id);
		if (inst != null) {
			return inst;
		} else {
			return null;
		}
	}

	@Override
	public Student getStudentById(int Id) {
		Student stu = courseMgtDao.getStudentById(Id);
		if (stu != null) {
			return stu;
		} else {
			return null;
		}
	}

	@Override
	public Course getCourseById(int Id) {
		Course course = courseMgtDao.getCourseById(Id);
		if (course != null) {
			return course;
		} else {
			return null;
		}
	}

}
