package com.example.roomdatabaseview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaseview.model.Book

@Dao
interface BookDao{

    @Query(value = "Select * From book_table ORDER BY book_name ASC")
    fun getAllBook(): LiveData<List<Book>>
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)
}