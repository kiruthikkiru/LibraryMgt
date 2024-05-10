package com.example.librarymanagement_final;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.Adapter.AuthorAdapter;
import com.example.librarymanagement_final.DAO.AuthorDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Author;

import java.util.List;

public class ViewAuthorsActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_author);

        databaseHelper = new LibraryDatabaseHelper(this);
        RecyclerView authorsRecyclerView = findViewById(R.id.authorsRecyclerView);
        Button addAuthorButton = findViewById(R.id.addAuthorButton);

        addAuthorButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewAuthorsActivity.this, AddAuthorActivity.class);
            startActivity(intent);
        });

        authorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshAuthorsList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAuthorsList();
    }

    private void refreshAuthorsList() {
        AuthorDAO authorDAO = new AuthorDAO(databaseHelper.getWritableDatabase());
        List<Author> authors = authorDAO.getAllAuthors();

        AuthorAdapter adapter = new AuthorAdapter(this, authors);
        RecyclerView authorsRecyclerView = findViewById(R.id.authorsRecyclerView);
        authorsRecyclerView.setAdapter(adapter);
    }
}

