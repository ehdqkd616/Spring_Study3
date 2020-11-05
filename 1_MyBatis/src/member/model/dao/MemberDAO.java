package member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberDAO {

	public Member selectMember(SqlSession session, Member m) throws MemberException {
		//session.selectOne(arg0, arg1);
		// 		arg0 : 어느 쿼리를 가지고 올 지에 대한 ID
		// 			query.properties ==> selectMember=SELECT * FROM MEMBER WHERE USER_ID=?
		// 			prop.getProperty("selectMember");
		
		//		arg1 : 쿼리에 넘길 값 전달
		Member member = session.selectOne("memberMapper.loginMember", m);
//		System.out.println(member);
		
		if(member == null) {
			throw new MemberException("로그인에 실패하였습니다.");
		}
		
		return member;
	}
	
	public Member selectMember(SqlSession session, String userId) {
		Member member = session.selectOne("memberMapper.selectMember", userId);
		return member;
	}
	
	public String selectMemberPwd(SqlSession session, String userId) {
		String userPwd = session.selectOne("memberMapper.selectMemberPwd", userId);
		return userPwd;
	}	

	public Member insertMember(SqlSession session, Member m) throws MemberException {
		
		int result = session.insert("memberMapper.insertMember", m);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원가입에 실패하였습니다.");
		}
		return m;
		
	}
	
	public Member updateMember(SqlSession session, Member m) throws MemberException {
		
		int result = session.update("memberMapper.updateMember", m);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("회원정보 수정에 실패하였습니다.");
		}
		return m;
		
	}
	
	public Member updateMemberPwd(SqlSession session, Member m) throws MemberException {
		
		int result = session.insert("memberMapper.updateMemberPwd", m);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			throw new MemberException("비밀번호 수정에 실패하였습니다.");
		}
		return m;
		
	}

	public void pwdUpdate(SqlSession session, HashMap<String, String> map) throws MemberException {
		int result = session.update("memberMapper.pwdUpdate", map);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			
			throw new MemberException("비밀번호 수정에 실패하였습니다.");
		}
	}

	public void deleteMember(SqlSession session, String userId) throws MemberException {
		int result = session.update("memberMapper.deleteMember", userId);
		
		if(result <= 0) {
			session.rollback();
			session.close();
			
			throw new MemberException("회원 탈퇴에 실패하였습니다.");
		}
		
	}




	
}
