package com.example.librarymanagement_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.Adapter.MemberAdapter;
import com.example.librarymanagement_final.DAO.MemberDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Member;

import java.util.List;

public class ViewMembersActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);
        Button addMemberButton = findViewById(R.id.addMemberButton);

        addMemberButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewMembersActivity.this, AddMemberActivity.class);
            startActivity(intent);
        });
        databaseHelper = new LibraryDatabaseHelper(this);
        RecyclerView membersRecyclerView = findViewById(R.id.membersRecyclerView);
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshMembersList();
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshMembersList();
    }
    private void refreshMembersList() {
        MemberDAO memberDAO = new MemberDAO(databaseHelper.getWritableDatabase());
        List<Member> members = memberDAO.getAllMembers();

        MemberAdapter adapter = new MemberAdapter(this, members);
        RecyclerView membersRecyclerView = findViewById(R.id.membersRecyclerView);
        membersRecyclerView.setAdapter(adapter);
    }
}
