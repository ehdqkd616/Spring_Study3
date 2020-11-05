package com.kh.spring.member.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;


@Repository("mDAO")
public class MemberDAO {
	
	public Member memberLogin(SqlSessionTemplate sqlSession, Member m) {
		Member member = (Member)sqlSession.selectOne("memberMapper.memberLogin", m);
		return member;
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.insert("memberMapper.insertMember", m);
		return result;
	}

	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		int result = sqlSession.update("memberMapper.updateMember", m);
		return result;
	}

	public int updatePwd(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		int result = sqlSession.update("memberMapper.updatePwd", map);
		return result;
	}

	public int deleteMember(SqlSessionTemplate sqlSession, String id) {
		int result = sqlSession.update("memberMapper.deleteMember", id);
		return result;
	}

	public int checkIdDup(SqlSessionTemplate sqlSession, String id) {
		int result = sqlSession.selectOne("memberMapper.checkIdDup", id);
		return result;
	}
	
}
