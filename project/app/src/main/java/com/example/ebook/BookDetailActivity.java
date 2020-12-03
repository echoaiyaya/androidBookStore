package com.example.ebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class BookDetailActivity extends AppCompatActivity {

    int quanlity = 1;

    TextView bookTitle;
    TextView bookDesc;
    TextView bookPrice;
    TextView bookAuthor;
    TextView bookType;
    ImageView bookImage;
    Button qualityBtn;
    Button buyBtn;
    Button cartBtn;

    private String name;
    private int image;
    private String author;
    private String desc;
    private double price;
    private String type;
    private String bookId;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        image = intent.getIntExtra("bookImage", 0);
        author = intent.getStringExtra("author");
        desc = intent.getStringExtra("desc");
        price = intent.getDoubleExtra("price", 0);
        type = intent.getStringExtra("type");
        bookId = intent.getStringExtra("bookId");

        bookTitle = findViewById(R.id.bookDetailTitle);
        bookAuthor = findViewById(R.id.bookDetailAuthor);
        bookImage = findViewById(R.id.bookDetailImage);
        bookPrice = findViewById(R.id.bookDetailPrice);
        bookDesc = findViewById(R.id.bookDetailDescContent);
        qualityBtn = findViewById(R.id.bookDetailQuanlityBtn);
        bookType = findViewById(R.id.bookDetailType);
        buyBtn = findViewById(R.id.bookDetailBuyBtn);
        cartBtn = findViewById(R.id.bookDetailCartBtn);

        bookTitle.setText(name);
        bookAuthor.setText(author);
        bookImage.setImageResource(image);
        bookType.setText(type);
        bookPrice.setText(String.valueOf(price));
        bookDesc.setText(desc);

        qualityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paymentPage = new Intent(BookDetailActivity.this, PaymentActivity.class);
                paymentPage.putExtra("name", name);
                paymentPage.putExtra("image", image);
                paymentPage.putExtra("author", author);
                paymentPage.putExtra("price", price);
                paymentPage.putExtra("quanlity", quanlity);
                startActivity(paymentPage);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                user = (User) getApplication();
                if(user.checkLogin()) {
                    Map<String, Object> bookCart = new HashMap<>();
                    Map<String, Object> bookSave = new HashMap<>();
                    bookSave.put("bookId", bookId);
                    bookSave.put("bookQty", quanlity);
                    bookCart.put(bookId, bookSave);
                    db.collection("carts").document(user.getPhone()).set(bookCart, SetOptions.merge());
                } else {
                    Toast.makeText(BookDetailActivity.this, "Please Login first!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void showInputDialog() {
        final EditText editText = new EditText(BookDetailActivity.this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(BookDetailActivity.this);
        inputDialog.setTitle("How many items you need?").setView(editText);
        inputDialog.setPositiveButton("Comfirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanlity = Integer.parseInt(editText.getText().toString());
                        qualityBtn.setText("QTY: " + editText.getText());
                        dialog.dismiss();
                    }
                }).show();
    }
}