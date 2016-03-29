  package web.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import web.board.repository.custom.CustomBoardRepository;
import web.domain.Board;
public interface BoardRepository extends JpaRepository<Board, Long>,JpaSpecificationExecutor<Board>,CustomBoardRepository{
	
	
}
