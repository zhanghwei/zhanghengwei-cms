package com.zhanghengwei.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhanghengwei.cms.common.CmsConstant;
import com.zhanghengwei.cms.pojo.Coll;
import com.zhanghengwei.cms.pojo.User;
import com.zhanghengwei.cms.service.CollectionService;
import com.zhangwei.common.utils.StringUtil;
@Controller
@RequestMapping("/shou/")
public class CollectionController {
	@Resource
	private CollectionService collectionService;
	
	//收藏功能
	@RequestMapping("colle")
	public String coll(Coll coll,Model model,HttpSession session,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		//设置用户Id
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		coll.setUser_id(userInfo.getId());
		//查询文章
		PageInfo<Coll> pageInfo = collectionService.getPage(coll,pageNum,pageSize);
		List<Coll> list = pageInfo.getList();
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		
		return "user/colle";
	}
	//删除功能
	@RequestMapping("del")
	public Object del(Integer id,Model model) {
		System.out.println(id);
		//删除文章
		int i = collectionService.del(id);
		System.out.println(i>0);
		return "redirect:/user/center";
	}
	//添加功能
	@ResponseBody
	@RequestMapping("add")
	public Object add(Coll coll,Model model) {
		System.out.println(coll.getUrl());
		boolean bl=false;
		String url = coll.getUrl();
		boolean httpUrl = StringUtil.isHttpUrl(url);
		System.out.println(httpUrl);
		if(httpUrl) {
			int i = collectionService.save(coll);
			if(i>0) {
				return true;
			}	
		}
		return bl;
	}
	//添加功能
		@RequestMapping("toAdd")
		public String toAdd() {
			
			
			return "user/add";
		}
}
