package kr.spring.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberVO;

public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public Integer selectMem_num();
	@Insert("INSERT INTO spmember (mem_num,id) VALUES (#{mem_num},#{id})")
	public void insert(MemberVO member);
	@Insert("INSERT INTO spmember_detail "
			+ "(mem_num,name,passwd,phone,email,zipcode,address1,address2)"
			+ "VALUES (#{mem_num},#{name},#{passwd},#{phone},#{email},#{zipcode},#{address1},#{address2})")
	public void insertDetail(MemberVO member);
	@Select("SELECT * FROM spmember a LEFT OUTER JOIN spmember_detail b ON a.mem_num=b.mem_num WHERE a.id=#{id}")
	public MemberVO selectCheckMember(String id);
	@Select("SELECT * FROM spmember a JOIN spmember_detail b ON a.mem_num=b.mem_num WHERE a.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	@Update("UPDATE spmember_detail SET name=#{name},phone=#{phone},email=#{email},zipcode=#{zipcode},address1=#{address1},address2=#{address2},modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void update(MemberVO member);
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	@Update("UPDATE spmember SET auth=0 WHERE mem_num=#{mem_num}")
	public void delete(Integer mem_num);
	@Delete("DELETE FROM spmember_detail WHERE mem_num=#{mem_num}")
	public void deleteDetail(Integer mem_num);
}














