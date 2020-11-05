package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ajax.model.vo.Sample;
import com.kh.ajax.model.vo.Sample2;
import com.kh.ajax.model.vo.User;

@Controller
public class TestController {
	
	@Autowired
	private Sample sam;
	
	@Autowired
	private Sample2 sam2;
	
	@RequestMapping("testtest.do")
	public void test() {
		sam2.setName("구운마늘");
		sam2.setAge(40);
		
		System.out.println("xml 방식 : " + sam);
		System.out.println("어노테이션 방식 : " + sam2);
	}
	
	@RequestMapping("test1")
	public void test1Method(@RequestParam("name") String ireum, @RequestParam("age") int nai, 
								HttpServletResponse response) {
		
		// ServletOutputStream 이용
		try {
			PrintWriter out = response.getWriter();
			
			if(ireum.equals("김연우") && nai == 10) {
				// ok
				out.append("ok");
			} else {
				// fail
				out.append("fail");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("test2")
	public void test2Method(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test json object");
		job.put("writer", "김연우");
		job.put("content", "JSON객체 리턴하기");
		
		try {
			PrintWriter out = response.getWriter();
			out.println(job);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("test3")
	@ResponseBody
	public String test3Method(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test json object");
		job.put("writer", "김연우");
		job.put("content", "JSON객체 리턴하기");
		
		return job.toJSONString();
		
	}

	@RequestMapping("test4")
	public void test4Method(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		
		ArrayList<User> list = new ArrayList<User>();
		list.add(new User("u111", "p111", "김연우", 25, "m111@kh.or.kr", "01011112222"));
		list.add(new User("u222", "p222", "김루디", 25, "m222@kh.or.kr", "01033332222"));
		list.add(new User("u333", "p333", "김마늘", 25, "m333@kh.or.kr", "01055552222"));
		list.add(new User("u444", "p444", "김빡빡", 25, "m444@kh.or.kr", "01077772222"));
		list.add(new User("u555", "p555", "김연틱", 25, "m555@kh.or.kr", "01099992222"));
		
		JSONArray jArr = new JSONArray();
		for(User user : list) {
			JSONObject jUser = new JSONObject();
			jUser.put("userId", user.getUserId());
			jUser.put("userPwd", user.getUserPwd());
			jUser.put("userName", user.getUserName());
			jUser.put("age", user.getAge());
			jUser.put("email", user.getEmail());
			jUser.put("phone", user.getPhone());
			
			jArr.add(jUser);
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.println(jArr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}