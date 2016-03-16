package web.common.util;

import java.security.MessageDigest;


public class SHA256
{
    

	//SHA256만 적용한 암호화
    public String encryptSHAStr(String val) throws Exception {
		// java.security.MessageDigest 사용 예제 1
		//
		MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
		byte[] SHA256_VALUE1 = SHA256.digest(val.getBytes());

        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < SHA256_VALUE1.length ; i++){
            sb.append(Integer.toString((SHA256_VALUE1[i]&0xff) + 0x100, 16).substring(1));
        }
        
        // SHA만 적용한 암호화
        //
        return sb.toString();
    }
}