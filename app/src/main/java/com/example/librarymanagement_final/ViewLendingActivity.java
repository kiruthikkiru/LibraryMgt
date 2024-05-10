package com.example.librarymanagement_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.Adapter.LendingAdapter;
import com.example.librarymanagement_final.DAO.LendingDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Lending;

import java.util.List;

public class ViewLendingActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lending);

        databaseHelper = new LibraryDatabaseHelper(this);
        RecyclerView lendingRecyclerView = findViewById(R.id.lendingRecyclerView);
        Button addLendingButton = findViewById(R.id.addLendingButton);

        addLendingButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewLendingActivity.this, AddLendingActivity.class);
            startActivity(intent);
        });

        lendingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLendingList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLendingList();
    }

    private void refreshLendingList() {
        LendingDAO lendingDAO = new LendingDAO(databaseHelper.getWritableDatabase());
        List<Lending> lendings = lendingDAO.getLending();
        RecyclerView lendingRecyclerView = findViewById(R.id.lendingRecyclerView);
        LendingAdapter adapter = new LendingAdapter(this, lendings);
        lendingRecyclerView.setAdapter(adapter);
    }
}
