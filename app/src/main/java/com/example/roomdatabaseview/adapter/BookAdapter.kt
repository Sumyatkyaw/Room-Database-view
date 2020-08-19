package com.example.roomdatabaseview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseview.R
import com.example.roomdatabaseview.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter:RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var books = emptyList<Book>()
    inner class BookViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            fun bind(book: Book) {
                itemView.txtBookName.text = book.bookName

        }
    }
    fun addBookList(bookList: List<Book>){
        this.books=bookList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int  {
        Log.d("size", books.size.toString())
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }
}