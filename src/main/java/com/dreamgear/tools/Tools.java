package com.dreamgear.tools;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Tools {

	static String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"1","2","3","4","5","6","7","8","9","0"};
	/**
	 * 
	 * 产生随机字符串，不长于32位
	 * @param int $length
	 * @return 产生的随机字符串
	 */
	public static String getNonceStr(int length) 
	{  
		String ret ="";
		for ( int i = 0; i < length; i++ )  {  
			ret += chars[(int) (Math.random() * chars.length)];
		} 
		return ret;
	}
	
	public static String getMd5(String str){
		try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return (new BigInteger(1, md.digest())).toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return "";
	}
}
