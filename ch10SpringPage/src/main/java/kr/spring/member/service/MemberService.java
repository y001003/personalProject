package kr.spring.member.service;

import kr.spring.member.domain.MemberVO;

public interface MemberService {
	public void insert(MemberVO member);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(Integer mem_num);
	public void update(MemberVO member);
	public void updatePassword(MemberVO member);
	public void delete(Integer mem_num);
}
