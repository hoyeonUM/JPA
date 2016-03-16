package web.board.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.jpa.impl.JPAQuery;

import web.board.repository.custom.CustomBoardRepository;
import web.common.util.querydsl.QueryDslSupport;
import web.common.util.string.StringUtil;
import web.domain.Board;
import web.domain.BoardSearch;
import web.domain.QBoard;

/**
 * 
  * @FileName : BoardRepositoryImpl.java
  * @Project : web
  * @Date : 2016. 3. 12. 
  * @작성자 : hoyeon
  * @변경이력 :
  * @프로그램 설명 : 기초적인 CRUD 를 제외한 나머지는 interface를 통한 상속으로 이부분에서 추가로 구현한다.
 */
public class BoardRepositoryImpl extends QueryDslSupport<Board, QBoard> implements CustomBoardRepository{

	
	public BoardRepositoryImpl() {
        super(Board.class);
    }
	
	
	/**
	 * EntityManager DI하는부분
	 */
	 @PersistenceContext(unitName = "entityManagerFactory")
	 private EntityManager em;
	 
	@Override 
 	public Page<Board>  selectList(BoardSearch board , Pageable page) throws Exception{
		 
		 QBoard Qboard = QBoard.board;
		 JPAQuery query= new JPAQuery(em);
		 String st = board.getSearchType(); //검색조건
		 String sv = StringUtil.appendLike(board.getSearchValue(),"full"); //검색어
		 query.from(Qboard);
		 
		 if("boardTitle".equals(st)){
			 query.where(Qboard.title.like(sv));
		 }else if("boardContents".equals(st)){
			 query.where(Qboard.contents.like(sv));
		 }else{
				 query.where(Qboard.title.like(sv).
				 or(Qboard.contents.like(sv)));
		 }
		 Page <Board> list = readPage(query, Qboard, page);
		 return list;
		
	 }

	 
}
