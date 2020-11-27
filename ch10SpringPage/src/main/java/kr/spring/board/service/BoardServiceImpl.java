package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.domain.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		
		return boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insert(BoardVO board) {
		boardMapper.insert(board);
	}

	@Override
	public BoardVO selectBoard(Integer num) {
		return boardMapper.selectBoard(num);
	}

	@Override
	public void updateHit(Integer num) {
		boardMapper.updateHit(num);
	}

	@Override
	public void update(BoardVO board) {
		boardMapper.update(board);
	}

	@Override
	public void delete(Integer num) {
		boardMapper.delete(num);
	}

}









