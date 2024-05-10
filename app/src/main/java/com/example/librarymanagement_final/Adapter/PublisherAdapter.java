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

import com.example.librarymanagement_final.AddPublisherActivity;
import com.example.librarymanagement_final.DAO.PublisherDAO;
import com.example.librarymanagement_final.Helper.LibraryDatabaseHelper;
import com.example.librarymanagement_final.Model.Publisher;
import com.example.librarymanagement_final.R;

import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.PublisherViewHolder> {
    private List<Publisher> publishers;
    private Context context;

    public PublisherAdapter(Context context, List<Publisher> publishers) {
        this.context = context;
        this.publishers = publishers;
    }

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publisher_item, parent, false);
        return new PublisherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublisherViewHolder holder, int position) {
        Publisher publisher = publishers.get(position);
        holder.publisherNameTextView.setText(publisher.getName());

        holder.editPublisherButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddPublisherActivity.class);
            intent.putExtra("publisher_id", publisher.getPublisherId()); // Pass the publisher ID to the edit activity
            context.startActivity(intent);
        });

        holder.deletePublisherButton.setOnClickListener(v -> {
            PublisherDAO publisherDAO = new PublisherDAO(new LibraryDatabaseHelper(context).getWritableDatabase());
            publisherDAO.deletePublisher(publisher.getPublisherId());
            publishers.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, publishers.size());
        });
    }

    @Override
    public int getItemCount() {
        return publishers.size();
    }

    static class PublisherViewHolder extends RecyclerView.ViewHolder {
        TextView publisherNameTextView;
        Button editPublisherButton, deletePublisherButton;

        public PublisherViewHolder(@NonNull View itemView) {
            super(itemView);
            publisherNameTextView = itemView.findViewById(R.id.publisherNameTextView);
            editPublisherButton = itemView.findViewById(R.id.editPublisherButton);
            deletePublisherButton = itemView.findViewById(R.id.deletePublisherButton);
        }
    }
}
