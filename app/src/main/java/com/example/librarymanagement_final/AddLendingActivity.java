package com.example.librarymanagement_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagement_final.DAO.LendingDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Lending;

public class AddLendingActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;
    private EditText bookIdEditText, memberIdEditText, borrowedDateEditText, returnDateEditText;
    private Button saveButton;
    private int lendingId = -1;  // Default to -1 to indicate adding a new lending record

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lending);

        databaseHelper = new LibraryDatabaseHelper(this);
        bookIdEditText = findViewById(R.id.bookIdEditText);
        memberIdEditText = findViewById(R.id.memberIdEditText);
        borrowedDateEditText = findViewById(R.id.borrowedDateEditText);
        returnDateEditText = findViewById(R.id.returnDateEditText);
        saveButton = findViewById(R.id.saveLendingButton);

        // Check if there's an intent to edit an existing record
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("lending_id")) {
            lendingId = intent.getIntExtra("lending_id", -1);
            loadLendingData(lendingId);
        }

        saveButton.setOnClickListener(v -> saveLending());
    }

    private void loadLendingData(int lendingId) {
        LendingDAO lendingDAO = new LendingDAO(databaseHelper.getWritableDatabase());
        Lending lending = lendingDAO.getLendingById(lendingId);
        if (lending != null) {
            bookIdEditText.setText(String.valueOf(lending.getBookId()));
            memberIdEditText.setText(String.valueOf(lending.getMemberId()));
            borrowedDateEditText.setText(lending.getBorrowedDate());
            returnDateEditText.setText(lending.getReturnDate());
        }
    }

    private void saveLending() {
        int bookId = Integer.parseInt(bookIdEditText.getText().toString().trim());
        int memberId = Integer.parseInt(memberIdEditText.getText().toString().trim());
        String borrowedDate = borrowedDateEditText.getText().toString().trim();
        String returnDate = returnDateEditText.getText().toString().trim();

        LendingDAO lendingDAO = new LendingDAO(databaseHelper.getWritableDatabase());
        if (lendingId == -1) { // Add new lending record
            lendingDAO.insertLending(bookId, memberId, borrowedDate, returnDate);
            Toast.makeText(this, "Lending record added successfully!", Toast.LENGTH_SHORT).show();
        } else { // Update existing lending record
            lendingDAO.updateLending(lendingId, bookId, memberId, borrowedDate, returnDate);
            Toast.makeText(this, "Lending record updated successfully!", Toast.LENGTH_SHORT).show();
        }
        finish();  // Close the activity
    }
}
