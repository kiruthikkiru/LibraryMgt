package com.example.librarymanagement_final;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagement_final.DAO.AuthorDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Author;

public class AddAuthorActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;
    private EditText authorNameEditText;
    private Button saveButton;
    private int authorId = -1;  // Default to -1 to indicate adding a new author

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);

        databaseHelper = new LibraryDatabaseHelper(this);
        authorNameEditText = findViewById(R.id.authorNameEditText);
        saveButton = findViewById(R.id.saveAuthorButton);

        if (getIntent().hasExtra("author_id")) {
            authorId = getIntent().getIntExtra("author_id", -1);
            loadAuthorData(authorId);
        }

        saveButton.setOnClickListener(v -> saveAuthor());
    }

    private void loadAuthorData(int authorId) {
        AuthorDAO authorDAO = new AuthorDAO(databaseHelper.getWritableDatabase());
        Author author = authorDAO.getAuthorById(authorId);
        if (author != null) {
            authorNameEditText.setText(author.getName());
        } else {
            Toast.makeText(this, "Error loading author details!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveAuthor() {
        String name = authorNameEditText.getText().toString().trim();

        AuthorDAO authorDAO = new AuthorDAO(databaseHelper.getWritableDatabase());
        if (authorId == -1) {
            authorDAO.insertAuthor(name);
            Toast.makeText(this, "Author added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            authorDAO.updateAuthor(authorId, name);
            Toast.makeText(this, "Author updated successfully!", Toast.LENGTH_SHORT).show();
        }
        finish();  // Close the activity
    }
}

