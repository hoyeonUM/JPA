package web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BOARD_TYPE")
public class BoardType {
	
	
	@Id
    @Column(name = "BOARD_TYPE")
	private String boardType;
	
	
	@OneToMany(mappedBy = "boardType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<Board>();
	
	
	@Column(name = "BOARD_TYPE_NAME")
	private String boardTypeName;
	
	@Column(name = "USE_YN")
	private String useYn;
	
	@Column(name = "BOARD_GROUP")
	private String boardGroup;
	
	@Column(name = "BOARD_ORDER_NUM")
	private String boardOrderNum;
	
	@Column(name = "BOARD_PARENT_KEY")
	private String boardParentKey;
	
	@Column(name = "REG_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@Column(name = "MOD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;
	
	@Column(name = "REG_ID")
	private String regId;
	
	@Column(name = "MOD_ID")
	private String modId;
	
	@Column(name = "REG_IP")
	private String regIp;
	
	@Column(name = "MOD_IP")
	private String modIp;
	
	@Column(name = "MENU_KEY")
	private String menuKey;

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public List<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getBoardGroup() {
		return boardGroup;
	}

	public void setBoardGroup(String boardGroup) {
		this.boardGroup = boardGroup;
	}

	public String getBoardOrderNum() {
		return boardOrderNum;
	}

	public void setBoardOrderNum(String boardOrderNum) {
		this.boardOrderNum = boardOrderNum;
	}

	public String getBoardParentKey() {
		return boardParentKey;
	}

	public void setBoardParentKey(String boardParentKey) {
		this.boardParentKey = boardParentKey;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getModIp() {
		return modIp;
	}

	public void setModIp(String modIp) {
		this.modIp = modIp;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	
	
}
