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

public class BookDetailActivity extends AppCompatActivity {

    int quanlity = 1;

    TextView bookTitle;
    TextView bookDesc;
    TextView bookPrice;
    TextView bookAuthor;
    ImageView bookImage;
    Button qualityBtn;
    Button buyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final int image = intent.getIntExtra("bookImage", 0);
        final String author = intent.getStringExtra("author");
        final String desc = intent.getStringExtra("desc");
        final double price = intent.getDoubleExtra("price", 0);

        bookTitle = findViewById(R.id.bookDetailTitle);
        bookAuthor = findViewById(R.id.bookDetailAuthor);
        bookImage = findViewById(R.id.bookDetailImage);
        bookPrice = findViewById(R.id.bookDetailPrice);
        bookDesc = findViewById(R.id.bookDetailDescContent);
        qualityBtn = findViewById(R.id.bookDetailQuanlityBtn);
        buyBtn = findViewById(R.id.bookDetailBuyBtn);

        bookTitle.setText(name);
        bookAuthor.setText(author);
        bookImage.setImageResource(image);
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