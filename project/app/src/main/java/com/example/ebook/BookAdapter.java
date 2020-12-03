package com.example.ebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> mBookList;

    // a listener for return the click event of each item;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bookView;
        ImageView bookImage;
        TextView bookName;
        TextView bookAuthor;
        TextView bookPrice;
        TextView bookType;
        Button buyBtn;

        public ViewHolder(View view) {
            super(view);
            bookView = view;
            bookImage = (ImageView) view.findViewById(R.id.bookImage1);
            bookName = (TextView) view.findViewById(R.id.bookTitle1);
            bookAuthor = (TextView) view.findViewById(R.id.bookAuthor1);
            bookPrice = (TextView) view.findViewById(R.id.bookPrice1);
            bookType = (TextView) view.findViewById(R.id.bookType1);
        }
    }

    public BookAdapter(List<Book> bookList) {
        mBookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = mBookList.get(position);
                mOnItemClickListener.onItemClick(v, position);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bookImage.setImageResource(book.getImageId());
        holder.bookName.setText(book.getName());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookPrice.setText("CAD$" + book.getPrice().toString());
        holder.bookType.setText("TYPE:" + book.getType().toString());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}