package web.board.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.domain.Board;
import web.domain.BoardSearch;

/**
 * 
  * @FileName : CustomBoardRepository.java
  * @Project : web
  * @Date : 2016. 3. 12. 
  * @작성자 : hoyeon
  * @변경이력 :
  * @프로그램 설명 : SPRING DATA JPA 에서 런타임시에 생성될수있는 인터페이스 부분들을 제외한 나머지 interFace를 정의하는 부분
  * 					 
 */
public interface CustomBoardRepository {
	
	/**
	 * r
	  * @Method Name : selectList
	  * @작성일 : 2016. 3. 12.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 : 게시판 리스트
	  * @param board
	  * @param page
	  * @return
	  * @throws Exception
	 */
	public Page<Board>  selectList(BoardSearch board , Pageable page) throws Exception;
	
	
}
