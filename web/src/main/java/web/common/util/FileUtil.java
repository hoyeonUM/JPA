package web.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

import web.common.vo.FileVO;




@Repository("FileUtil")
public class FileUtil {

	
	static final String FILESTOREPATH=Properties.getProperty("Globals.fileStorePath");
	static final String NOTFILEUPLOAD=Properties.getProperty("Globals.notFileUpLoad");
	static final long FILEMAXSIZE=Long.parseLong(Properties.getProperty("Globals.fileMaxSize"));
	public static final int BUFFER_SIZE = 8192;	
	
	
	
	/**
	 * 파일 업로드
	 * 
	 * */
	public List<FileVO> fileUpload(HttpServletRequest request,FileVO param) throws Exception {
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileIter = mptRequest.getFileNames();
		int check = 0;
		int total = 0;

		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());
			if ("".equals(mFile.getOriginalFilename())) {
				check++;
			} else {
				// 최대 업로드 사이즈 비교
				if (FILEMAXSIZE < mFile.getSize()) {
					ModelAndView modelAndView = new ModelAndView("cmm/error/exceedFileSize" );
					throw new ModelAndViewDefiningException(modelAndView);
				}
			}
			total++;
		}

		if (check == total) {
			return fileList;
		}

		// 현재날짜 가져오기
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd",Locale.getDefault());
		String today = format.format(new Date());

		// 업로드 할 파일경로
		// 클럽일 경우 사이트경로/클럽번호
		String upload_file_folder = FILESTOREPATH
				+ File.separator
				+ "file"
				+ File.separator
				+ param.getFileGubun()
				+ File.separator;

		// 실제 파일 업로드 시작
		fileIter = mptRequest.getFileNames();
		while (fileIter.hasNext())
		{
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());
			String origin_file_name = mFile.getOriginalFilename();
			long fileSize =  mFile.getSize();
			if (origin_file_name.lastIndexOf("\\") >= 0)origin_file_name = origin_file_name.substring(origin_file_name.lastIndexOf("\\") + 1);
				String file_extension = origin_file_name.substring(origin_file_name.lastIndexOf(".") + 1);
				boolean isAllowExtension = fileExtensionAllow(file_extension);// 파일확장자체크
				if (!isAllowExtension)
				{
					ModelAndView modelAndView = new ModelAndView("/cmm/error/isAllowExtension");
					throw new ModelAndViewDefiningException(modelAndView);
				}
				
				
				// 이미지 게시판인 경우는 이미지 파일만 업로드 가능하게 처리
				if ("images".indexOf(param.getFileGubun()) > -1)
				{
					String filterextension = ".jpg,.gif,.png,.bmp,.jpeg,.jpe,.jfif";
					filterextension = filterextension.toLowerCase();
					if (filterextension.indexOf(file_extension.toLowerCase()) < 0)
					{
						ModelAndView modelAndView = new ModelAndView("/cmm/error/isAllowExtension");
						throw new ModelAndViewDefiningException(modelAndView);
					}
				}
				
			try{	
			FileVO fileVO = new FileVO();
					
			// 파일에 등록 할 데이터 세팅
			String file_folder = File.separator+ "file"+ File.separator+ param.getFileGubun()+ File.separator;
			file_folder += today+ File.separator;
			long file_no = System.currentTimeMillis();
			// 서버에 파일저장
			saveFile(mFile.getInputStream(), new File(upload_file_folder + file_no + "."+ file_extension));

			fileVO.setFileExtension(file_extension);
			fileVO.setFileFolder(file_folder);
			fileVO.setOriginFileName(origin_file_name);
			fileVO.setFileNo(file_no);
			fileVO.setFileSize(fileSize);
			
			//썸네일이 존재하면
			if(null != param.getThumnails()){
				String realpath = upload_file_folder;
				String saveFullPath = upload_file_folder + file_no
						+ "." + file_extension;
				for(String str[] : param.getThumnails()){
					thumbConvertRate(saveFullPath, realpath
							+ File.separator + "thum_"+ str[0] + file_no
							+ "." + file_extension, Integer.parseInt(str[0]),
							Integer.parseInt(str[1]), file_extension);
				}
			}
			fileList.add(fileVO);

			} catch (Exception e)
				{
					for (FileVO FileVO : fileList) {// 에러날 시 파일 삭제
						File uf = new File(
											FILESTOREPATH
										+ FileVO.getFileFolder()
										+ FileVO.getFileName()
										+ "."
										+ FileVO.getFileExtension());
						if (uf.exists()) {
							uf.delete();
						}

					}

					fileList = null;
					return fileList;
				}
		} //end while



		return fileList;
	}
	
/**
 * 
  * @Method Name : fileExtensionAllow
  * @작성일 : 2016. 3. 13.
  * @작성자 : hoyeon
  * @변경이력 : 
  * @Method 설명 :  서버 파일의 확장자를 체크하여 업로드가능한 파일인지 체크한다.
  * @param file_extension 파일확장자
  * @return
  * @throws Exception
 */
public boolean fileExtensionAllow(String file_extension) throws Exception{
	boolean isAllow = false;
	String notAllowFileExtension =NOTFILEUPLOAD;
	
	notAllowFileExtension = notAllowFileExtension.toLowerCase();
	//업로드금지된 확장명에 속한다면
	if(notAllowFileExtension.indexOf(file_extension.toLowerCase()) > -1){
		isAllow = false;
	}else{
		isAllow = true;
	}
	return isAllow;
}

/**
 * 
  * @Method Name : saveFile
  * @작성일 : 2016. 3. 13.
  * @작성자 : hoyeon
  * @변경이력 : 
  * @Method 설명 :Stream으로부터 파일을 저장함.
  * @param is
  * @param file
  * @return
  * @throws IOException
 */
public long saveFile(InputStream is, File file) throws IOException {
// 디렉토리 생성
if (! file.getParentFile().exists()) {
    file.getParentFile().mkdirs();
}

//사용자 사진은 하나이기때문에 지워준다.
if(file.exists())file.delete();	


OutputStream os = null;
long size = 0L;

try {
    os = new FileOutputStream(file);

    int bytesRead = 0;
    byte[] buffer = new byte[8192];

    while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
	size += bytesRead;
	os.write(buffer, 0, bytesRead);
    }
} finally {
    if (os != null) {
	os.close();
    }
}

return size;
}
    
/**
 * 
  * @Method Name : thumbConvert
  * @작성일 : 2016. 3. 13.
  * @작성자 : hoyeon
  * @변경이력 : 
  * @Method 설명 : 일반 썸네일 이미지 만들기
  * @param in
  * @param out
  * @param width
  * @param height
  * @param format
  * @return
 */
public static boolean thumbConvert(String in, String out, int width,
		int height, String format) {
	boolean ret = false;
	File inFile = null;
	try {
		File saveFile = new File(out);
		inFile = new File(in);

		if (inFile.isFile()) {
			if (inFile.length() < 50000000) { // 50M 이하만 썸네일 등록.

			BufferedImage im = ImageIO.read(inFile);

			ResampleOp resampleOp = new ResampleOp(width, height);

			resampleOp
					.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);

			BufferedImage rescaledImage = resampleOp.filter(im, null);

			ret = ImageIO.write(rescaledImage, format, saveFile);
		}
	}
} catch (Exception io) {
	// System.out.println(in);
	// io.printStackTrace();
		return false;
	}
	return ret;
}
    
	
  /**
   * 
    * @Method Name : thumbConvertRate
    * @작성일 : 2016. 3. 13.
    * @작성자 : hoyeon
    * @변경이력 : 
    * @Method 설명 : 썸네일 이미지 만들기 비율에 맞춰서
    * @param in
    * @param out
    * @param width
    * @param height
    * @param format
    * @return
   */
public boolean thumbConvertRate(String in, String out, int width,
		int height, String format) {
	boolean ret = false;
	File inFile = null;
	try {
		File saveFile = new File(out);
		inFile = new File(in);

		if (inFile.isFile()) {
			if (inFile.length() < 50000000) { // 50M 이하만 썸네일 등록.

				BufferedImage im = ImageIO.read(inFile);
				
				//실제 이미지 size
				int imageWidth = im.getWidth(null);   
	            int imageHeight = im.getHeight(null); 
		        
	            int wantWeight = width;
	            int wantHeight = height;
	            
	            double ratio =Math.max((double)wantWeight/ (double)imageWidth, (double)wantHeight/ (double)imageHeight);
		        
	            int w = (int)(imageWidth * ratio);
				int h = (int)(imageHeight * ratio);
				
				ResampleOp resampleOp = new ResampleOp(w, h);
				
				
				

				resampleOp
						.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);

				BufferedImage rescaledImage = resampleOp.filter(im, null);

				ret = ImageIO.write(rescaledImage, format, saveFile);
			}
		}
	} catch (Exception io) {
		// System.out.println(in);
		// io.printStackTrace();
		return false;
	}
	return ret;
}
		
	
	
	







/**
 * 
  * @Method Name : deleteFile
  * @작성일 : 2016. 3. 13.
  * @작성자 : hoyeon
  * @변경이력 : 
  * @Method 설명 : 파일물리삭제
  * @param list
  * @throws Exception
 */
public void deleteFile(List<FileVO> list) throws Exception {
	
	for(FileVO vo : list){
		File file = new File(FILESTOREPATH+File.separator+vo.getFileFolder()+vo.getFileName());
		if(file.exists())file.delete();
	}

}









/**
 * 
  * @Method Name : downLoadFileByMaking
  * @작성일 : 2016. 3. 13.
  * @작성자 : hoyeon
  * @변경이력 : 
  * @Method 설명 : 파일다운로드
  * @param request
  * @param response
  * @param vo
  * @throws Exception
 */
public void downLoadFileByMaking(HttpServletRequest request,
		HttpServletResponse response, FileVO vo) throws Exception {

	String browser = getBrowser(request);


	String downFileName ="";
	String orgFileName = "";
	String downFileFolder = "";
	
	orgFileName = vo.getOriginFileName();
	downFileFolder = vo.getFileFolder()+vo.getFileName();
		
	
	
	
	
	
	File file = new File((FILESTOREPATH+ downFileFolder + downFileName).trim());
	String resultName = "";

	if (!file.exists()) {
		throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
		throw new FileNotFoundException(downFileName);
	}

	try {
		// Explorer
		if (browser.indexOf("MSIE") != -1) {
			resultName = new String(orgFileName.getBytes("EUC-KR"),
					"ISO-8859-1").replaceAll(" ", "%20");
		}
		// Opera
		else if (browser.indexOf("Opera") != -1) {
			resultName = new String(orgFileName.getBytes("UTF-8"),
					"ISO-8859-1");
		}
		// Chrome
		else if (browser.indexOf("Chrome") != -1) {
			resultName = new String(orgFileName.getBytes("EUC-KR"),
					"ISO-8859-1");
		}
		// Safari
		else if (browser.indexOf("Safari") != -1) {
			resultName = new String(orgFileName.getBytes("UTF-8"),
					"ISO-8859-1");
		}
		// FireFox
		else if (browser.indexOf("Firefox") != -1) {
			resultName = new String(orgFileName.getBytes("UTF-8"),
					"ISO-8859-1");
		}
		// Other
		else {
			resultName = new String(orgFileName.getBytes("EUC-KR"),
					"ISO-8859-1");
		}
	} catch (Exception ex) {
		resultName = orgFileName;
	}

	// byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename=\""
			+ resultName + "\";");
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	byte[] b = new byte[BUFFER_SIZE];

	BufferedInputStream fin = null;
	BufferedOutputStream outs = null;

	try {
		fin = new BufferedInputStream(new FileInputStream(file));
		outs = new BufferedOutputStream(response.getOutputStream());

		int read = 0;
		while ((read = fin.read(b)) != -1) {
			outs.write(b, 0, read);
		}
	} finally {
		if (outs != null) {
			outs.close();
		}
		if (fin != null) {
			fin.close();
		}
	}
}




/**
 * @brief 브라우저 판별 메서드
 * @author
 * @param request
 * @return
 */
public static String getBrowser(HttpServletRequest request) {
	String header = request.getHeader("User-Agent");
	if (header.indexOf("MSIE") > -1) {
		return "MSIE";
	} else if (header.indexOf("Chrome") > -1) {
		return "Chrome";
	} else if (header.indexOf("Opera") > -1) {
		return "Opera";
	} else if (header.indexOf("Safari") > -1) {
		return "Safari";
	}
	return "Firefox";
}




















		
}



