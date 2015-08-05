package com.git.iboke.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class JacksonUtil {

	private static final ObjectMapper m = new ObjectMapper();
	

	/**
	 * 将指定字符串,转换为某个受泛型约束的JAVA对象
	 * @param jsonString
	 * @param typeRef
	 * @return
	 * @throws Js---onParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T toBean(String jsonString,TypeReference<T> typeRef) throws Exception {
		try {
			if(StringUtils.isNotEmpty(jsonString))
				return m.readValue(jsonString, typeRef);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e);
		} 
		return null;
	}
	/**
	 * 将任意对象转换为json字符串
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static String toJSONString(Object obj) throws Exception {
		try {
			return m.writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e);
		}
	}
	
	/**
	 * 输出对象数组的json数据,如[{"code":"","descn":"","id":0,"cname":"name1"},{"code":"","descn":"","id":0,"cname":"name2"}]
	 * 
	 * @param array
	 * 对象数组
	 * @param excludesProperties
	 * 不需要转换到json数据中的属性名称构成的数组 如String[] {"roleses","departments","department"}
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public static String toJSONStringExcludes(Object array, String[] excludesProperties) throws Exception {
		try {
			JsonConfig jsonConfig = new JsonConfig();
			if (excludesProperties != null)
				jsonConfig.setExcludes(excludesProperties);
			return JSONArray.fromObject(array, jsonConfig).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e);
		}
	}
	
	/**
	 * 简单Bean(不包含任何泛型约束),直接传Class对象进来
	 * @param jsonString
	 * @param t
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T toBean(String jsonString,Class<T> t) throws Exception{
		try {
			if(StringUtils.isNotEmpty(jsonString))
			return m.readValue(jsonString, t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e);
		}
		return null;
	}
	
	
	public static void main(String[] args) throws Exception {
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		
		for(int i =0;i<5;i++){
			List<String> list = new ArrayList<String>();
			for(int j =0;j<10;j++){
				list.add(i+":"+j);
			}
			map.put(i, list);
		}
		//转换为json字符串
		String jsonString = toJSONString(map);
		//将该字符串再转换为Map<Integer,List<String>>
		Map<Integer,List<String>> map1 = toBean(jsonString, new TypeReference<HashMap<Integer,List<String>>>() {});
		for(Entry<Integer,List<String>> e : map1.entrySet()){
			System.out.println(e.getKey());
			for (String string : e.getValue()) {
				System.out.println("\t"+string);
			}
		}
	}	

}
