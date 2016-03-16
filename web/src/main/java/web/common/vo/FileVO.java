package web.common.vo;

import java.util.List;

public class FileVO {
	private String fileName;
	private long fileSize;
	private long fileNo;
	private String originFileName;
	private String fileFolder;
	private String fileExtension;
	private String fileGubun;
	private List<String[]> thumnails = null;
	
	public List<String[]> getThumnails() {
		return thumnails;
	}
	public void setThumnails(List<String[]> thumnails) {
		this.thumnails = thumnails;
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
	public String getFileGubun() {
		return fileGubun;
	}
	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
	}
	public long getFileNo() {
		return fileNo;
	}
	public void setFileNo(long fileNo) {
		this.fileNo = fileNo;
	}
}
