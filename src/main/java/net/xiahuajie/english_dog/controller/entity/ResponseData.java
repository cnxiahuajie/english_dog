package net.xiahuajie.english_dog.controller.entity;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * 响应数据实体
 * 
 * @author xiahuajie
 *
 */
@Data
public class ResponseData {

	private String code;

	private String msg;

	private String data;
	
	public static ResponseData unauth() {
		ResponseData data = new ResponseData();
		data.setCode("4003");
		data.setMsg("unauth.");
		return data;
	}

	public static ResponseData denied() {
		ResponseData data = new ResponseData();
		data.setCode("4002");
		data.setMsg("access denied.");
		return data;
	}

	public static ResponseData logout() {
		ResponseData data = new ResponseData();
		data.setCode("4001");
		data.setMsg("logouted.");
		return data;
	}

	public static ResponseData authfail() {
		ResponseData data = new ResponseData();
		data.setCode("4000");
		data.setMsg("login fail.");
		return data;
	}

	public static ResponseData ok() {
		ResponseData data = new ResponseData();
		data.setCode("200");
		data.setMsg("success");
		return data;
	}

	public static <T> ResponseData ok(T obj) {
		ResponseData data = new ResponseData();
		data.setCode("200");
		data.setData(JSONObject.toJSONString(obj));
		data.setMsg("success");
		return data;
	}

	public static <T> ResponseData ok(List<T> objs) {
		ResponseData data = new ResponseData();
		data.setCode("200");
		data.setData(JSONArray.toJSONString(objs));
		data.setMsg("success");
		return data;
	}

}
