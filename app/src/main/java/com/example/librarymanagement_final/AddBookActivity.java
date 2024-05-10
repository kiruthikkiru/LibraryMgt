package com.example.librarymanagement_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.librarymanagement_final.DAO.BookDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Book;

public class AddBookActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;
    private EditText titleEditText, authorIdEditText, publisherIdEditText, yearEditText;
    private Button saveButton;
    private int bookId = -1;  // Default to -1 to indicate adding a new book

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        databaseHelper = new LibraryDatabaseHelper(this);
        titleEditText = findViewById(R.id.titleEditText);
        authorIdEditText = findViewById(R.id.authorIdEditText);
        publisherIdEditText = findViewById(R.id.publisherIdEditText);
        yearEditText = findViewById(R.id.yearEditText);
        saveButton = findViewById(R.id.saveButton);

        if (getIntent().hasExtra("book_id")) {
            bookId = getIntent().getIntExtra("book_id", -1);
            loadBookData(bookId);
        }

        saveButton.setOnClickListener(v -> saveBook());
    }

    private void loadBookData(int bookId) {
        BookDAO bookDAO = new BookDAO(databaseHelper.getWritableDatabase());
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            titleEditText.setText(book.getTitle());
            authorIdEditText.setText(String.valueOf(book.getAuthorId()));
            publisherIdEditText.setText(String.valueOf(book.getPublisherId()));
            yearEditText.setText(String.valueOf(book.getYear()));
        } else {
            Toast.makeText(this, "Error loading book details!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveBook() {
        String title = titleEditText.getText().toString().trim();
        int authorId = Integer.parseInt(authorIdEditText.getText().toString().trim());
        int publisherId = Integer.parseInt(publisherIdEditText.getText().toString().trim());
        int year = Integer.parseInt(yearEditText.getText().toString().trim());

        BookDAO bookDAO = new BookDAO(databaseHelper.getWritableDatabase());
        if (bookId == -1) {
            bookDAO.insertBook(title, authorId, publisherId, year);
            Toast.makeText(this, "Book added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            bookDAO.updateBook(bookId, title, authorId, publisherId, year);
            Toast.makeText(this, "Book updated successfully!", Toast.LENGTH_SHORT).show();
        }
        finish();  // Close the activity
    }
}
