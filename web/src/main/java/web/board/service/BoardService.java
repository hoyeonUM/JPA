package web.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import web.domain.Board;
import web.domain.BoardSearch;

@Service
public interface BoardService{
	
	
	
	
	/**
	 * 
	  * @Method Name : selectList
	  * @작성일 : 2016. 3. 12.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 : 게시판 전체리스트
	  * @param pageable
	  * @return
	  * @throws Exception
	 */
	public Page<Board> selectList(BoardSearch board ,Pageable pageable) throws Exception;
	
	
	
	/**
	 * 등록하기
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public void insert(Board board)  throws Exception;
	
	
	/**
	 * 키값으로 select 하기
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Board selectById(Long id)  throws Exception;
	
	
	/**
	 * 수정하기
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Board update(Board board)  throws Exception;


	/**
	 * 삭제하기
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Board delete(Board board) throws Exception;
	
	
	
}
