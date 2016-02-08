package com.example.himanshu.login;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BuyNextNext extends ListActivity {

    private ProgressDialog pDialog;
    String subject;
    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/get_all_books.php";

    // JSON Node names
    private static final String TAG_ALLBOOKS = "allbooks";
    private static final String TAG_BOOKNAME = "bookname";
    private static final String TAG_AUTHORNAME = "authorname";
    private static final String TAG_NAME = "name";
    private static final String TAG_MOBILE = "mobile";
    private static final String TAG_PRICE = "price";
    private static final String TAG_SUBJECT = "subject";

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextnext);

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String bookname = ((TextView) view.findViewById(R.id.bookname))
                        .getText().toString();
                String authorname = ((TextView) view.findViewById(R.id.authorname))
                        .getText().toString();
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String mobile = ((TextView) view.findViewById(R.id.mobile))
                        .getText().toString();
                String price = ((TextView) view.findViewById(R.id.price))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra(TAG_BOOKNAME, bookname);
                in.putExtra(TAG_AUTHORNAME, authorname);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_MOBILE, mobile);
                in.putExtra(TAG_PRICE, price);
                startActivity(in);

            }
        });

        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(BuyNextNext.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_ALLBOOKS);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String bookname = c.getString(TAG_BOOKNAME);
                        String authorname = c.getString(TAG_AUTHORNAME);
                        String name = c.getString(TAG_NAME);
                        String mobile = c.getString(TAG_MOBILE);
                        String price = c.getString(TAG_PRICE);
                        String subject = c.getString(TAG_SUBJECT);


                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_BOOKNAME, bookname);
                        contact.put(TAG_AUTHORNAME, authorname);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_MOBILE, mobile);
                        contact.put(TAG_PRICE, price);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    BuyNextNext.this, contactList,
                    R.layout.list_item, new String[] { TAG_BOOKNAME, TAG_AUTHORNAME, TAG_NAME, TAG_MOBILE,
                    TAG_PRICE }, new int[] { R.id.bookname, R.id.authorname, R.id.name,
                    R.id.mobile, R.id.price });

            setListAdapter(adapter);
        }

    }

}
