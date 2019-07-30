package com.example.book.bean;

public class Book {

    private Integer id;
    private Integer number;
    private String bookName;
    private String authorName;
    private String type;
    private Integer price;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", number=" + number +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
