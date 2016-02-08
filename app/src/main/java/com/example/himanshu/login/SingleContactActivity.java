package com.example.himanshu.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleContactActivity  extends Activity {

    // JSON node keys
    private static final String TAG_BOOKNAME = "book_name";
    private static final String TAG_AUTHORNAME = "author_name";
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PRICE = "price";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);

        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        String book_name = in.getStringExtra(TAG_BOOKNAME);
        String author_name = in.getStringExtra(TAG_AUTHORNAME);
        String name = in.getStringExtra(TAG_NAME);
        String price = in.getStringExtra(TAG_PRICE);
        String mobile = in.getStringExtra(TAG_PHONE_MOBILE);

        // Displaying all values on the screen
        TextView lblBookName = (TextView) findViewById(R.id.book_name_label);
        TextView lblAuthorName = (TextView) findViewById(R.id.author_label);
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblMobile = (TextView) findViewById(R.id.mobile_label);
        TextView lblPrice = (TextView) findViewById(R.id.price_label);

        lblBookName.setText(book_name);
        lblAuthorName.setText(author_name);
        lblName.setText(name);
        lblMobile.setText(mobile);
        lblPrice.setText(price);
    }
}

