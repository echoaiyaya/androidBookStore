package com.example.ebook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();


    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);
}
