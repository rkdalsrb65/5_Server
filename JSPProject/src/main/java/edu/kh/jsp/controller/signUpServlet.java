package edu.kh.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class signUpServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// 응답 형식, 문자 인코딩 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 클라이언트 응답용 스트림
		PrintWriter out = resp.getWriter();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signUpResult.jsp");
		
		
		dispatcher.forward(req, resp);
		
		
		
	}
	
}