package com.zhanghengwei.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghengwei.cms.dao.CarDao;
import com.zhanghengwei.cms.pojo.Car;
import com.zhanghengwei.cms.service.CarService;
import com.zhangwei.common.utils.GeoUtils;
@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarDao carDao;

	@Override
	public List<Car> list(Car car) {
		// TODO Auto-generated method stub
		List<Car> list = carDao.list(car);
		
		if(car.getLon()!=null&&car.getLat()!=null) {
			for (Car car2 : list) {
				Double distance = GeoUtils.getDistance(car.getLat(),car.getLon(),car2.getLat(),car2.getLon());
				
				car2.setJuli(distance.intValue());
				
			}
		}
		return list;
	}
	
	
}
