package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class test extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("호출");
	}
}



/*
 * @WebServlet("/test") public class test extends HttpServlet{
 * 
 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
 * resp) throws ServletException, IOException { System.out.println("post 호출");
 * req.setCharacterEncoding("UTF-8");
 * 
 * String id = req.getParameter("inputId"); String pw =
 * req.getParameter("inputPw");
 * 
 * System.out.println(id); System.out.println(pw);
 * 
 * // resp.setContentType("text/html; charset=UTF-8");
 * 
 * PrintWriter out = resp.getWriter();
 * 
 * RequestDispatcher dispatcher =
 * req.getRequestDispatcher("/WEB-INF/views/test.jsp");
 * 
 * dispatcher.forward(req, resp);
 * 
 * }
 * 
 * }
 */