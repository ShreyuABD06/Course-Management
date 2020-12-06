package com.ncet.coursemgt.dao;

import static com.ncet.coursemgt.dao.QueryConstants.ADD_NEW_INSTRUCTOR;
import static com.ncet.coursemgt.dao.QueryConstants.GET_ACTIVE_INSTRUCTORS;
import static com.ncet.coursemgt.dao.QueryConstants.UPDATE_INSTRUCTOR;
import static com.ncet.coursemgt.dao.QueryConstants.ADD_NEW_COURSE;
import static com.ncet.coursemgt.dao.QueryConstants.UPDATE_COURSE;
import static com.ncet.coursemgt.dao.QueryConstants.GET_ACTIVE_COURSES;
import static com.ncet.coursemgt.dao.QueryConstants.REGISTER_NEW_STUDENT;
import static com.ncet.coursemgt.dao.QueryConstants.UPDATE_STUDENT;
import static com.ncet.coursemgt.dao.QueryConstants.GET_STUDENTS;
import static com.ncet.coursemgt.dao.QueryConstants.GET_STUDENTS_BY_COURSEID;
import static com.ncet.coursemgt.dao.QueryConstants.GET_INSTRUCTOR_BY_ID;
import static com.ncet.coursemgt.dao.QueryConstants.GET_COURSE_REPORT;
import static com.ncet.coursemgt.dao.QueryConstants.REGISTER_TO_COURSE;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ncet.coursemgt.dao.util.DbUtil;
import com.ncet.coursemgt.domain.Course;
import com.ncet.coursemgt.domain.Instructor;
import com.ncet.coursemgt.domain.STATUS;
import com.ncet.coursemgt.domain.Student;
import com.ncet.coursemgt.domain.StudentCourse;
import com.ncet.coursemgt.reports.CourseReport;

public class CourseMgtDaoImpl implements CourseMgtDao {
	private static CourseMgtDaoImpl courseMgtDaoImpl;
	private DbUtil dbUtil;

	public CourseMgtDaoImpl() {
		dbUtil = DbUtil.obj;
	}

	public static CourseMgtDaoImpl getInstanceCourseDaoImpl() {
		synchronized (CourseMgtDaoImpl.class) {
			if (courseMgtDaoImpl == null)
				courseMgtDaoImpl = new CourseMgtDaoImpl();
		}
		return courseMgtDaoImpl;
	}

	@Override
	public String addInstructor(Instructor instructor) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ADD_NEW_INSTRUCTOR);
			pst.setString(1, instructor.getName());
			pst.setString(2, instructor.getEmail());
			pst.setInt(3, instructor.getYearsExp());
			pst.setString(4, instructor.getStatus().name());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return instructor.getName();
		} catch (SQLException e) {
			System.out.println("While adding new instrctor : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections Closed");
		}
		return null;
	}

	@Override
	public List<Instructor> getActiveInstrctors() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Instructor inst = null;
		List<Instructor> instList = new ArrayList<Instructor>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_ACTIVE_INSTRUCTORS;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				inst = new Instructor();
				inst.setInstId(rs.getInt(1));
				inst.setName(rs.getString(2));
				inst.setEmail(rs.getString(3));
				inst.setYearsExp(rs.getInt(4));
				inst.setStatus(STATUS.valueOf(rs.getString(5)));
				instList.add(inst);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Active Instrctors " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connectins Closed");
		}
		return instList;
	}

	@Override
	public Instructor updateInstructor(Instructor updatedValue) {
		Connection con = null;
		// Instructor inst = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_INSTRUCTOR;

			pst = con.prepareStatement(query);
			pst.setString(1, updatedValue.getStatus().name());
			pst.setInt(2, updatedValue.getInstId());
			pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return updatedValue;
	}

	@Override
	public String addCourse(Course course) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ADD_NEW_COURSE);
			pst.setString(1, course.getTitle());
			pst.setString(2, course.getDescription());
			pst.setObject(3, course.getStartDate());
			pst.setObject(4, course.getEndDate());
			pst.setInt(5, course.getInstId());
			pst.setString(6, course.getStatus().name());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return course.getTitle();
		} catch (SQLException e) {
			System.out.println("While adding new course : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return null;
	}

	@Override
	public Course updateCourse(Course course) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_COURSE;
			pst = con.prepareStatement(query);
			pst.setString(1, course.getStatus().name());
			pst.setInt(2, course.getCourseId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("While Updating Course " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("COnnections Closed");
		}
		return course;
	}

	@Override
	public List<Course> getAllActiveCourses() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Course course = null;
		List<Course> courseList = new ArrayList<Course>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_ACTIVE_COURSES;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt(1));
				course.setTitle(rs.getString(2));
				course.setDescription(rs.getString(3));
				course.setStartDate(rs.getDate(4));
				course.setEndDate(rs.getDate(5));
				course.setInstId(rs.getInt(6));
				course.setStatus(STATUS.valueOf(rs.getString(7)));
				courseList.add(course);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Active courses " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return courseList;
	}

	@Override
	public String registerStudent(Student student) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(REGISTER_NEW_STUDENT);
			pst.setString(1, student.getName());
			pst.setString(2, student.getQualification());
			pst.setString(3, student.getEmail());
			pst.setString(4, student.getContry());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return student.getName();
		} catch (SQLException e) {
			System.out.println("While adding new student : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections closed");
		}
		return null;
	}

	@Override
	public Student updateStudent(Student student) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_STUDENT;
			pst = con.prepareStatement(query);
			pst.setString(1, student.getEmail());
			pst.setInt(2, student.getStuId());
			pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("While Updating Course " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections Closed");
		}
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Student stud = null;
		List<Student> studentList = new ArrayList<Student>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_STUDENTS;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				stud = new Student();
				stud.setStuId(rs.getInt(1));
				stud.setName(rs.getString(2));
				stud.setQualification(rs.getString(3));
				stud.setEmail(rs.getString(4));
				stud.setContry(rs.getString(5));

				studentList.add(stud);
			}
		} catch (SQLException e) {
			System.out.println("While Getting All Students " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return studentList;
	}

	@Override
	public List<Student> getAllStudents(int courseId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Student st = null;
		List<Student> stList = new ArrayList<Student>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			// String view = GET_STUDENTS_BY_COURSEID;
			String view = " select st.stuid,st.name,st.qualification,st.email,st.contry from student st"
					+ " inner join student_course s on st.stuid=s.stuid "
					+ "inner join course c on s.courid=c.courid "
					+ "where c.courid="+courseId;
			// // pst.setInt(1, courseId);
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				st = new Student();
				st.setStuId(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setQualification(rs.getString(3));
				st.setEmail(rs.getString(4));
				st.setContry(rs.getString(5));
				stList.add(st);

			}
		} catch (SQLException e) {
			System.out.println("While Getting Students For A Course " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return stList;
	}

	@Override
	public boolean registerStudentForCourse(int stuId, int courId) {
		Connection con = null;
		PreparedStatement pst = null;
		StudentCourse st1 = null;
		try {
			con = dbUtil.getConnection();
			st1 = new StudentCourse();
			String query=REGISTER_TO_COURSE;
			pst = con.prepareStatement(query);		
			pst.setInt(1, stuId);
			pst.setInt(2, courId);
			int isExcecuted=pst.executeUpdate();
			if (isExcecuted!=0)
				return true;
		} catch (SQLException e) {
			System.out.println("While Registering for course : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return false;
	}

	@Override
	public List<CourseReport> getAllActiveCourseDetails() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		CourseReport cRp = null;
		List<CourseReport> reportList = new ArrayList<CourseReport>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			// String view = GET_COURSE_REPORT;
			String view = " select c.courid,c.title,i.name,st.name from student st "
					+ "inner join student_course s on st.stuid=s.stuid "
					+ "inner join course c on s.courid=c.courid"
					+ " inner join instructor i on c.instid=i.instid "
					+ "where c.status='ACTIVE'";
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				cRp = new CourseReport();
				cRp.setCourId(rs.getInt(1));
				cRp.setCourseTitle(rs.getString(2));
				cRp.setFacultyName(rs.getString(3));
				cRp.setStudentName(rs.getString(4));
				reportList.add(cRp);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Course Report " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connection Closed");
		}
		return reportList;

	}

	@Override
	public Instructor getInstructor(int instId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Instructor inst = null;
		try {
			con = dbUtil.getConnection();
			// String sql = GET_INSTRUCTOR_BY_ID;
			// pst = con.prepareStatement(sql);
			// pst.setInt(1, instId);
			// rs = pst.executeQuery();
			String sql = "select * from Instructor where instid=" + instId;// Not
																			// working
																			// with
																			// Query
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				inst = new Instructor();
				inst.setInstId(rs.getInt(1));
				inst.setName(rs.getString(2));
				inst.setEmail(rs.getString(3));
				inst.setYearsExp(rs.getInt(4));
				inst.setStatus(STATUS.valueOf(rs.getString(5)));
				// return inst;
			}
		} catch (SQLException e) {
			System.out.println("While Getting Instructor By Id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections closed");
		}
		return inst;
	}

	@Override
	public Student getStudentById(int stuId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Student stu = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from student where stuid=" + stuId;// Not
																		// Working
																		// with
																		// Query
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				stu = new Student();
				stu.setStuId(rs.getInt(1));
				stu.setName(rs.getString(2));
				stu.setQualification(rs.getString(3));
				stu.setEmail(rs.getString(4));
				stu.setContry(rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println("While getting Student By id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return stu;
	}

	@Override
	public Course getCourseById(int courseId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Course course = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from course where courid=" + courseId;// Not
																			// Working
																			// With
																			// q
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt(1));
				course.setTitle(rs.getString(2));
				course.setDescription(rs.getString(3));
				course.setStartDate(rs.getDate(4));
				course.setEndDate(rs.getDate(5));
				course.setInstId(rs.getInt(6));
				course.setStatus(STATUS.valueOf(rs.getString(7)));
			}
		} catch (SQLException e) {
			System.out.println("While Getting Course By Id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connection closed");
		}
		return course;
	}

	@Override
	public Instructor findInstructor(String name, String email) {
		Connection con = null;
		// Statement stmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Instructor inst = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from Instructor where name= ? and email=?";// Not
																				// with
																				// Query
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, email);
			rs = pst.executeQuery();
			while (rs.next()) {
				inst = new Instructor();
				inst.setInstId(rs.getInt(1));
				inst.setName(rs.getString(2));
				inst.setEmail(rs.getString(3));
				inst.setYearsExp(rs.getInt(4));
				inst.setStatus(STATUS.valueOf(rs.getString(5)));
				// return inst;
			}
		} catch (SQLException e) {
			System.out.println("While Finding Instructor " + e);
		} finally {
			dbUtil.close(pst, rs, con);
			System.out.println("Connection Closed");
		}
		return inst;
	}
}
