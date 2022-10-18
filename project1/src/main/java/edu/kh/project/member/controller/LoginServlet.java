package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberService service = new MemberService(); // 서비스 객체 생성
		
		try {
			
			// 파라미터 얻어오기
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			
			Member member = new Member();
			member.setMemberEmail(inputEmail);
			member.setMemberPw(inputPw);
			
			// 로그인 Service 호출 후 결과 반환 받기
			Member loginMember = service.login(member);
			
			// forward를 하는 경우
			// - 요청을 다른 Servlet/JSP로 위임
			// -> 어떤 요청이 위임됐는지 알아야 되기 때문에
			//		주소창에 요청 주소가 계속 남아있다.
			// - 
//			RequestDispatcher dispatcher =  req.getRequestDispatcher("/WEB-INF/views/common/main.jsp");
//			
//			req.setAttribute("loginMember", loginMember);
//			
//			dispatcher.forward(req, resp);
			
			// *** redirect(재요청) ***
			// - servlet이 다른 주소를 요청함.
			// - 요청에 대한 응답화면을 직접 만드는 거이 아닌
			// 다른 응답화면을 구현하는 Servlet을 요청하여
			// 대신 화면을 만들게 하는 것.
			
			// request scope에 속성을 추가해도
			// redirect를 하는 경우 request가 다시 만들어져 유지되지 않음 !
//			req.setAttribute("loginMember", loginMember);
			
			// 해결 방법 : Session scope 이용
			
			// 1) HttpSession 객체 얻어오기
			HttpSession session = req.getSession();
			
			if(loginMember != null) { // 로그인 성공 시
			
			// 2) Session scope에 속성 추가하기
			session.setAttribute("loginMember", loginMember);
			
			// 3) 세션 만료시간 설정(초 단위로 지정)
			// 		(클라이언트가 새로운 요청을 할 때 마다 초기화)
			session.setMaxInactiveInterval(60 * 60); // 60초 * 60초 == 1시간(3600초)

			} else { // 로그인 실패 시
				
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				
			}
			
			// 메인 페이지로 redirect
			// -> 메인 페이지를 요청한 것이기 때문에
			// 		 주소창에 주소가 메인 페이지 주소(/)로 변함.
			resp.sendRedirect("/");
			
			/* forward / redirect 차이점
			 * 
			 * forward
			 * - 주소창 변화 X
			 * - JSP 경로 작성
			 * - req, resp가 유지된다
			 * 
			 * redirect
			 * - 주소창 변화 O
			 * - 요청 주소 작성
			 * - req, resp가 유지되지 않는다.
			 */
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}