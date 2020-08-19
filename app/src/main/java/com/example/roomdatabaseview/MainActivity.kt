package com.example.roomdatabaseview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseview.adapter.BookAdapter
import com.example.roomdatabaseview.model.Book
import com.example.roomdatabaseview.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel

    private val addBookActivityRequestCode =1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bookAdapter = BookAdapter()

        recyclerView.apply {
            layoutManager=LinearLayoutManager(context)
             adapter =bookAdapter
        }

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        bookViewModel.allBook.observe(this, Observer {
            books -> books.let {
            Log.d("books", it.toString())
            bookAdapter.addBookList(books)
        }
        }
        )
        btnAdd.setOnClickListener{
            val intent =Intent(this,
            AddBookActivity::class.java)
            startActivityForResult(intent,addBookActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== addBookActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(AddBookActivity.EXTRA_REPLY)?.let{
                val book =Book(it)
                bookViewModel.insert(book)
            }
        }
    }
}