package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/minsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int date = Integer.parseInt(request.getParameter("date"));		
		
		Date birthDay = new Date(new GregorianCalendar(year, month, date).getTimeInMillis());

	/*	
		String date = request.getParameter("birthDay");
		Date birthDay = null;
		if (date != "") {
			String[] dateArr = date.split("-");

			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]) - 1;
			int day = Integer.parseInt(dateArr[2]);

			birthDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			birthDay = new Date(new GregorianCalendar().getTimeInMillis());
		}
	*/
		
		
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Member m = new Member(userId, userPwd, userName, nickName, email, birthDay, gender, phone, address);
		try {
			new MemberService().insertMember(m);
			response.sendRedirect(request.getContextPath());
		} catch (MemberException e) {
			request.setAttribute("message", e.getMessage());
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
