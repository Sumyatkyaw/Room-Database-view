package com.example.roomdatabaseview.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabaseview.database.BookDatabase
import com.example.roomdatabaseview.model.Book
import com.example.roomdatabaseview.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel (application: Application) :AndroidViewModel(application){
    private val repository: BookRepository
    val allBook: LiveData<List<Book>>
    init {
        val bookDao = BookDatabase.getDatabase(application
        ).bookDao()
        repository = BookRepository(bookDao)
        allBook =repository.allBook
    }
    fun insert(book: Book) =viewModelScope.launch{
        repository.insert(book)
    }
}