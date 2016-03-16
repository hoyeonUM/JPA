package web.domain;

import java.util.ArrayList;
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

@Entity
@Table(name="BOARD_FILE")
public class BoardFile {

	@Id @GeneratedValue
    @Column(name = "FILE_KEY")
	private long  fileKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_KEY")
	private Board board;
	
	
    @OneToMany(mappedBy = "BoardFileList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<Board>();
    
	
	@Column(name = "FILE_NAME",nullable=false)
	private String fileName;
	
	@Column(name = "FILE_SIZE",nullable=false)
	private long fileSize;
	
	@Column(name = "ORIGIN_FILE_NAME",nullable=false)
	private String originFileName;
	
	@Column(name = "FILE_FOLDER",nullable=false)
	private String fileFolder;
	
	@Column(name = "FILE_EXTENSION",nullable=false)
	private String fileExtension;
	
	@Column(name = "USE_YN",insertable = false, columnDefinition="CHAR(1) DEFAULT 'Y'")
	private char useYn;

	public long getFileKey() {
		return fileKey;
	}

	public void setFileKey(long fileKey) {
		this.fileKey = fileKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public char getUseYn() {
		return useYn;
	}

	public void setUseYn(char useYn) {
		this.useYn = useYn;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	
	
	
	
}
