package com.example.book.controller;

import com.example.book.bean.Book;
import com.example.book.mapper.BookMapper;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.jdbc.SQL;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController extends SQL {

    @Autowired
    BookMapper bookMapper;

    //显示所有信息
    @ResponseBody
    @GetMapping("/lists")
    public List<Book> findAll() {
        List<Book> list = bookMapper.findAll();
        return list;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Integer id) {
        Book book = bookMapper.getBookById(id);
        return book;
    }

    @ResponseBody
    @PostMapping(value = "/book")
    public Book insertBook(Book book) {
        bookMapper.insertBook(book);
        return book;
    }


    @ResponseBody
    @PutMapping("/{id}")
    public Book updateBook(Book book, @PathVariable("id") Integer id) {
        bookMapper.updateBook(book);
        return book;
    }

    @ResponseBody
//    @RequestMapping("/delete/{id}")
    @DeleteMapping("/del/{id}")
    public List<Book> deletaBook(@PathVariable("id") Integer id) {
        bookMapper.deleteBookById(id);
        return bookMapper.findAll();
    }

    /**
     * 本地浏览器默认是使用get请求的，所以页面会报错
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public List<Book> deleteBook(@PathVariable("id") Integer id) {
        bookMapper.deleteBookById(id);
        return bookMapper.findAll();
    }


    //实现部分更新 动态更新（@UpdateProvider(type = BookController.class,method = "update1")）
    public String update1(Book book) {
        return new SQL() {
            {
                UPDATE("book");
                if (book.getNumber() != null) {
                    SET("number=#{number}");
                }
                if (book.getBookName() != null) {
                    SET("bookName=#{bookName}");
                }
                if (book.getAuthorName() != null) {
                    SET("authorName=#{authorName}");
                }
                if (book.getType() != null) {
                    SET("type=#{type}");
                }
                if (book.getPrice() != null) {
                    SET("price=#{price}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
//        return bookMapper.getBookById(id);
    }

    //调用动态更新的方法（bookMapper.update1(book);）
    @ResponseBody
    @PatchMapping("/up/{id}")
    public Book showUpdate(Book book, @PathVariable("id") Integer id) {
        bookMapper.update1(book);
        return bookMapper.getBookById(id);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

    /**
     * 页面展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String findAll(Model model) {
        List<Book> list = bookMapper.findAll();
//        JSONPObject jsonpObject = new JSONPObject("data", list);
        model.addAttribute("books", list);
        return "booklist";
    }


    @RequestMapping("/edit")
    public String editDept(ModelMap map, @RequestParam(defaultValue = "0") Integer id) {
        if (id > 0) {
            map.addAttribute("isAdd", false);
            map.addAttribute("book", bookMapper.getBookById(id));
        } else {
            map.addAttribute("isAdd", true);
            map.addAttribute("book", new Book());
        }
        return "edit";
    }


    //    @ResponseBody
    @RequestMapping("/save")
    public String save(@ModelAttribute Book book, Model model) {
        if (book == null) {
            return "fail";
        }
        if (book.getId() != null && book.getId() > 0) {
//            bookMapper.updateBook(book);
            bookMapper.update1(book);
        } else {
            bookMapper.insertBook(book);
        }

        List<Book> list = bookMapper.findAll();
        model.addAttribute("books", list);
        return "redirect:/books/list";
    }


    @RequestMapping("/delete/{id}")
//    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public String deleteDept(@PathVariable("id") Integer id, Model model) {
        bookMapper.deleteBookById(id);
        return "redirect:/books/list";
    }

    @ResponseBody
    @RequestMapping("/find/")
    public Book findBook(@RequestParam("id") Integer id, Model model) {
        Book book = bookMapper.getBookById(id);
        model.addAttribute("book", book);
        return book;
    }
}
