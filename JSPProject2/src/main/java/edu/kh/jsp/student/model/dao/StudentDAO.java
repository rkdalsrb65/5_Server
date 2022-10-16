package edu.kh.jsp.student.model.dao;

//JDBCTemplate의 static 필드/메서드 호출 시 클래스명 생략
import static edu.kh.jsp.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jsp.student.model.vo.Student;

public class StudentDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	// 기본 생성자
	public StudentDAO() {
		try {
			String filePath = StudentDAO.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
			
			prop = new Properties();
			
			prop.loadFromXML( new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 학생 전체 조회 DAO
	 * @param conn
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectAll(Connection conn) throws Exception{
		
		// 결과 저장용 변수
		List<Student> stdList = new ArrayList<>();
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과 반환 받기
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				stdList.add(new Student(studentNo, studentName, studentAddress, departmentName));
			}
			
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		// 결과 반환
		return stdList;
	}

	/**
	 * @param conn
	 * @param departmentName 
	 * @return stdList
	 * @throws Exception
	 */
	public List<Student> selectDepartment(Connection conn, String departmentName) throws Exception {
		
		// 결과 저장용 변수
		List<Student> student = new ArrayList<>();
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectDepartment");
			
			// PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?에 알맞은 값 대입
			pstmt.setString(1, departmentName);
			
			// SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName2 = rs.getString("DEPARTMENT_NAME");
				
				student.add(new Student(studentNo, studentName, studentAddress, departmentName2));
			}
			
		} finally {
			// 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		// 결과 반환		
		return student;
		
	}

	
	
	
	
	
	
	
	
	
	
}
