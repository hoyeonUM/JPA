package web.common.vo;


import java.io.Serializable;


/**
 * 공통 vo
  * @FileName : CommonVO.java
  * @Project : web
  * @Date : 2016. 3. 12. 
  * @작성자 : hoyeon
  * @변경이력 :
  * @프로그램 설명 :
 */
public class CommonVO implements Serializable {
	
	
	private static final long serialVersionUID = 1297967145392984769L;
	
	private String searchType=""; //검색타입
	private String searchValue =""; //검색어
  
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
    
	
}
