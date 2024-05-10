package com.example.librarymanagement_final;




import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button viewBooksButton = findViewById(R.id.viewBooksButton);

        Button viewAuthorsButton = findViewById(R.id.viewAuthorsButton);

        Button viewLendingButton = findViewById(R.id.viewLendingsButton);
        Button viewMemberButton = findViewById(R.id.viewMemberButton);
        Button viewPublisherButton = findViewById(R.id.viewPublisherButton);

        viewBooksButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewBooksActivity.class);
            startActivity(intent);
        });


        viewAuthorsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewAuthorsActivity.class);
            startActivity(intent);
        });

        viewLendingButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewLendingActivity.class);
            startActivity(intent);
        });


        viewMemberButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewMembersActivity.class);
            startActivity(intent);
        });

        viewPublisherButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewPublishersActivity.class);
            startActivity(intent);
        });
    }
}
