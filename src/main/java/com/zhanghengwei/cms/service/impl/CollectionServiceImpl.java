package com.zhanghengwei.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghengwei.cms.dao.CollectionDao;
import com.zhanghengwei.cms.pojo.Coll;
import com.zhanghengwei.cms.service.CollectionService;
@Service
public class CollectionServiceImpl implements CollectionService{
	@Resource
	private CollectionDao collectionDao;
	
	@Override
	public int save(Coll coll) {
		// TODO Auto-generated method stub
		return collectionDao.save(coll);
	}

	@Override
	public List<Coll> select(int user_id) {
		// TODO Auto-generated method stub
		return collectionDao.select(user_id);
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return collectionDao.del(id);
	}

	//收藏
	@Override
	public PageInfo<Coll> getPage(Coll coll, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Coll> articleList = collectionDao.list(coll);
		
		
		return new PageInfo<>(articleList);
	}
}
