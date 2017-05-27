package com.android.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 25-May-17.
 */

public class BItems {
    @SerializedName("copies")
    @Expose
    private Integer copies;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("book_name")
    @Expose
    private String bookName;
    @SerializedName("imagelink")
    @Expose
    private String imagelink;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("author")
    @Expose
    private String author;

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
