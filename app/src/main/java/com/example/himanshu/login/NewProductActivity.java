package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.himanshu.login.helper.JSONParser;

public class NewProductActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;


    JSONParser jsonParser = new JSONParser();
    EditText inputName1;
    EditText inputName2;
    EditText inputName3;
    EditText inputPrice;

    // url to create new product
    private static String url_create_product = "http://api.androidhive.info/android_connect/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BOOKNAME = "name";
    private static final String TAG_AUTHORNAME = "mobile";

            Intent in = new Intent();
    String sellername = in.getStringExtra(TAG_BOOKNAME);
    String mobile = in.getStringExtra(TAG_AUTHORNAME);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        // Edit Text

        inputName1 = (EditText) findViewById(R.id.inputName1);
        inputName2 = (EditText) findViewById(R.id.inputName2);
        inputName3 = (EditText) findViewById(R.id.inputName3);
        inputPrice = (EditText) findViewById(R.id.inputPrice);



        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NewProductActivity.this);
            pDialog.setMessage("Creating Product..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = args[0];
            String author = args[1];
            String subject = args[2];
            String price = args[3];


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("bookname", name));
            params.add(new BasicNameValuePair("bookauthor", author));
            params.add(new BasicNameValuePair("booksubject", subject));
            params.add(new BasicNameValuePair("bookprice", price));
            params.add(new BasicNameValuePair("name", sellername));
            params.add(new BasicNameValuePair("contact", mobile));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), SellActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}