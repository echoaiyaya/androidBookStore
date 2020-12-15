package com.example.ebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    Double price;
    Double shipPrice = 6.00;
    Double taxPrice;
    Double beforeTax;
    Double totalPrice;
    String bookType;
    String bookId;
    String author;
    String desc;
    String name;
    int imageId;
    int quanlity = 1;
    String webApi;

    Button creditBtn;
    Button buyBtn;
    TextView cTitle;
    TextView cPrice;
    ImageView cImage;
    Button cQuanlity;
    TextView cAddress;
    TextView itemPriceView;
    TextView taxPriceView;
    TextView totalPriceView;
    TextView cn;
    TextView cd;
    TextView cv;



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RelativeLayout creditLinear;
    User user;


//    public void getCredit(String num, String date, String cvv) {
//        this.cardNum = num;
//        this.cardDate = date;
//        this.cardCvv = cvv;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        user = (User) getApplication();

        creditBtn = findViewById(R.id.CreditCardBtn);

        cn = findViewById(R.id.paymentCardNum);
        cd = findViewById(R.id.paymentCardDate);
        cv = findViewById(R.id.paymentCardCVV);
        cAddress = findViewById(R.id.cart_addressContent);

        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditCardDialog mDialog = new CreditCardDialog(new CreditCardDialog.PriorityListener(){
                    @Override
                    public void setActivityText(CreditCard creditInfo) {
                        creditLinear = findViewById(R.id.creditCardCardLinear);

                        cn.setText(creditInfo.getCreditNum());
                        cd.setText(creditInfo.getCreditDate());
                        cv.setText(creditInfo.getCreditCvv());


                    }
                });
                mDialog.show(getSupportFragmentManager(), "credit");
            }
        });

        cTitle = findViewById(R.id.cart_reviewCard_name);
        cImage = findViewById(R.id.cart_reviewCard_image);
        cPrice = findViewById(R.id.cart_reviewCard_price);
        cQuanlity = findViewById(R.id.quantityBtn);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        imageId = intent.getIntExtra("image", 0);
        price = intent.getDoubleExtra("price", 0);
        author = intent.getStringExtra("author");
        quanlity = intent.getIntExtra("quanlity", 0);
        bookId = intent.getStringExtra("bookId");
        bookType = intent.getStringExtra("bookType");
        desc = intent.getStringExtra("desc");
        webApi = intent.getStringExtra("webApi");

        cTitle.setText(name);
        cImage.setImageResource(imageId);
        cPrice.setText(String.valueOf(price));
        cQuanlity.setText("Quanlity:" + quanlity);

        User user;
        user = (User) getApplication();
        if(user.checkLogin()) {
            String userName = user.getFirstName() + user.getLastName();
            String address = user.getAddress();

            cAddress.setText(userName + " , " + address);
        }

        itemPriceView = findViewById(R.id.cart_itemPrice);
        taxPriceView = findViewById(R.id.cart_taxPrice);
        totalPriceView = findViewById(R.id.cart_totalPrice);

        setPrice(price, quanlity, shipPrice);


        cQuanlity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        buyBtn = findViewById(R.id.cartoutBtn);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });


    }

    private void setPrice(double price, int quanlity, double shipPrice) {
        beforeTax = price * quanlity;
        taxPrice = (beforeTax + shipPrice) * 13 / 100;
        totalPrice = beforeTax + taxPrice;
        itemPriceView.setText("CDN$ " + String.format("%.2f", beforeTax));
        taxPriceView.setText("CND$ " + String.format("%.2f", taxPrice));
        totalPriceView.setText("CDN$ " + String.format("%.2f", totalPrice));
    }

    private void showInputDialog() {
        final EditText editText = new EditText(PaymentActivity.this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(PaymentActivity.this);
        inputDialog.setTitle("How many items you need?").setView(editText);
        inputDialog.setPositiveButton("Comfirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quanlity = Integer.parseInt(editText.getText().toString());
                        setPrice(price, quanlity, shipPrice);
                        cQuanlity.setText("Quanlity: " + editText.getText());
                        dialog.dismiss();
                    }
                }).show();
    }

    private void checkInput() {
        if (TextUtils.isEmpty(cn.getText()) || TextUtils.isEmpty(cd.getText()) || TextUtils.isEmpty(cv.getText())) {
            Toast.makeText(PaymentActivity.this,"Please complete your credit card information!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cAddress.getText())) {

            Toast.makeText(PaymentActivity.this, "Please log in!", Toast.LENGTH_SHORT).show();


            return;
        }
        Log.d("test", bookType);
        if (bookType.equals("eBook")) {
//            Map<String, Object> bookSave = new HashMap<>();
//            bookSave.put(bookId, bookId);
//            db.collection("bookShelf").document(user.getPhone()).set(bookSave, SetOptions.merge());
            Book saveBook = new Book(name, imageId, author, desc, bookId, webApi);
            Log.d("tset", saveBook.toString());
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();
            db.bookDao().insertAll(saveBook);
            Toast.makeText(PaymentActivity.this, "Your order is completed!Your book is in your bookshelf!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PaymentActivity.this, "Your order is completed!", Toast.LENGTH_SHORT).show();
        }
    }
}