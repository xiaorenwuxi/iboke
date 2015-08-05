package com.git.iboke.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Util {

	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRealIpAddr(request);
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
	
	/**
	 * 判断是否为合法IP
	 * 
	 * @return the ip
	 */
	public static boolean isboolIp(String ipAddress) {
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
						+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
						+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\."
						+ "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ipAddress); // 以验证127.400.600.2为例
		return matcher.matches();
	}
	
	
	public static boolean isNumber(String number){
		Pattern pattern = Pattern
				.compile("\\[^0-9.]+\\");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}
	
	public static String fmtObj(Object obj){
		return obj==null || obj==""?"":(String.valueOf(obj).trim().equalsIgnoreCase("null") || String.valueOf(obj).trim().equalsIgnoreCase("\"null\"") ?"":(String.valueOf(obj).trim().equalsIgnoreCase("undefined")?"":String.valueOf(obj).trim()));
	}
	
	
	@SuppressWarnings("rawtypes")
	private static String getRealIpAddr(HttpServletRequest request) {
		String localIp = "127.0.0.1";
		try {
			Enumeration e1 = (Enumeration) NetworkInterface.getNetworkInterfaces();
			while(e1.hasMoreElements()){
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				
				if(ni.getName().equalsIgnoreCase("lo")){
					continue;
				}
				Enumeration e2 = ni.getInetAddresses(); 
				while (e2.hasMoreElements()) {
					 InetAddress ia = (InetAddress) e2.nextElement();
					 if (ia instanceof Inet6Address){
						 continue;
					 }
					 localIp =ia.getHostAddress();
					 if (!e2.hasMoreElements()) {
						 return localIp;
					 }
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return localIp;
	}
	
	/**
	 * 转换成二进制的字符串显示
	 * @param num 需转换的对象
	 * @param bitNum 转换后最终二进制位数显示
	 * @return
	 */
	public static String convertBinary(int num,int bitNum){
		 	String s = "";  
	        boolean minus = (num < 0);  
	        int length = 0;  
	        //如果是负数，求其反码  
	        if (minus)
	        	num = -num;  
	        while (num > 0) {  
	            if (num % 2 != 0)  
	                s = "1" + s;  
	            else
	                s = "0" + s;  
	            num /= 2;  
	            length++;  
	        }  
	        if (minus){
	        	 if(length<bitNum){
						for(int k=0;k<bitNum-length;k++){
							s ="0"+s;
						}
				   }
	        	 System.out.println("before getting reverse value = "+s);
	        	 //取反
	        	 s = s.replaceAll("0", "A");
	        	 s = s.replaceAll("1", "0");
	        	 s = s.replace("A", "1");
	        	 System.out.println("after getting reverse value = "+s);
	        	 boolean isAdd = true;
	        	 String nStr = "";
	        	 for(int i=s.toCharArray().length-1;i>=0;i--){
	        		 switch(s.toCharArray()[i]){
	        			 case '0':{
	        				 if(isAdd){
	        					 nStr ="1"+nStr;
	        					 isAdd = false;
	        				 }else{
	        					 nStr ="0"+nStr;
	        				 }
	        			 };break;
	        			 case '1':{
	        				 if(isAdd){
	        					 nStr ="0"+nStr;
	        				 }else{
	        					 nStr ="1"+nStr;
	        				 }
	        			 };break;
	        		 }
	        		
	        	 }
	        	 
	        	 System.out.println(" after reversing value and plus one the value="+nStr);
	        	 s = nStr;
	        }
		   else{
			   if(length<bitNum){
					for(int k=0;k<bitNum-length;k++){
						s ="0"+s;
					}
			   }
		   }
	       return s;
	}
	
	
	public static int convertInt(String binaryNum){
		int r = 0;
		char[] chArray = binaryNum.toCharArray();
		if(chArray[0]=='0'){//正数转换
			for(int i=chArray.length-1;i>=0;i--){
				r += Integer.valueOf(String.valueOf(chArray[i]))*((int)Math.pow(2, chArray.length-1-i));
			}
		}else if(chArray[0]=='1'){//负数转换
		 boolean isdel = true;
       	 String nStr = "";
       	 for(int i=binaryNum.toCharArray().length-1;i>=0;i--){
       		 switch(binaryNum.toCharArray()[i]){
       			 case '0':{
       				 if(isdel){
       					 nStr ="1"+nStr;
       				 }else{
       					 nStr ="0"+nStr;
       				 }
       			 };break;
       			 case '1':{
       				 if(isdel){
       					 nStr ="0"+nStr;
       					 isdel = false;
       				 }else{
       					 nStr ="1"+nStr;
       				 }
       			 };break;
       		 }
       		
       	 }
       	 System.out.println("未取反前："+nStr);
       	binaryNum = nStr;
       	binaryNum = binaryNum.replaceAll("0", "A");
       	binaryNum = binaryNum.replaceAll("1", "0");
       	binaryNum = binaryNum.replace("A", "1");
       	System.out.println("取反后："+binaryNum);
       	chArray = binaryNum.toCharArray();
       	for(int i=chArray.length-1;i>=0;i--){
			r += Integer.valueOf(String.valueOf(chArray[i]))*((int)Math.pow(2, chArray.length-1-i));
		}
       	r = -r;
	}
		return r;
	}
	
	public static void main(String[] args){
		String binaryNum = convertBinary(-1,32);
		System.out.println(binaryNum);
		System.out.println(convertInt(binaryNum));
	}
}
