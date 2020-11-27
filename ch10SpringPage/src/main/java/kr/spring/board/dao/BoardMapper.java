package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO spboard (num,title,content,uploadfile,filename,ip,mem_num) "
			+ "VALUES (board_seq.nextval,#{title},#{content},#{uploadfile},#{filename},#{ip},#{mem_num})")
	public void insert(BoardVO board);
	@Select("SELECT * FROM spboard b JOIN spmember m ON b.mem_num=m.mem_num WHERE b.num=#{num}")
	public BoardVO selectBoard(Integer num);
	@Update("UPDATE spboard SET hit=hit+1 WHERE num=#{num}")
	public void updateHit(Integer num);
	public void update(BoardVO board);
	@Delete("DELETE FROM spboard WHERE num=#{num}")
	public void delete(Integer num);
}
