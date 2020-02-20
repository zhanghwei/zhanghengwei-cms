package com.zhanghengwei.cms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghengwei.cms.dao.TousuDao;
import com.zhanghengwei.cms.pojo.Tousu;
import com.zhangwei.common.utils.DateUtil;
@Service
public class TousuService {

	@Autowired
	private TousuDao tousuDao;
	
	@Autowired
	private ArticleService articleService;

	public boolean add(Tousu tousu) {
		// TODO Auto-generated method stub
		String created = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(created);
		int i=tousuDao.insert(tousu);
		articleService.addTousu(tousu.getArticleId());
		return i>0;
	}

	
	    
/*	public boolean add(Tousu tousu) {
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(createdStr);
		int i = tousuDao.insert(tousu);
		System.out.println(i+"&&&&&&&&");
		articleService.addTousu(tousu.getArticleId());
		return i>0;
	}*/
	/*public boolean add(Tousu tousu) {
		// TODO Auto-generated method stub
		String createdStr = DateUtil.dateTimeFormat.format(new Date());
		tousu.setCreated(createdStr);
		int i=tousuDao.insert(tousu);
		articleService.addTousu(tousu.getArticleId());
		return i>0;
	}*/
	
	
}
