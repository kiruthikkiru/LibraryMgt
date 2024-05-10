package com.example.librarymanagement_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagement_final.DAO.MemberDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Member;

public class AddMemberActivity extends AppCompatActivity {
    private LibraryDatabaseHelper databaseHelper;
    private EditText memberNameEditText, membershipDateEditText;
    private Button saveMemberButton;
    private int memberId = -1; // Default to -1 to indicate adding a new member

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        databaseHelper = new LibraryDatabaseHelper(this);
        memberNameEditText = findViewById(R.id.memberNameEditText);
        membershipDateEditText = findViewById(R.id.membershipDateEditText);
        saveMemberButton = findViewById(R.id.saveMemberButton);

        // Check if we're editing an existing member
        if (getIntent().hasExtra("member_id")) {
            memberId = getIntent().getIntExtra("member_id", -1);
            loadMemberData(memberId);
        }

        saveMemberButton.setOnClickListener(v -> saveMember());
    }

    private void loadMemberData(int memberId) {
        MemberDAO memberDAO = new MemberDAO(databaseHelper.getWritableDatabase());
        Member member = memberDAO.getMemberById(memberId);
        if (member != null) {
            memberNameEditText.setText(member.getName());
            membershipDateEditText.setText(member.getMembershipDate());
        } else {
            Toast.makeText(this, "Error loading member details!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveMember() {
        String name = memberNameEditText.getText().toString().trim();
        String membershipDate = membershipDateEditText.getText().toString().trim();

        MemberDAO memberDAO = new MemberDAO(databaseHelper.getWritableDatabase());
        if (memberId == -1) {
            memberDAO.insertMember(name, membershipDate);
            Toast.makeText(this, "Member added successfully!", Toast.LENGTH_SHORT).show();
        } else {
            memberDAO.updateMember(memberId, name, membershipDate);
            Toast.makeText(this, "Member updated successfully!", Toast.LENGTH_SHORT).show();
        }
        finish(); // Close the activity
    }
}
