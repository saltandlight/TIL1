package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frame.Biz;
import com.vo.Product;
import com.vo.User;

@Controller
public class MainController {
	@Resource(name="ubiz")
	Biz<String, User> biz;
	
	@Resource(name="pbiz")
	Biz<Integer, Product> pbiz;
	
	@RequestMapping("/main.mc")
	public ModelAndView main() {
		//데이터와 화면(Model And View)
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/aboutus.mc")
	public ModelAndView aboutus() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("navi", Navi.aboutus);   
		mv.addObject("center","aboutus");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/pdata.mc")
	@ResponseBody
	public void pdata(HttpServletResponse rep) {
		ArrayList<Product> plist = null;
		try {
			plist = pbiz.select();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONArray ja = new JSONArray();
		for(Product p:plist) {
			JSONObject jo = new JSONObject();
			jo.put("name",p.getName());
			jo.put("y", p.getPrice());
			ja.add(jo);
		}
		PrintWriter out = null;
		try {
			rep.setCharacterEncoding("UTF-8");
			rep.setContentType("text/json;charset=UTF-8");
			out = rep.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(ja.toJSONString());
		
	}
	
	
	
	@RequestMapping("/login.mc")
	public ModelAndView login() {		
		ModelAndView mv = new ModelAndView();
		mv.addObject("navi", Navi.login);
		mv.addObject("center","login/login");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/loginimpl.mc")
	public ModelAndView loginimpl(ModelAndView mv,
			HttpServletRequest request,
			HttpSession session) {
		String id=request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		if(id.equals("qqq") && pwd.equals("111")) {
			User user = new User(id,pwd,"james");
			session.setAttribute("loginuser", user);
			mv.addObject("center","login/loginok");
		}else {
			mv.addObject("center","login/loginfail");
		}
		mv.setViewName("main");
		return mv;
	}	
	@RequestMapping("/logout.mc")
	public ModelAndView logout(ModelAndView mv,
			HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		mv.setViewName("main");
		return mv;
	}
	@RequestMapping("/register.mc")
	public ModelAndView register() {
		//데이터와 화면(Model And View)
//		request.setAttribute("navi", Navi.login);
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("navi", Navi.register);
		mv.addObject("center","register");
		mv.setViewName("main");
		return mv;
	}
	

}
