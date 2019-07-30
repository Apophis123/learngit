package com.example.book.mapper;

import com.example.book.bean.Book;
import com.example.book.controller.BookController;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.core.SqlProvider;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    public List<Book> findAll();

    @Select("select * from book where id=#{id}")
    public Book getBookById(Integer id);

    @Delete("delete from book where id=#{id}")
    public int deleteBookById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into book(number,bookName,authorName,type,price) values(#{number},#{bookName},#{authorName},#{type},#{price})")
    public int insertBook(Book book);

    @Update("update book set number=#{number},bookName=#{bookName},authorName=#{authorName},type=#{type},price=#{price}  where id=#{id}")
    public int updateBook(Book book);

    @UpdateProvider(type = BookController.class,method = "update1")
    public int update1(Book book);
}
