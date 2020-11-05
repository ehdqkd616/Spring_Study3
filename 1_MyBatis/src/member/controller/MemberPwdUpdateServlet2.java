package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/mPwdUpdate.me2")
public class MemberPwdUpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberPwdUpdateServlet2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginUser");
		
		String userId = loginMember.getUserId();
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		
		MemberService memberService = new MemberService();		
		
		String page = null;
		
		if(userPwd !=null) {
			if(!userPwd.equals(loginMember.getUserPwd())){
				request.setAttribute("message", "비밀번호가 일치하지않습니다.");
				page = "WEB-INF/views/common/errorPage.jsp";
			} else if(newPwd.equals(null) && newPwd2.equals(null)) {
				request.setAttribute("message", "새 비밀번호 값이 입력되지 않았습니다.");
				page = "WEB-INF/views/common/errorPage.jsp";				
			} else if(!newPwd.equals(newPwd2)){
				request.setAttribute("message", "새 비밀번호, 새 비밀번호 확인이 일치하지않습니다.");
				page = "WEB-INF/views/common/errorPage.jsp";
			} else if(userPwd.equals(loginMember.getUserPwd()) && newPwd.equals(newPwd2)) {
				Member updatePwdMember = new Member(userId, newPwd);
				try {
					memberService.updateMemberPwd(updatePwdMember);
					Member newMember = memberService.selectMember(userId);
					session.setAttribute("loginUser", newMember);
					session.setMaxInactiveInterval(600);
					page = "WEB-INF/views/member/memberInfo.jsp";
				} catch (MemberException e) {
					request.setAttribute("message", e.getMessage());
				}
			}
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
