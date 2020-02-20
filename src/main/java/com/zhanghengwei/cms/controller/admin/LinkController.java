package com.zhanghengwei.cms.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhanghengwei.cms.common.JsonResult;
import com.zhanghengwei.cms.pojo.Link;
import com.zhanghengwei.cms.service.LinkService;

@Controller
@RequestMapping("/admin/link/")
public class LinkController {
	@Autowired
	private LinkService linkService;

	/*
	 * 友情链接管理   
	 */
	@RequestMapping("list")
	public String list(Link link,Model model,
			@RequestParam(value="pageNum",defaultValue="1") int pageNum,@RequestParam(value="pageSize",defaultValue="3") int pageSize) {
		PageInfo<Link> pageInfo = linkService.getPageInfo(link,pageNum,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "link/list";
	}
	
	/*
	 * 编辑   
	 */
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public String update(Integer id,Model model) {
		if(id!=null) {
			Link link = linkService.getById(id);
			model.addAttribute("link", link);
		}
		return "link/edit";
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("save")
	@ResponseBody
	public JsonResult save(Link link,Model model) {
		linkService.save(link);
		return JsonResult.sucess();
	}
	
	/*
	 * 批量删除
	 */
	@RequestMapping("delByIds")
	@ResponseBody
	public JsonResult delByIds(String ids,Model model) {
		linkService.delByIds(ids);
		return JsonResult.sucess();
	}
}
