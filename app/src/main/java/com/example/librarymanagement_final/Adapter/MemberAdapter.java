//package com.example.librarymanagement_final.Adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.librarymanagement_final.AddMemberActivity;
//import com.example.librarymanagement_final.DAO.MemberDAO;
//import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
//import com.example.librarymanagement_final.Model.Member;
//import com.example.librarymanagement_final.R;
//
//import java.util.List;
//
//public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
//    private List<Member> members;
//    private Context context;
//
//    public MemberAdapter(Context context, List<Member> members) {
//        this.context = context;
//        this.members = members;
//    }
//
//    @NonNull
//    @Override
//    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
//        return new MemberViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
//        Member member = members.get(position);
//        holder.memberNameTextView.setText(member.getName());
//        holder.memberIdTextView.setText(String.valueOf(member.getMemberId()));
//        holder.membershipDateTextView.setText(member.getMembershipDate());
//
//        holder.editMemberButton.setOnClickListener(v -> {
//            Intent intent = new Intent(context, AddMemberActivity.class);
//            intent.putExtra("member_id", member.getMemberId()); // Pass the member ID to the activity
//            context.startActivity(intent);
//        });
//
//        holder.deleteMemberButton.setOnClickListener(v -> {
//            MemberDAO memberDAO = new MemberDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
//            memberDAO.deleteMember(member.getMemberId());
//            members.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeChanged(position, members.size());
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return members.size();
//    }
//
//    static class MemberViewHolder extends RecyclerView.ViewHolder {
//        TextView memberNameTextView, memberIdTextView, membershipDateTextView;
//        Button editMemberButton, deleteMemberButton;
//
//        public MemberViewHolder(@NonNull View itemView) {
//            super(itemView);
//            memberNameTextView = itemView.findViewById(R.id.memberNameTextView);
//            memberIdTextView = itemView.findViewById(R.id.memberIdTextView);
//            membershipDateTextView = itemView.findViewById(R.id.membershipDateTextView);
//            editMemberButton = itemView.findViewById(R.id.editMemberButton);
//            deleteMemberButton = itemView.findViewById(R.id.deleteMemberButton);
//        }
//    }
//}



package com.example.librarymanagement_final.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagement_final.AddMemberActivity;
import com.example.librarymanagement_final.DAO.MemberDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Member;
import com.example.librarymanagement_final.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private List<Member> members;
    private Context context;

    public MemberAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = members.get(position);
        holder.memberNameTextView.setText(member.getName());
        holder.memberIdTextView.setText(String.valueOf(member.getMemberId()));
        holder.membershipDateTextView.setText(member.getMembershipDate());

        holder.editMemberButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddMemberActivity.class);
            intent.putExtra("member_id", member.getMemberId()); // Pass the member ID to the activity
            context.startActivity(intent);
        });

        holder.deleteMemberButton.setOnClickListener(v -> {
            MemberDAO memberDAO = new MemberDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
            memberDAO.deleteMember(member.getMemberId());
            members.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, members.size());
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView memberNameTextView, memberIdTextView, membershipDateTextView;
        Button editMemberButton, deleteMemberButton;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            memberNameTextView = itemView.findViewById(R.id.memberNameTextView);
            memberIdTextView = itemView.findViewById(R.id.memberIdTextView);
            membershipDateTextView = itemView.findViewById(R.id.membershipDateTextView);
            editMemberButton = itemView.findViewById(R.id.editMemberButton);
            deleteMemberButton = itemView.findViewById(R.id.deleteMemberButton);
        }
    }
}
