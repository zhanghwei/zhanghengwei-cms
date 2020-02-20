package com.zhanghengwei.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhanghengwei.cms.common.CmsConstant;
import com.zhanghengwei.cms.common.JsonResult;
import com.zhanghengwei.cms.pojo.Tousu;
import com.zhanghengwei.cms.pojo.User;
import com.zhanghengwei.cms.service.TousuService;

@Controller
@RequestMapping("/tousu/")
public class TousuController {

	@Autowired
	private TousuService tousuService;

	
	@RequestMapping("add")
	@ResponseBody
	public Object add(Tousu tousu,HttpSession session) {
		User userInfo = (User) session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode,"未登录");
		}
		tousu.setUserId(userInfo.getId());
		boolean result=tousuService.add(tousu);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(1000,"未知错误");
	}
}
