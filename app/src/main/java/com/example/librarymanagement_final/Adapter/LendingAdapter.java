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

import com.example.librarymanagement_final.AddLendingActivity;

import com.example.librarymanagement_final.DAO.LendingDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Lending;
import com.example.librarymanagement_final.R;

import java.util.List;

public class LendingAdapter extends RecyclerView.Adapter<LendingAdapter.LendingViewHolder> {
    private List<Lending> lendings;
    private Context context;

    public LendingAdapter(Context context, List<Lending> lendings) {
        this.context = context;
        this.lendings = lendings;
    }

    @NonNull
    @Override
    public LendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lending_item, parent, false);
        return new LendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LendingViewHolder holder, int position) {
        Lending lending = lendings.get(position);
        holder.lendingIdTextView.setText(String.valueOf(lending.getLendingId()));
        holder.bookIdTextView.setText(String.valueOf(lending.getBookId()));
        holder.memberIdTextView.setText(String.valueOf(lending.getMemberId()));
        holder.borrowedDateTextView.setText(lending.getBorrowedDate());
        holder.returnDateTextView.setText(lending.getReturnDate());

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddLendingActivity.class);
            intent.putExtra("lending_id", lending.getLendingId());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            lendings.remove(position);
            notifyItemRemoved(position);
            LendingDAO lendingDAO = new LendingDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
            lendingDAO.deleteLending(lending.getLendingId());
        });
    }

    @Override
    public int getItemCount() {
        return lendings.size();
    }

    static class LendingViewHolder extends RecyclerView.ViewHolder {
        TextView lendingIdTextView, bookIdTextView, memberIdTextView, borrowedDateTextView, returnDateTextView;
        Button editButton, deleteButton;

        public LendingViewHolder(@NonNull View itemView) {
            super(itemView);
            lendingIdTextView = itemView.findViewById(R.id.lendingIdTextView);
            bookIdTextView = itemView.findViewById(R.id.bookIdTextView);
            memberIdTextView = itemView.findViewById(R.id.memberIdTextView);
            borrowedDateTextView = itemView.findViewById(R.id.borrowedDateTextView);
            returnDateTextView = itemView.findViewById(R.id.returnDateTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
