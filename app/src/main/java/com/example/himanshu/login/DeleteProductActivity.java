package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.himanshu.login.helper.JSONParser;

public class DeleteProductActivity extends Activity {
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputName1;
    EditText inputName2;
    EditText inputName3;
    EditText inputPrice;

    // url to create new product
    //  private static String url_create_product = "http://api.androidhive.info/android_connect/delete_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_product);

        // Edit Text

        inputName1 = (EditText) findViewById(R.id.inputName1);
        inputName2 = (EditText) findViewById(R.id.inputName2);
        inputName3 = (EditText) findViewById(R.id.inputName3);
        inputPrice = (EditText) findViewById(R.id.inputPrice);

        // Create button
        Button btnDeleteProduct = (Button) findViewById(R.id.btnDeleteProduct);

        // button click event
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                Intent i = new Intent(DeleteProductActivity.this, DeletionProduct.class);
                i.putExtra("bname", inputName1.getText().toString());
                i.putExtra("bauthor", inputName2.getText().toString());
                i.putExtra("bsubject", inputName3.getText().toString());
                i.putExtra("bprice", inputPrice.getText().toString());
                startActivity(i);
            }
        });
    }
}




