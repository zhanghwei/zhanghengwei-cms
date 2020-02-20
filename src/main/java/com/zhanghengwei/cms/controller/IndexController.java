package com.zhanghengwei.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageInfo;
import com.zhanghengwei.cms.pojo.Article;
import com.zhanghengwei.cms.pojo.Category;
import com.zhanghengwei.cms.pojo.Channel;
import com.zhanghengwei.cms.pojo.Slide;
import com.zhanghengwei.cms.pojo.User;
import com.zhanghengwei.cms.service.ArticleService;
import com.zhanghengwei.cms.service.SlideService;
import com.zhanghengwei.cms.service.UserService;
import com.zhanghengwei.cms.util.ESUtils;


@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SlideService slideService;
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	@Resource
	private RedisTemplate redisTemplate;
	
	@RequestMapping(value="/")
	public String index(Model model) {
		return index(1, model);
	}
	
	@RequestMapping(value="/hot/{pageNum}.html")
	public String index(@PathVariable Integer pageNum, Model model) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 轮播图 */
		List<Slide> slideList = slideService.getAll();
		model.addAttribute("slideList", slideList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 热点文章 **/
		if(pageNum==null) {
			pageNum=1;
		}
		PageInfo<Article> pageInfo =  articleService.getHotList(pageNum);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	

	//高亮显示
	@RequestMapping(value="/articlSearch")
	public String articlSearch(Model model,String key) {
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		List<Article> newList = articleService.getNewList(6);
		ListOperations opsForList = redisTemplate.opsForList();
		//Redis缓存功能
		opsForList.leftPush("newList", newList);
		//有效期5分钟。
		opsForList.leftPop("newList", 5, TimeUnit.MINUTES);
		
		model.addAttribute("newArticleList",newList);
		long c1 = System.currentTimeMillis();
		
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class,null, 0, 1000, "id", new String[] {"title"}, key);		
		long c2 = System.currentTimeMillis();
		long c3=c2-c1;

		List<Article> content = selectObjects.getContent();
		PageInfo<Article> pageInfo = new PageInfo<>(content);
		model.addAttribute("c3", c3);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("/{channelId}/{cateId}/{pageNo}.html")
	public String channel(@PathVariable Integer channelId, Model model,
			@PathVariable Integer cateId,@PathVariable Integer pageNo) {
		/** 频道 */
		List<Channel> channelList = articleService.getChannelList();
		model.addAttribute("channelList", channelList);
		/** 最新文章 **/
		List<Article> newArticleList = articleService.getNewList(6);
		model.addAttribute("newArticleList", newArticleList);
		/** 分类 */
		List<Category> cateList = articleService.getCateListByChannelId(channelId);
		model.addAttribute("cateList", cateList);
		PageInfo<Article> pageInfo =  articleService.getListByChannelIdAndCateId(channelId,cateId,pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "index";
	}
	
	@RequestMapping("article/{id}.html")
	public String articleDetail(@PathVariable Integer id,Model model) {
		/** 查询文章 **/
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		if(article.getStatus()==3){
			return "article/error";
		}
		/** 查询用户 **/
		User user = userService.getById(article.getUserId());
		model.addAttribute("user", user);
		/** 查询相关文章 **/
		List<Article> articleList = articleService.getListByChannelId(article.getChannelId(),id,10);
		model.addAttribute("articleList", articleList);
		return "article/detail";
	}
	
}
