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

import com.example.librarymanagement_final.AddAuthorActivity;
import com.example.librarymanagement_final.AddBookActivity;
import com.example.librarymanagement_final.DAO.AuthorDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Author;
import com.example.librarymanagement_final.R;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {
    private List<Author> authors;
    private Context context;

    public AuthorAdapter(Context context, List<Author> authors) {
        this.context = context;
        this.authors = authors;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_item, parent, false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = authors.get(position);
        holder.authorNameTextView.setText(author.getName());

        holder.editAuthorButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddAuthorActivity.class);
            intent.putExtra("author_id", author.getAuthorId());
            context.startActivity(intent);
        });

        holder.deleteAuthorButton.setOnClickListener(v -> {
            authors.remove(position);
            notifyItemRemoved(position);
            AuthorDAO authorDAO = new AuthorDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
            authorDAO.deleteAuthor(author.getAuthorId());
        });
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView authorNameTextView;
        Button editAuthorButton, deleteAuthorButton;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            authorNameTextView = itemView.findViewById(R.id.authorNameTextView);
            editAuthorButton = itemView.findViewById(R.id.editAuthorButton);
            deleteAuthorButton = itemView.findViewById(R.id.deleteAuthorButton);
        }
    }
}
