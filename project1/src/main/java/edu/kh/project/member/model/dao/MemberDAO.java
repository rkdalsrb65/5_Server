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
	
	Member loginMember = null; // 결과 저장용 변수 선언
	
	try {
		
		String sql = prop.getProperty("login"); // SQL 얻어오기
		
		pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
		
		pstmt.setString(1, member.getMemberEmail()); // ? 알맞은 값 대입
		pstmt.setString(2, member.getMemberPw()); // ? 알맞은 값 대입
		
		rs = pstmt.executeQuery(); // SQL 수행 후 결과 반환 받기
		
		if(rs.next()) {
			loginMember = new Member();
			
			loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
			loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
			loginMember.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
			loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
			loginMember.setMemberAddress(rs.getString("MEMBER_ADDRESS"));
			loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
			loginMember.setAuthority(rs.getInt("AUTHORITY"));
			loginMember.setEnrollDate(rs.getString("ENROLL_DATE"));
		}
		
	} finally {
		close(rs); // 사용한 JDBC 객체(rs) 자원 반환
		close(pstmt); // 사용한 JDBC 객체(pstmt) 자원 반환
		}
	return loginMember; // 결과 반환
	}

/** 회원 가입 DAO
 * @param conn
 * @param member
 * @return result
 * @throws Exception
 */
public int signUp(Connection conn, Member member) throws Exception {
	
	int result = 0; // 결과 저장용 변수 선언
	
	try {
		
		String sql = prop.getProperty("signUp");
		
		pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
		
		pstmt.setString(1, member.getMemberEmail()); // ? 알맞은 값 대입
		pstmt.setString(2, member.getMemberPw()); // ? 알맞은 값 대입
		pstmt.setString(3, member.getMemberNickname()); // ? 알맞은 값 대입
		pstmt.setString(4, member.getMemberTel()); // ? 알맞은 값 대입
		pstmt.setString(5, member.getMemberAddress()); // ? 알맞은 값 대입
		
		result = pstmt.executeUpdate(); // SQL 수행 후 결과 반환 받기
		
		
	} finally {
		close(pstmt); // 사용한 JDBC 객체(pstmt) 자원 반환		
	}

	return result; // 결과 반환
}

/** 회원 정보 수정 DAO
 * @param conn
 * @param member
 * @return result
 * @throws Exception
 */
public int updateMember(Connection conn, Member member) throws Exception{
	
	int result = 0;
	
	try {
		
		String sql = prop.getProperty("updateMember");
		
		pstmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
		
		pstmt.setString(1, member.getMemberNickname()); // ? 알맞은 값 대입
		pstmt.setString(2, member.getMemberTel()); // ? 알맞은 값 대입
		pstmt.setString(3, member.getMemberAddress()); // ? 알맞은 값 대입
		pstmt.setInt(4, member.getMemberNo()); // ? 알맞은 값 대입
		
		result = pstmt.executeUpdate(); // SQL 수행 후 결과 반환 받기
		
	} finally {
		close(pstmt);
	}
	
	return  result;
}



}