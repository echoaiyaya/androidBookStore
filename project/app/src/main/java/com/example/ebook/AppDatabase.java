package com.example.ebook;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ebook.Book;
import com.example.ebook.BookDao;

@Database(entities = {Book.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}
