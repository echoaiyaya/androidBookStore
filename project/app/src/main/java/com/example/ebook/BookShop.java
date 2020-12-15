package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookShop extends AppCompatActivity {


    private List<Book> bookList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView userBtn;
    ImageView bookShelfBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop);


        gotBooks();


        //if user doesn't log in, then go to log in page, else go to user page.
        userBtn = findViewById(R.id.accountBtn);
        bookShelfBtn = findViewById(R.id.bookShelfBtn);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                user = (User) getApplication();
                Log.i("test", String.valueOf(user.checkLogin()));
                if(user.checkLogin()) {
                    Intent userInfo = new Intent(BookShop.this, UserInfoActivity.class);
                    startActivity(userInfo);
                } else {
                    Intent userInfo = new Intent(BookShop.this, LoginActivity.class);
                    startActivity(userInfo);
                }

            }
        });
        bookShelfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookShelf = new Intent(BookShop.this, MainActivity.class);
                startActivity(bookShelf);
            }
        });
    }


    private void listRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList, 0);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(BookShop.this, BookDetailActivity.class);
                Book mbook = bookList.get(position);
                intent.putExtra("name", mbook.getName());
                intent.putExtra("bookImage", mbook.getImageId());
                intent.putExtra("author", mbook.getAuthor());
                intent.putExtra("desc", mbook.getDescription());
                intent.putExtra("price", mbook.getPrice());
                intent.putExtra("type", mbook.getType());
                intent.putExtra("bookId", mbook.getBookId());
                intent.putExtra("webApi", mbook.getWebApi());
                startActivity(intent);
            }
        });
    }


    private void gotBooks() {
        db.collection("books")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bookList.add(document.toObject(Book.class));

                            }
                            listRecyclerView();
                        } else {
                            Log.d("test", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}