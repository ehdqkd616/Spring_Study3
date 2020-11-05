package com.kh.spring.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.HomeController;
import com.kh.spring.member.model.exception.MemberException;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller // @Component의 확장판 (@Component + Controller의 성격 추가)
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// MemberServiceImpl 클래스를 객체로 만들어서 MemberService를 래퍼타입으로 사용. 
	// mService라는 이름의 MemberServiceImpl 클래스를 찾아 객체를 넣어줌.
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	// 로그아웃용 컨트롤러
//	@RequestMapping("logout.me")
//	public String logout(HttpSession session) {
//		
//		session.invalidate();
//		
//		return "redirect:home.do";
//		
//	}
	
	// 로그아웃용 컨트롤러2
	@RequestMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		
		return "redirect:home.do";
		
	}
	
	// 파라미터 전송 받는 방법
	// 1. HttpServletRequest를 통해 전송받기(JSP/Servlet 방식)
//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
//	public String login(HttpServletRequest request) {
//		System.out.println("로그인처리합니다.");
//
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//
//		System.out.println("id : " + id);
//		System.out.println("pwd : " + pwd);
//
//		return "redirect:home.do";
//	}
//
//	 // 2. @RequestParam 어노테이션 방식
//	 @RequestMapping(value="login.me", method =RequestMethod.POST)
//	 public String login(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
//	      System.out.println("id : " + id);
//	      System.out.println("pwd : " + pwd);
//	      
//	      return "redirect:home.do";
//	 }
//
//	 // 3. @RequestParam 생략
//	 @RequestMapping(value="login.me", method =RequestMethod.POST)
//	 public String login(String id, String pwd) {
//	      System.out.println("id : " + id);
//	      System.out.println("pwd : " + pwd);
//	      
//	      return "redirect:home.do";
//	 }
//
//	 // 4. @ModelAttribute 어노테이션 방식
//	 // Parameter 이름이랑 Member의 필드 이름이랑 같으면 받아진다.
//	 @RequestMapping(value="login.me", method =RequestMethod.POST)
//	 public String login(@ModelAttribute Member m) {
//	      
//	      System.out.println("m : " + m);
//	      
//	      return "redirect:home.do";
//	 }

	// 5. @ModelAttribute 생략
//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
//	public String login(Member m, HttpSession session) {
//
//		System.out.println("m : " + m);
//		mService = new MemberServiceImpl(); // 결합도가 굉장히 높은 코드
//		mService.memberLogin(m);
//		Member loginUser = mService.memberLogin(m);
//		System.out.println(loginUser);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//		}
//		
//		return "redirect:home.do";
//	}
	
	// 요청 후 전달하고자 하는 데이터가 있을 경우
	// 1. Model객체 사용
	//		맴 형식(key, value), scope = request
//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
//	public String login(Member m, HttpSession session, Model model) {
//		
//		Member loginUser = mService.memberLogin(m);
//		
//		System.out.println(loginUser);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:home.do";
//		} else {
//			model.addAttribute("message", "로그인에 실패했습니다.");
//			return "../common/errorPage";
//		}
//		
//		
//	}
	
	// 2. ModelAndView객체 사용
	//		Model(맵 형식 : key와 value) + View(RequestDispatcher.forward 할 뷰페이지 정보 담고 있음)
	//		맴 형식(key, value), scope = request
//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
//	public ModelAndView login(Member m, HttpSession session, ModelAndView mv) {
//		
//		Member loginUser = mService.memberLogin(m);
//		
//		System.out.println(loginUser);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			mv.setViewName("redirect:home.do");
//		} else {
//			mv.addObject("message", "로그인에 실패했습니다.");
//			mv.setViewName("../common/errorPage");
//		}
//		
//		return mv;
//	}
	
	
	// 3. session에 저장할 때 @SessionAttributes 사용
	//		model에 Attribute가 추가될 때 자동으로 키를 찾아 세션에 등록하는 기능 제공
//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
//	public String login(Member m, Model model) {
//		
//		Member loginUser = mService.memberLogin(m);
//		
//		System.out.println(loginUser);
//		
//		if(loginUser != null) {
//			model.addAttribute("loginUser", loginUser);
//			return "redirect:home.do";			
//		} else {
//			model.addAttribute("message", "로그인에 실패했습니다.");
//			return "../common/errorPage";
//		}
//		
//	}
	
	@RequestMapping("enrollView.me")
	public String enrollView() {
		
		if(logger.isDebugEnabled()) {
	         logger.debug("회원등록페이지");
	    }
		return "memberJoin";
	}
	
	@RequestMapping("minsert.me")
	public String insertMember(@ModelAttribute Member m, Model model, @RequestParam("post") String post,
														 @RequestParam("address1") String address1,
														 @RequestParam("address2") String address2) {
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		System.out.println("회원가입 시 들어온 member 정보 : " + m);
		// bcrypt ?
		// 비밀번호 평문으로 저장이 될 수 있기 때문에 암호화 처리 필요 ==> 스프링 시큐리티 모듈에서 제공하는 bcrypt 암호화 방식 사용
		//		1차로 암호화 된 메세지를 수학적 연산을 통해 암호화 된 메세지인 다이제스트 생성
		//		salt값 ==> 값을 랜덤하게 생성하여 암호화가 계속 다르게 나오도록
		
		String encPwd = bcryptPasswordEncoder.encode(m.getPwd());
		System.out.println("비밀번호 암호화 : " + encPwd);
		m.setPwd(encPwd);
		
		int result = mService.insertMember(m);
		
		if(result > 0) {
			return "redirect:home.do";
		} else {
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		
	}
	
	// 암호화 후 로그인
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	public String login(Member m, Model model) {
		
		Member loginUser = mService.memberLogin(m);
		
		if(bcryptPasswordEncoder.matches(m.getPwd(), loginUser.getPwd())) {
			model.addAttribute("loginUser", loginUser);
//			logger.info("로그인 아이디 : " + loginUser.getId());
		} else {
			throw new MemberException("로그인에 실패했습니다.");
		}
		
		return "redirect:home.do";
	}
	
	@RequestMapping("myinfo.me")
	public String myInfoView() {
		return "mypage";
	}
	
	@RequestMapping("mupdateView.me")
	public String updateFormView() {
		return "memberUpdateForm";
	}

	@RequestMapping("mupdate.me")
	public String updateMember(@ModelAttribute Member m, Model model, @RequestParam("post") String post,
														 @RequestParam("address1") String address1,
														 @RequestParam("address2") String address2) {
		
		m.setAddress(post + "/" + address1 + "/" + address2);
		System.out.println("회원수정 시 들어온 member 정보 : " + m);
		
		int result = mService.updateMember(m);
		
		if(result > 0) {
			model.addAttribute("loginUser", m);
			return "mypage";
		} else {
			throw new MemberException("회원 정보 수정에 실패하였습니다.");
		}
		
	}

	@RequestMapping("mpwdUpdateView.me")
	public String pwdUpdateView() {
		return "memberPwdUpdateForm";
	}
	
	@RequestMapping("mPwdUpdate.me")
	public String updatePwd(@RequestParam("pwd") String pwd, @RequestParam("newPwd1") String newPwd, HttpSession session) {
		
		// 아이디 얻기 : 정보 수정만 했을 때 비밀번호가 없을 것을 예상하여 몯느 정보를 새로 얻어오는 과정
		Member m = mService.memberLogin((Member)session.getAttribute("loginUser"));
		
		System.out.println("암호화 전 pwd : " + pwd +", newPwd1 : " + newPwd);
		
		if(bcryptPasswordEncoder.matches(pwd, m.getPwd())) {
			String encNewPwd = bcryptPasswordEncoder.encode(newPwd);
			m.setPwd(encNewPwd);
			
			System.out.println("암호화 후  encNewPwd1 : " + encNewPwd);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", m.getId());
			map.put("newPwd", encNewPwd);
			
			int result = mService.updatePwd(map);
			
			if(result > 0) {
				return "mypage";
			} else {
				throw new MemberException("비밀번호 수정에 실패하였습니다.");
			}
		} else {
			throw new MemberException("비밀번호가 일치하지 않습니다.");		
		}
		
	}	
	
	@RequestMapping("mdelete.me")
	public String deleteMember(String id) {
		int result = mService.deleteMember(id);
		
		if(result > 0) {
			return "redirect:logout.me";
		} else {
			throw new MemberException("회원 탈퇴에 실패하였습니다.");
		}
	}
	
	@RequestMapping("dupid.me")
	public void dupId(String id, HttpServletResponse response) throws IOException {
		boolean isUsable = mService.checkIdDup(id) == 0 ? true : false;
		
		response.getWriter().print(isUsable);	
	}
}




