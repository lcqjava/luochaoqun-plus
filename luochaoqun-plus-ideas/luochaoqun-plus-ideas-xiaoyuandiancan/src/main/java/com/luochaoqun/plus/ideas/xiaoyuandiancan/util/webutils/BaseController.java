package com.luochaoqun.plus.ideas.xiaoyuandiancan.util.webutils;

import javax.servlet.http.HttpServletRequest;

import com.luochaoqun.plus.ideas.xiaoyuandiancan.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
* @description:
* @author:luo.cq 150-1921-8492
* @date:2017年12月18日
**/

@ControllerAdvice
public abstract class BaseController {
	
	protected User getCurrUser(HttpServletRequest request) {
		String token = getToken(request);
		return new User();
		
	}
	
	protected String getToken(HttpServletRequest request) {
		String token = request.getHeader("token");

		return token;
	}
	public static void main(String[] args) {
		System.out.println(System.getProperties());
	}
	
}
