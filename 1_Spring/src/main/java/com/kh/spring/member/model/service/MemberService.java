package com.kh.spring.member.model.service;

import java.util.HashMap;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	
	public Member memberLogin(Member m);

	public int insertMember(Member m);

	public int updateMember(Member m);

	public int updatePwd(HashMap<String, String> map);

	public int deleteMember(String id);

	public int checkIdDup(String id);
	
		
	
}
