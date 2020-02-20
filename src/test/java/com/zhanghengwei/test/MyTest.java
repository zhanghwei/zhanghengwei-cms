package com.zhanghengwei.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.elasticsearch.action.fieldstats.FieldStats.Ip;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.alibaba.druid.support.http.util.IPAddress;
import com.google.gson.Gson;
import com.zhanghengwei.cms.dao.CarDao;
import com.zhanghengwei.cms.pojo.Article;
import com.zhanghengwei.cms.pojo.Book;
import com.zhanghengwei.cms.pojo.Car;
import com.zhanghengwei.cms.service.ArticleService;
import com.zhanghengwei.cms.service.BookService;
import com.zhanghengwei.cms.service.CarService;
import com.zhangwei.common.utils.CarUtil;
import com.zhangwei.common.utils.FileUtil;
import com.zhangwei.common.utils.StreamUtil;
import com.zhangwei.common.utils.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class MyTest {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CarDao carDao;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private KafkaTemplate kafkaTemplate;
	
	
	@Test
	public void test() {
		List<Book> list=bookService.save();
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	@Test
	public void panduan() {
		ArrayList<Car> carList = new ArrayList<Car>();
		List<String> readTextFileOfList = FileUtil.readTextFileOfList("C:\\Users\\MACHENIKE\\Desktop\\git\\car.txt");
		for (String string : readTextFileOfList) {
			String[] split = string.split(",");
			Car car = new Car();
			car.setCreated(split[0]);
			car.setPlate(split[1]);
			car.setType(split[2]);
			car.setLat(Double.valueOf(split[3]));
			car.setLon(Double.valueOf(split[4]));
			carList.add(car);
		}
		int i=carDao.insert(carList);
		System.out.println(i+"%^&*(");
	
	}
	@Test
	public void addTest() {
		int i=104;
		File file = new File("D:\\1709DJsoup");
		String[] list = file.list();
		for (String string : list) {
			List<String> content = FileUtil.readTextFileOfList("D:\\1709DJsoup\\"+string);
			for (String con : content) {
				i+=1;
				Article article = new Article();
				article.setId(i);
				article.setContent(con);
				article.setTitle(string);
				article.setPicture("");
				article.setChannelId(1);
				article.setCategoryId(1);
				article.setCategoryName("");
				article.setChannelName("");
				article.setUserId(1);
				article.setNickname("ss");
				article.setStatus(0);
				article.setDeleted(1);
				article.setUpdated(new Date());
				article.setCommentcnt(1);
				article.setStatusIds("1");
				article.setTousuCnt(1);
				Random random = new Random();
				int nextInt = random.nextInt(10);
				article.setHits(nextInt);
				article.setHot(nextInt);
				article.setChannelId(nextInt);
				article.setCreated(new Date());
				Gson gson = new Gson();
				
				String json = gson.toJson(article);
				kafkaTemplate.sendDefault("article", json);
				System.out.println(article);
			}

		}	
		System.out.println("发送完毕");
	}
	
}
