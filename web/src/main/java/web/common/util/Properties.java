package web.common.util;

import java.io.FileInputStream;



/**
 * 프로퍼티의 값을 가져올수 있는 메소드 
 * 전자정부프레임워크 참조함
 * 서블릿을 상속받지 않아도 경로를 알아낼수있음
 * @author hoyeon
 *
 */
public class Properties {

	
	public static final String RELATIVE_PATH_PREFIX = Properties.class.getResource("").getPath()
		    + System.getProperty("file.separator") + ".." + System.getProperty("file.separator")
		    + ".." + System.getProperty("file.separator");
	
	public static final String ERR_CODE =" EXCEPTION OCCURRED";
	
    public static final String GLOBALS_PROPERTIES_FILE
    = RELATIVE_PATH_PREFIX + "properties" + System.getProperty("file.separator") + "globals.properties";
    
    public static final String SERVLETREALPATH = Properties.class.getResource("").getPath()
		    + System.getProperty("file.separator") + ".." + System.getProperty("file.separator")
		    + ".." + System.getProperty("file.separator")+ ".." + System.getProperty("file.separator")+".." + System.getProperty("file.separator");

    
    
    /**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * @param keyName String
	 * @return String
	*/
    public static String getProperty(String keyName){
		String value = ERR_CODE;
		value="99";
		FileInputStream fis = null;
		try{
			java.util.Properties props = new java.util.Properties();
			fis  = new FileInputStream(GLOBALS_PROPERTIES_FILE);
			props.load(new java.io.BufferedInputStream(fis));
			value = props.getProperty(keyName).trim();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (fis != null) fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return value;
	}
    
	
	
	
    
}
