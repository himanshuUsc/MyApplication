package com.example.himanshu.login;



        import android.os.Bundle;
        import android.preference.PreferenceActivity;

public class UserSettinsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

    }
}
