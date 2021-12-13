package beans.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserPwd {
	public static String hashPwd(String pwd){
//		String originalPwd = "test";
        String pwdHash = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            //Add password bytes to digest
            md.update(pwd.getBytes());
            
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            //Get complete hashed password in hex format
            pwdHash = sb.toString();
            
            //Add a salt HERE
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        System.out.println(pwdHash);
        return pwdHash;
	}
  
        

}
