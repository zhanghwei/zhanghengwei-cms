package com.zhanghengwei.test;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhanghengwei.cms.pojo.Article;
import com.zhanghengwei.cms.service.RedisArticleService;
import com.zhangwei.common.utils.FileUtil;

import scala.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class KafkaTest {

	@Resource
	private RedisArticleService redisArticleService;
	
	@Test
	public void mytest() {
		File file = new File("D:\\1709DJsoup");
		String[] list = file.list();
		for (String string : list) {
			List<String> textFileOfList = FileUtil.readTextFileOfList("D:\\1709DJsoup\\"+string);
			for (String string2 : textFileOfList) {
				Article article = new Article();
				article.setContent(string2);
				article.setTitle(string);
				Random random = new Random();
				int nextInt = random.nextInt(10);
				article.setChannelId(nextInt);
				article.setHits(nextInt);
				article.setHot(nextInt);
				article.setCreated(new Date());
				article.setPicture("");
				article.setCategoryId(1);
				article.setCategoryName("hhh");
				article.setChannelName("kkk");
				article.setUserId(1);
				article.setNickname("lll");
				article.setStatus(0);
				article.setDeleted(0);
				article.setUpdated(new Date());
				article.setCommentcnt(22);
				article.setStatusIds("0");
				article.setTousuCnt(11);
				redisArticleService.save(article);
				
			}
		}
		System.out.println("发送成功");
	}
	
}
