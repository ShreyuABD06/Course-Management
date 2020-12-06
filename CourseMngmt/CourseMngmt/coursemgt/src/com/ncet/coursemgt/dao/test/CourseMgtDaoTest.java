package com.ncet.coursemgt.dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ncet.coursemgt.dao.CourseMgtDao;
import com.ncet.coursemgt.dao.CourseMgtDaoImpl;
import com.ncet.coursemgt.dao.util.DbUtil;
import com.ncet.coursemgt.domain.Instructor;
import com.ncet.coursemgt.domain.STATUS;

public class CourseMgtDaoTest {

	private static DbUtil dbUtil;
	static Connection con=null;
	private CourseMgtDao courseMgtDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Setup Before Class");
		dbUtil=DbUtil.obj;
		con=dbUtil.getConnection();		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Tear Down after class");
		dbUtil.close(con);
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("set Up");
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear Down");
	}

//	@Test
//	public void test() {
//		System.out.println("Running dao Test");
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testAddInstructor(){
		CourseMgtDao courseMgtDao=CourseMgtDaoImpl.getInstanceCourseDaoImpl();
		Instructor instructor=new Instructor();
//		//courseMgtDao.addInstructor(instructor);
		instructor.setName("Shreyas");
		instructor.setEmail("shreyuabd.360@gmail.com");
		instructor.setYearsExp(2);
		instructor.setStatus(STATUS.ACTIVE);
		courseMgtDao.addInstructor(instructor);
		assertEquals("Shreyas", courseMgtDao.addInstructor(instructor));
		//assertSame(instructor.getName(),"Shreyas");
	}
	

}
