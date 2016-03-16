package web.common.util.string;

public class StringUtil {

	
	public static boolean hasValue(Object obj){
		boolean returnValue = false;
		if (obj instanceof String) {
			returnValue = obj.toString().length() > 0 ? true : false;
		}
		return returnValue;
	}
	
	public static String appendLike(String obj,String type){
		String returnValue ="";
			if("left".equals(type)){
				returnValue = "%"+obj; 
			}else if("right".equals(type)){
				returnValue = obj + "%";
			}else{
				returnValue = "%" + obj+ "%";
			} 
		return returnValue;
	}
	
	
}
