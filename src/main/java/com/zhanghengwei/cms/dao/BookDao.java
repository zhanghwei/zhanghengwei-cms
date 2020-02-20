package com.zhanghengwei.cms.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.zhanghengwei.cms.pojo.Book;
import com.zhanghengwei.cms.pojo.ErrorMessage;

public interface BookDao {

	int insertBook(@Param("bookList")ArrayList<Book> bookList);

	int insertError(@Param("errorList")ArrayList<ErrorMessage> errorList);

}
