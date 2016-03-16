package web.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import web.board.repository.BoardRepository;
import web.board.service.BoardService;
import web.common.util.FileUtil;
import web.domain.Board;
import web.domain.BoardFile;
import web.domain.BoardSearch;

/**
 * 
  * @FileName : BoardServiceImpl.java
  * @Project : web
  * @Date : 2016. 3. 12. 
  * @작성자 : hoyeon
  * @변경이력 :
  * @프로그램 설명 : 게시판 관련 로직
  * 					  
 */
@Repository("BoardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepository;
	@Resource(name="FileUtil")
	FileUtil fileUtil;
	
	@Override
	public Page<Board>  selectList(BoardSearch board , Pageable page) throws Exception{
		return boardRepository.selectList(board,page);
	}

	@Override
	public void insert(Board board) throws Exception {
		boardRepository.save(board);
		List<BoardFile> list = board.getBoardFileList();
		
		
		//=======================파일추가=====================
		BoardFile boardFile = new BoardFile();
		boardFile.setBoard(board);
		boardFile.setFileName("파일명");
		boardFile.setFileSize(1000);
		boardFile.setOriginFileName("원본파일명");
		boardFile.setFileFolder("파일폴더");
		boardFile.setFileExtension("파일확장자명");
		list.add(boardFile);
	}

	@Override
	public Board selectById(Long id) throws Exception {
		Board board = boardRepository.findOne(id);
		board.setReadCount(board.getReadCount()+1);
		/* 양방향 테스트
		 List<BoardFile> boardList = board.getBoardFileList();
		System.out.println(boardList.get(0).getBoard().getTitle());
		BoardFile f = new BoardFile();
		f.setBoard(board);
		f.getBoard().getTitle();
		*/
		return board; 
		
	}

	@Override
	public Board update(Board board) throws Exception {
		boardRepository.save(board);
		return board;
	}
	
	
	
	@Override
	public Board delete(Board board) throws Exception {
		board.setUseYn("N");
		boardRepository.save(board);
		return board;
	}
	

	
	
	
	
}
