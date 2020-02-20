package com.zhanghengwei.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.zhanghengwei.cms.pojo.Article;
import com.zhanghengwei.cms.service.ArticleService;


@Component
public class kafkaConsumerListener implements MessageListener<String,String>{

	@Resource
	private ArticleService articleService;
	

	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String key = data.key();
		
		if(data.key().equals("article_new")) {
			ValueOperations<String, Article> opsForValue = redisTemplate.opsForValue();
			String value = data.value();
			Article article = opsForValue.get(value);
			if(article!=null) {
				System.out.println(article+",消费端");
				boolean bl =articleService.save(article);
				System.out.println(article.getTitle()+"导入成功，消费端");
				if(bl) {
					redisTemplate.delete(value);
				}
			}
			
		}
	}

}
