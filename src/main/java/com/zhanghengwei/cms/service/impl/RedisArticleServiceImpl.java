package com.zhanghengwei.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zhanghengwei.cms.pojo.Article;
import com.zhanghengwei.cms.service.RedisArticleService;
@Service
public class RedisArticleServiceImpl implements RedisArticleService{

	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Override
	public void save(Article article) {
		// TODO Auto-generated method stub
		ValueOperations<String, Article> string = redisTemplate.opsForValue();
		String title = article.getTitle();
		string.set(title, article);
		kafkaTemplate.sendDefault("article_new",title);
		System.out.println(article.getTitle()+"导入完毕，实现类");
		
	}

}
