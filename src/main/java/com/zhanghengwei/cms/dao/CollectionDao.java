package com.zhanghengwei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhanghengwei.cms.pojo.Coll;

public interface CollectionDao {

	int save(Coll coll);

	List<Coll> select(int user_id);

	int del(int id);

	List<Coll> list(@Param("coll")Coll coll);

}
