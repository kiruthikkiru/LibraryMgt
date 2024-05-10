package com.example.librarymanagement_final;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.Adapter.BookAdapter;
import com.example.librarymanagement_final.DAO.BookDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Book;

import java.util.List;

public class ViewBooksActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        databaseHelper = new LibraryDatabaseHelper(this);
        RecyclerView booksRecyclerView = findViewById(R.id.booksRecyclerView);
        Button addBookButton = findViewById(R.id.addBookButton);

        addBookButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewBooksActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        // Set up RecyclerView
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load books data
        BookDAO bookDAO = new BookDAO(databaseHelper.getWritableDatabase());
        List<Book> books = bookDAO.getBooks();

        BookAdapter adapter = new BookAdapter(this, books);
        booksRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the data in case a new book is added
        BookDAO bookDAO = new BookDAO(databaseHelper.getWritableDatabase());
        List<Book> books = bookDAO.getBooks();

        BookAdapter adapter = new BookAdapter(this, books);
        RecyclerView booksRecyclerView = findViewById(R.id.booksRecyclerView);
        booksRecyclerView.setAdapter(adapter);
    }
}

