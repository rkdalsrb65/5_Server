package edu.kh.jsp.student.model.service;

// JDBCTemplate의 static 필드/메서드 호출 시 클래스명 생략
import static edu.kh.jsp.common.JDBCTemplate.*;

import edu.kh.jsp.student.model.dao.StudentDAO;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();
	
}