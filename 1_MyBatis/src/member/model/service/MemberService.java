package member.model.service;

import org.apache.ibatis.session.SqlSession;

import static common.Template.getSqlSession;

import java.util.HashMap;

import member.model.dao.MemberDAO;
import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberService {

	public Member selectMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
//		System.out.println(session);
		
		Member member = new MemberDAO().selectMember(session, m);
		
		return member;
	}
	
	public Member selectMember(String userId) throws MemberException {
		SqlSession session = getSqlSession();
		
		Member member = new MemberDAO().selectMember(session, userId);
		
		return member;
	}
	
	public String selectMemberPwd(String userId) throws MemberException {
		SqlSession session = getSqlSession();
		
		String userPwd = new MemberDAO().selectMemberPwd(session, userId);
		
		return userPwd;
	}	

	public void insertMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		new MemberDAO().insertMember(session, m);
	
		session.commit();
		session.close();
	}
	
	public void updateMember(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		new MemberDAO().updateMember(session, m);
	
		session.commit();
		session.close();
	}
	
	public void updateMemberPwd(Member m) throws MemberException {
		SqlSession session = getSqlSession();
		new MemberDAO().updateMemberPwd(session, m);
	
		session.commit();
		session.close();
	}

	public void pwdUpdate(HashMap<String, String> map) throws MemberException {
		SqlSession session = getSqlSession();
		new MemberDAO().pwdUpdate(session, map);
	
		session.commit();
		session.close();
	}

	public void deleteMember(String userId) throws MemberException {
		SqlSession session = getSqlSession();
		new MemberDAO().deleteMember(session, userId);
	
		session.commit();
		session.close();
		
	}
	
}
