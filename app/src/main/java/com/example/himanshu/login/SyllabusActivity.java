package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import org.apache.http.NameValuePair;
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

        import com.example.himanshu.login.helper.JSONParser;

public class SyllabusActivity extends ListActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "http://api.androidhive.info/android_connect/get_all_syllabus.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_SID = "sid";
    private static final String TAG_DEPARTMENT = "department";
    private static final String TAG_SEMESTER = "semester";
    private static final String TAG_LINK = "link";

    // products JSONArray
    JSONArray products = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllProducts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String department = ((TextView) view.findViewById(R.id.department))
                        .getText().toString();
                String semester = ((TextView) view.findViewById(R.id.semester))
                        .getText().toString();
                String link = ((TextView) view.findViewById(R.id.link))
                        .getText().toString();

                Intent in = new Intent(getApplicationContext(),
                        SingleSyllabusActivity.class);
                in.putExtra(TAG_DEPARTMENT, department);
                in.putExtra(TAG_SEMESTER, semester);
                in.putExtra(TAG_LINK, link);
                startActivity(in);


            }
        });

    }

    // Response from Edit Product Activity


            /**
             * Background Async Task to Load all product by making HTTP Request
             * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SyllabusActivity.this);
            pDialog.setMessage("Loading products. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String sid = c.getString(TAG_SID);
                        String department = c.getString(TAG_DEPARTMENT);
                        String semester = c.getString(TAG_SEMESTER);
                        String link = c.getString(TAG_LINK);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_SID, sid);
                        map.put(TAG_DEPARTMENT, department);
                        map.put(TAG_SEMESTER, semester);
                        map.put(TAG_LINK, link);

                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            NavigatorActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            SyllabusActivity.this, productsList,
                            R.layout.list_item, new String[] {
                            TAG_DEPARTMENT, TAG_SEMESTER, TAG_LINK},
                            new int[] { R.id.department, R.id.semester, R.id.link });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}