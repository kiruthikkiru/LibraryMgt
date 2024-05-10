package com.example.librarymanagement_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.Adapter.PublisherAdapter;
import com.example.librarymanagement_final.DAO.PublisherDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Publisher;

import java.util.List;

public class ViewPublishersActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_publisher);
        Button addMemberButton = findViewById(R.id.addpublisherButton);

        addMemberButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewPublishersActivity.this, AddPublisherActivity.class);
            startActivity(intent);
        });
        databaseHelper = new LibraryDatabaseHelper(this);
        RecyclerView publishersRecyclerView = findViewById(R.id.publishersRecyclerView);
        publishersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshPublishersList();
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshPublishersList();
    }
    private void refreshPublishersList() {
        PublisherDAO publisherDAO = new PublisherDAO(databaseHelper.getWritableDatabase());
        List<Publisher> publishers = publisherDAO.getAllPublishers();

        PublisherAdapter adapter = new PublisherAdapter(this, publishers);
        RecyclerView publishersRecyclerView = findViewById(R.id.publishersRecyclerView);
        publishersRecyclerView.setAdapter(adapter);
    }
}
