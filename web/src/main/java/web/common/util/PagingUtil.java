package web.common.util;

import org.springframework.data.domain.Page;

import web.common.util.pageination.AbstractPaginationRenderer;
import web.common.util.pageination.PaginationInfo;
import web.common.vo.CommonVO;


public class PagingUtil extends AbstractPaginationRenderer {
	
	//public PaginationInfo paginationInfo;
	//페이징 공통 함수
	//Paging(리스트 갯수, VO)
	public static <T> PaginationInfo Paging(Page<T> page,int pageSize){
		/** EgovPropertyService.sample */
    	/** pageing setting */
		PaginationInfo paginationInfo= new PaginationInfo();
		paginationInfo.setCurrentPageNo(page.getNumber()+1);
		paginationInfo.setRecordCountPerPage(page.getSize());
		paginationInfo.setPageSize(pageSize);
		paginationInfo.setTotalRecordCount((int)page.getTotalElements());
		
		
		
		
		
		return paginationInfo;
		
	}

		
		
		
		
}