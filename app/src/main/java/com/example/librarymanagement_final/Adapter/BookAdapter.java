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
import com.example.librarymanagement_final.AddBookActivity;
import com.example.librarymanagement_final.DAO.BookDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Book;
import com.example.librarymanagement_final.R;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorIdTextView.setText("Author ID: " + book.getAuthorId());
        holder.publisherIdTextView.setText("Publisher ID: " + book.getPublisherId());
        holder.yearTextView.setText("Year: " + book.getYear());

        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddBookActivity.class);
            intent.putExtra("book_id", book.getId());
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            books.remove(position);
            notifyItemRemoved(position);
            BookDAO bookDAO = new BookDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
            bookDAO.deleteBook(book.getId());
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorIdTextView, publisherIdTextView, yearTextView;
        Button editButton, deleteButton;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorIdTextView = itemView.findViewById(R.id.authorIdTextView);
            publisherIdTextView = itemView.findViewById(R.id.publisherIdTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
