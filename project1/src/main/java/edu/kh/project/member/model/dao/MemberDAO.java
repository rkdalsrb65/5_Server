package edu.kh.project.member.model.dao;

import static edu.kh.project.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.project.member.model.vo.Member;

public class MemberDAO {
	

private Statement stmt;	
private PreparedStatement pstmt;	
private ResultSet rs;
private Properties prop;

public MemberDAO() {
	
	try {
		
           prop = new Properties();
           
           String filePath = MemberDAO.class.getResource("/edu/kh/project/sql/member-sql.xml").getPath();
           
           prop.loadFromXML( new FileInputStream(filePath) );
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

/** 로그인 DAO
 * @param conn
 * @param member
 * @return loginMember
 * @throws Exception
 */
public Member login(Connection conn, Member member) throws Exception {
	
	Member loginMember = new Member();
	
	try {
		
		String sql = prop.getProperty("login"); // SQL 얻어오기
		
		pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
		
		pstmt.setString(1, memberEmail); // ? 알맞은 값 대입
		pstmt.setString(2, memberPw); // ? 알맞은 값 대입
		
		rs = pstmt.executeQuery(); // SQL 수행 후 결과 반환 받기
		
		if(rs.next()) {
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberPw = rs.getString("MEMBER_PW");
			
			loginMember.add(new Member(memberEmail, memberPw));
		}
		
	} finally {
		close(rs); // 사용한 JDBC 객체(rs) 자원 반환
		close(pstmt); // 사용한 JDBC 객체(pstmt) 자원 반환
		}
	return loginMember; // 결과 반환
	}

}