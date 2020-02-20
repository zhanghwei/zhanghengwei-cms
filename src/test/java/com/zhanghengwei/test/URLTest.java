package com.zhanghengwei.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhanghengwei.cms.pojo.Coll;
import com.zhanghengwei.cms.service.CollectionService;
import com.zhangwei.common.utils.StringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class URLTest {

	@Resource
	private CollectionService collectionService;
	
	//收藏功能的添加测试
	@Test
	public void urlAdd() {
		String text="雷军股市往事：A股火爆身家一天暴涨150亿，港股破发说大势不好";
		String url="http://127.0.0.1/article?id=2";
		int user_id=185;
		String created="2019-11-20 11:06:09";
		if(StringUtil.isHttpUrl(url)) {
			Coll coll = new Coll(0,text,url,user_id,created);
			
			int i = collectionService.save(coll);
			System.out.println("保存成功"+1);
			
		}else {
			System.out.println("url是非法的");
		}
		
	}
	//收藏功能的列表查询功能测试（按时间倒排序、user_id为条件，并有分页功能）
	@Test
	public void urlSelect() {
		int user_id=185;
		List<Coll> list=collectionService.select(user_id);
		for (Coll coll : list) {
			System.out.println(coll);
		}
	}
	//收藏列表的删除功能(根据id删除)
	@Test
	public  void del() {
		int id=7;
		int i=collectionService.del(id);
		System.out.println(i>0);
	}
	//测试传入非法url
	@Test
	public void  errorUrl() {
		String text="雷军股市往事：A股火爆身家一天暴涨150亿，港股破发说大势不好";
		String url="ht27.0.0.1/article?id=2";
		int user_id=185;
		String created="2019-11-20 11:06:09";
		if(StringUtil.isHttpUrl(url)) {
			Coll coll = new Coll(0,text,url,user_id,created);
			
			int i = collectionService.save(coll);
			System.out.println("保存成功"+1);
			
		}else {
			System.out.println("url是非法的");
		}
		
	}
	
}
