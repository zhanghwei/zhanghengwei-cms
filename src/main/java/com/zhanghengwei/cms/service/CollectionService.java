package com.zhanghengwei.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhanghengwei.cms.pojo.Coll;

public interface CollectionService {

	int save(Coll coll);

	List<Coll> select(int user_id);

	int del(int id);

	PageInfo<Coll> getPage(Coll coll, int pageNum, int pageSize);

}
