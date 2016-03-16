package web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

@Entity
@Table(name="BOARD")
public class Board {

	@Id @GeneratedValue
    @Column(name = "BOARD_KEY")
    private Long id;
	 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_TYPE")
	private BoardType boardType;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardFile> BoardFileList = new ArrayList<BoardFile>();

	
	@Column(name = "TITLE",nullable=false)
	private String title;
	
	@Column(name = "CONTENTS",nullable=false)
	private String contents;
	
	@Column(name = "USE_YN",insertable = false, columnDefinition="CHAR(1) DEFAULT 'Y'")
	private String useYn;
	
	@Column(name = "READ_COUNT",insertable = false, columnDefinition="NUMBER DEFAULT 1")
	private long readCount;
	
	@Column(name = "SECRET_YN")
	private String secretYn;
	
	@Column(name = "REG_ID")
	private String regId;
	
	@Column(name = "MOD_ID")
	private String modId;
	
	@Column(name = "REG_IP")
	private String regIp;
	
	@Column(name = "MOD_IP")
	private String modIp;
	
	
	@Column(name = "REG_DATE",insertable = false, columnDefinition="DATE DEFAULT CURRENT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@Column(name = "MODE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modeDate;
	
	@Column(name = "TOP_YN",columnDefinition="CHAR(1) DEFAULT 'N'")
	private String topYn;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public long getReadCount() {
		return readCount;
	}

	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}

	public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModeDate() {
		return modeDate;
	}

	public void setModeDate(Date modeDate) {
		this.modeDate = modeDate;
	}

	public String getTopYn() {
		return topYn;
	}

	public void setTopYn(String topYn) {
		this.topYn = topYn;
	}


	public BoardType getBoardType() {
		return boardType;
	}

	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	public List<BoardFile> getBoardFileList() {
		return BoardFileList;
	}

	public void setBoardFileList(List<BoardFile> boardFileList) {
		BoardFileList = boardFileList;
	}

	
}
