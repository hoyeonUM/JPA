package web.common.util;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import web.common.util.pageination.DefaultPaginationManager;
import web.common.util.pageination.PaginationInfo;
import web.common.util.pageination.PaginationManager;
import web.common.util.pageination.PaginationRenderer;
import web.common.util.pageination.PaginationTag;

public class PaginationTagEx extends PaginationTag {

	private PaginationInfo paginationInfo;
	private String type;
	private String jsFunction;
	private String action;
	
	public int doEndTag() throws JspException{
		
		try {
			
			JspWriter out = pageContext.getOut();
			
			PaginationManager paginationManager;
			
            // WebApplicationContext에 id 'paginationManager'로 정의된 해당 Manager를 찾는다.
            WebApplicationContext ctx = RequestContextUtils.getWebApplicationContext(pageContext.getRequest(), pageContext.getServletContext());
            
            if(ctx.containsBean("paginationManager")){
            	paginationManager = (PaginationManager) ctx.getBean("paginationManager");
            }else{
            	//bean 정의가 없다면 DefaultPaginationManager를 사용. 빈설정이 없으면 기본 적인 페이징 리스트라도 보여주기 위함.
            	paginationManager = new DefaultPaginationManager();
            }
            
            PaginationRenderer paginationRenderer = paginationManager.getRendererType(type);
            
            String contents = renderPagination(type, paginationInfo, jsFunction, action);
            
            out.println(contents);
            
            return EVAL_PAGE;
            
        } catch (IOException e) {
            throw new JspException();
        }
	}

	public String firstPageLabel;
	public String previousPageLabel;
	public String currentPageLabel;
	public String otherPageLabel;
	public String nextPageLabel;
	public String lastPageLabel;
	
	public String renderPagination(String intype, PaginationInfo paginationInfo,String jsFunction, String action){
		//paginationInfo = PagingUtil.paginationInfo;
		if("adminpaging".equals(intype)){
			//////////////관리자/////////
			firstPageLabel = "<a class='first' href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\">" +
							 "<img alt='처음' src='/images/viya/manager/btn_pagenavi_fist.gif' border=0/></a>";
			// 이전페이지
	        previousPageLabel = "<a class='prev' href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\">" +
	        					"<img alt='이전' src='/images/viya/manager/btn_pagenavi_prev.gif' border=0/></a>";
	        // 현재 페이징 번호
	        currentPageLabel = "<strong>{0}</strong>";
	        
	        // 다른 페이징 번호
	        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\">{2}</a>";
	        
	        // 다음페이지
	        nextPageLabel = "<a class='next' href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\">" +
	        				"<image src='/images/viya/manager/btn_pagenavi_next.gif' border=0/></a>";
	        
	        //마지막 페이징 번호
	        lastPageLabel = "<a class='end' href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\">" +
	        				"<image src='/images/viya/manager/btn_pagenavi_end.gif' border=0/></a>";
		}else {
			firstPageLabel = "";
			// 이전페이지
	        previousPageLabel = "<span class='prev'><span onclick=\"{0}({1},''"+action+"'');\" style=\"cursor: pointer;\">이전</span></span>&#160;";
	        
	        // 현재 페이징 번호
	        currentPageLabel = "<b>{0}</b>&#160;";
	        
	        // 다른 페이징 번호
	        otherPageLabel = "<a href=\"#\" onclick=\"{0}({1},''"+action+"''); return false;\" style=\"text-align:center;\">{2}</a>&#160;";
	        
	        // 다음페이지
	        nextPageLabel = "<span class='next'><span onclick=\"{0}({1},''"+action+"''); return false;\" style=\"cursor: pointer;\">다음</span></span>";
	        
	        //마지막 페이징 번호
	        lastPageLabel = "";
		}
		
		StringBuffer strBuff = new StringBuffer();
        
        int firstPageNo = paginationInfo.getFirstPageNo();
        int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
        int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();
		
//		System.out.println("totalPageCount: " + totalPageCount);
//		System.out.println("firstPageNo: " + firstPageNo);
//		System.out.println("lastPageNo: " + lastPageNo);
		
		if(totalPageCount > pageSize){
			if(firstPageNoOnPageList > pageSize){
				strBuff.append(MessageFormat.format(firstPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNo)}));
				strBuff.append(MessageFormat.format(previousPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNoOnPageList-1)}));
	        }else{
	        	strBuff.append(MessageFormat.format(firstPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNo)}));
				strBuff.append(MessageFormat.format(previousPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNo)}));
	        }
		}
		
		for(int i=firstPageNoOnPageList;i<=lastPageNoOnPageList;i++){
			if(i==currentPageNo){
        		strBuff.append(MessageFormat.format(currentPageLabel,new Object[]{Integer.toString(i)}));
        	}else{
        		strBuff.append(MessageFormat.format(otherPageLabel,new Object[]{jsFunction,Integer.toString(i),Integer.toString(i)}));
        	}
        }
        
		if(totalPageCount > pageSize){
			if(lastPageNoOnPageList < totalPageCount){
	        	strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNoOnPageList+pageSize)}));
	        	strBuff.append(MessageFormat.format(lastPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo)}));
	        }else{
	        	strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo)}));
	        	strBuff.append(MessageFormat.format(lastPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo)}));
	        }
		}
		return strBuff.toString();
	}

	public final PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}

	public final void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final String getJsFunction() {
		return jsFunction;
	}

	public final void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}

	public final String getAction() {
		return action;
	}

	public final void setAction(String action) {
		this.action = action;
	}

	public final String getFirstPageLabel() {
		return firstPageLabel;
	}

	public final void setFirstPageLabel(String firstPageLabel) {
		this.firstPageLabel = firstPageLabel;
	}

	public final String getPreviousPageLabel() {
		return previousPageLabel;
	}

	public final void setPreviousPageLabel(String previousPageLabel) {
		this.previousPageLabel = previousPageLabel;
	}

	public final String getCurrentPageLabel() {
		return currentPageLabel;
	}

	public final void setCurrentPageLabel(String currentPageLabel) {
		this.currentPageLabel = currentPageLabel;
	}

	public final String getOtherPageLabel() {
		return otherPageLabel;
	}

	public final void setOtherPageLabel(String otherPageLabel) {
		this.otherPageLabel = otherPageLabel;
	}

	public final String getNextPageLabel() {
		return nextPageLabel;
	}

	public final void setNextPageLabel(String nextPageLabel) {
		this.nextPageLabel = nextPageLabel;
	}

	public final String getLastPageLabel() {
		return lastPageLabel;
	}

	public final void setLastPageLabel(String lastPageLabel) {
		this.lastPageLabel = lastPageLabel;
	}
	
	
}
