package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.domain.BoardVO;

public interface BoardService {
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(BoardVO board);
	public BoardVO selectBoard(Integer num);
	public void updateHit(Integer num);
	public void update(BoardVO board);
	public void delete(Integer num);
}
