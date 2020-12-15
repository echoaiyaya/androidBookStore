package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart extends AppCompatActivity {

    TextView cartTitle;
    Button cQuantity;
    Button placeOrder;
    int quantity = 1;
    private LinearLayout mContainer;
    private List<Book> bookList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        gotBooks();

    }

    private void listRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList, 3);
        recyclerView.setAdapter(adapter);

    }


    private void gotBooks() {
        user = (User) getApplication();
        db.collection("carts")
                .whereEqualTo("userId", user.getPhone())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bookList.add(document.toObject(Book.class));
                            }
                            Log.d("TAG", bookList.toString());
                            listRecyclerView();
                        } else {
                            Log.d("test", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
}