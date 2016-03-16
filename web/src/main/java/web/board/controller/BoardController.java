package web.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import web.board.service.BoardService;
import web.common.util.PagingUtil;
import web.domain.Board;
import web.domain.BoardSearch;

/**
 * 등록 POST
 * 수정 PUT
 * 삭제 DELETE
 * 리스트 , 뷰 GET
 * @author hoyeon
 *
 */
@Controller
public class BoardController{
	

	 @Resource(name="BoardService")
	 private BoardService boardService;
	 
	
	
	/**
	 * 데이터 바인터를 이용해 ID를 통해 들어오는 값을 제거해준다 . (등록시 문제해결을위해)
	 * @param dataBinder
	 */
  	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
	  
	
	/**
	 * 게시판리스트 불러오기
	  * @Method Name : list
	  * @작성일 : 2016. 3. 12.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param request
	  * @param response
	  * @param model		view로 보내는 메서드
	  * @param pageable  페이징처리 web-dispatchservlet 에서 처리함
	  * @param board		 보드게시판에 도메인모델
	  * @param pageSize   아래 페이지리스트 갯수에 표출되는 사이즈의 갯수
	  * @return
	  * @throws Exception
	 */
	 @RequestMapping(value = "/boards", method = RequestMethod.GET)
	public String list(ModelMap model,
			@PageableDefault(sort = {"id"}, direction = Direction.DESC, size = 10) Pageable pageable,
			@ModelAttribute("params") BoardSearch board,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
			) throws Exception {
		 	Page<Board> page =boardService.selectList(board,pageable);
			model.addAttribute("paginationInfo", PagingUtil.Paging(page,pageSize));
			model.addAttribute("list", page.getContent());
		
		return "/board/list";			
	}
	 
	 
	
	
	
	/**
	 * 
	  * @Method Name : writeForm
	  * @작성일 : 2016. 3. 13.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 : 게시판글쓰기폼
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/board/new",method = RequestMethod.GET)
	public String writeForm() throws Exception {
		return "/board/writeFm";			
	}
		
		
	/**
	 * 
	  * @Method Name : write
	  * @작성일 : 2016. 3. 13.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 : 게시판 글등록
	  * @param request
	  * @param response
	  * @param model
	  * @param board
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/board/new",method = RequestMethod.POST)
	public String write(HttpServletRequest request,HttpServletResponse response,ModelMap model,@ModelAttribute Board board) throws Exception {
		boardService.insert(board);
		
		return "redirect:/boards";			
	}
	
	/**
	 * 
	  * @Method Name : writeForm
	  * @작성일 : 2016. 3. 13.
	  * @작성자 : hoyeon
	  * @변경이력 : 
	  * @Method 설명 : 게시판 수정폼
	  * @param boardId
	  * @param model
	  * @return
	  * @throws Exception
	 */
    @RequestMapping(value = "/board/{boardId}/edit", method = RequestMethod.GET)
    public String writeForm(@PathVariable("boardId") Long boardId, Model model) throws Exception{
    	model.addAttribute("result",boardService.selectById(boardId));
    	return "/board/writeFm";

    }
    
    
  /**
   * 
    * @Method Name : updateBoard
    * @작성일 : 2016. 3. 13.
    * @작성자 : hoyeon
    * @변경이력 : 
    * @Method 설명 : 게시판수정폼
    * @param boardId
    * @param board
    * @param status
    * @return
    * @throws Exception
   */
    @RequestMapping(value = "/board/{boardId}/edit", method = RequestMethod.PUT)
    public String updateBoard(@PathVariable("boardId") Long boardId,@ModelAttribute("params") Board board, SessionStatus status)throws Exception{
    	board.setId(boardId);
    	boardService.update(board);
        //status.setComplete();
        return "redirect:/boards";
    }
    
    
	 /**
	  * 
	   * @Method Name : deleteBoard
	   * @작성일 : 2016. 3. 13.
	   * @작성자 : hoyeon
	   * @변경이력 : 게시판삭제
	   * @Method 설명 :
	   * @param boardId
	   * @param board
	   * @param status
	   * @return
	   * @throws Exception
	  */
     @RequestMapping(value = "/board/{boardId}/edit", method = RequestMethod.DELETE)
     public String deleteBoard(@PathVariable("boardId") Long boardId,@ModelAttribute("params") Board board, SessionStatus status)throws Exception{
    	board.setId(boardId);
     	boardService.delete(board);
         return "redirect:/boards";
     }
    
	
	
	

}
