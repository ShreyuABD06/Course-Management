package com.ncet.coursemgt.dao;

public interface QueryConstants {
	String ADD_NEW_INSTRUCTOR="insert into instructor(name,email,yearofexp,status) values(?,?,?,?)";
	String SEARCH_INSTRUCTOR="select instid, name, email, yearofexp, status from Instructor where name = ? and email = ? ";
	String GET_ACTIVE_INSTRUCTORS="select instid,name,email,yearofexp,status from Instructor where status='ACTIVE'";
	String UPDATE_INSTRUCTOR="update Instructor set STATUS=? where instid=?";
	String ADD_NEW_COURSE="insert into course(title,description,startDate,endDate,instid,status)values(?,?,?,?,?,?)";
	String UPDATE_COURSE="update course set STATUS=? where courid=?";
	String GET_ACTIVE_COURSES="select courid,title,description,startdate,enddate,instid,status from course where status='ACTIVE'";
	String REGISTER_NEW_STUDENT="insert into student(name,qualification,email,contry) values(?,?,?,?)";
	String UPDATE_STUDENT="update student set email=? where stuid=?";
	String GET_STUDENTS="select stuid,name,qualification,email,contry from student";
	String GET_STUDENTS_BY_COURSEID=" select st.stuid,st.name,st.qualification,st.email,"
			+ "st.contry from student st inner join student_course s on st.stuid=s.stuCourId inner join "
			+ "course c on s.stuCourId=c.courid where c.courid=courseId";
	String GET_INSTRUCTOR_BY_ID="select * from Instructor instid =?";
	String GET_COURSE_REPORT="select c.courid,c.title,i.name,st.name from student st inner join student_course "
			+ "	s on st.stuid=s.stuCourId inner join course"
			+ " c on s.stuCourId=c.courid inner join instructor i on c.courid=i.instid "
			+ "where c.status='ACTIVE'";
	String REGISTER_TO_COURSE="insert into student_course(stuid,courid) values(?,?)";
}
//select c.courid,c.title,i.name,st.name from student st inner join student_course s on st.stuid=s.stuCourId inner join course c on s.stuCourId=c.courid inner join instructor i on c.courid=i.instid where c.status='ACTIVE'