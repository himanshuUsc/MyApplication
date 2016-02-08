package com.example.himanshu.login;



        import java.util.ArrayList;
        import java.util.List;
    import android.widget.EditText;
        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;

        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;



public class BuyNext extends Activity implements OnItemSelectedListener {
    private Button btnlogin2;
    private TextView txtCategory;
    private Spinner spinnerSubject;
    String item1;
    // array list for spinner adapter
    private ArrayList<Subject> subjectsList;
    ProgressDialog pDialog;

    // API urls

    // Url to get all categories
    private String URL_CATEGORIES = "http://10.0.2.2/food_api/get_categories.php";
    //  just check with the url properly while coding it up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buynext);


         spinnerSubject = (Spinner) findViewById(R.id.spinSubject);
        txtCategory = (TextView) findViewById(R.id.txtCategory);
        btnlogin2 = (Button) findViewById(R.id.btnLogin2);

        String getting_data1 = getIntent().getStringExtra("get_department");
        String getting_data2 = getIntent().getStringExtra("get_semester");

        subjectsList = new ArrayList<Subject>();

        // spinner item select listener
        spinnerSubject.setOnItemSelectedListener(this);

        btnlogin2.setOnClickListener(new View.OnClickListener()

        {


            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), BuyNextNext.class);
                i.putExtra("subject",item1);
                startActivity(i);

            }
        });
    }
        /**
         * Adding spinner data
         * */

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();

        txtCategory.setText("");

        for (int i = 0; i < subjectsList.size(); i++) {
            lables.add(subjectsList.get(i).getsubject_name());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerSubject.setAdapter(spinnerAdapter);
    }


    /**
     * Async task to get all food categories
     */
    class GetSubjects extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(BuyNext.this);
            pDialog.setMessage("Fetching subject categories..");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler jsonParser = new ServiceHandler();
            String json = jsonParser.makeServiceCall(URL_CATEGORIES, ServiceHandler.GET);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {
                        JSONArray categories = jsonObj
                                .getJSONArray("subjects");

                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject catObj = (JSONObject) categories.get(i);
                            Subject cat = new Subject(catObj.getString("department"),
                                    catObj.getString("semester"), catObj.getString("subject_name"));
                            subjectsList.add(cat);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            populateSpinner();
        }

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        item1 = parent.getItemAtPosition(position).toString();
        Toast.makeText(
                getApplicationContext(),
                parent.getItemAtPosition(position).toString() + " Selected",
                Toast.LENGTH_LONG).show();

    }


    public void onNothingSelected(AdapterView<?> arg0) {
    }




}
