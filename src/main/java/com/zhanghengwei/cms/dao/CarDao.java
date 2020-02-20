package com.zhanghengwei.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhanghengwei.cms.pojo.Car;

public interface CarDao {

	int insert(@Param("carList")ArrayList<Car> carList);

	List<Car> list(@Param("car")Car car);

}
