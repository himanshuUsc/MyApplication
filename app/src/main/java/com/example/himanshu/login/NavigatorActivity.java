package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */
import com.example.himanshu.login.app.AppConfig;
import com.example.himanshu.login.app.AppController;
import com.example.himanshu.login.helper.SessionManager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class NavigatorActivity extends Activity {

    private Button btnBuy;
    private Button btnSell;
    private Button btnSyllabus;
    private Button btnAboutUs;
    private Button btnSettings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnSell = (Button) findViewById(R.id.btnSell);
        btnSyllabus = (Button) findViewById(R.id.btnSyllabus);
        btnAboutUs = (Button) findViewById(R.id.btnAboutUs);
        btnSettings = (Button) findViewById(R.id.btnSettings);

        btnBuy.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
             Intent i= new Intent(NavigatorActivity.this, BuyActivity.class);
                startActivity(i);

            }

        });

        btnSell.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i= new Intent(NavigatorActivity.this, SellActivity.class);
                startActivity(i);
            }

        });

        btnSyllabus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i= new Intent(NavigatorActivity.this, SyllabusActivity.class);
                startActivity(i);
            }

        });

        btnSettings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i= new Intent(NavigatorActivity.this, SettingsActivity.class);
                startActivity(i);
            }

        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i= new Intent(NavigatorActivity.this, AboutUsActivity.class);
                startActivity(i);
            }

        });

    }
}