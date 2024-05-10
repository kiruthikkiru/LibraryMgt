package com.example.librarymanagement_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagement_final.DAO.PublisherDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Publisher;

public class AddPublisherActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;
    private EditText publisherNameEditText;
    private Button savePublisherButton;
    private int publisherId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);

        databaseHelper = new LibraryDatabaseHelper(this);
        publisherNameEditText = findViewById(R.id.publisherNameEditText);
        savePublisherButton = findViewById(R.id.savePublisherButton);

        if (getIntent().hasExtra("publisher_id")) {
            publisherId = getIntent().getIntExtra("publisher_id", -1);
            loadPublisherData(publisherId);
        }

        savePublisherButton.setOnClickListener(v -> savePublisher());
    }

    private void loadPublisherData(int publisherId) {
        PublisherDAO publisherDAO = new PublisherDAO(databaseHelper.getWritableDatabase());
        Publisher publisher = publisherDAO.getPublisherById(publisherId);
        if (publisher != null) {
            publisherNameEditText.setText(publisher.getName());
        } else {
            Toast.makeText(this, "Error loading publisher details!", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePublisher() {
        String name = publisherNameEditText.getText().toString().trim();
        PublisherDAO publisherDAO = new PublisherDAO(databaseHelper.getWritableDatabase());
        if (publisherId == -1) {
            publisherDAO.insertPublisher(name);
            Toast.makeText(this, "Publisher added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            publisherDAO.updatePublisher(publisherId, name);
            Toast.makeText(this, "Publisher updated successfully!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
