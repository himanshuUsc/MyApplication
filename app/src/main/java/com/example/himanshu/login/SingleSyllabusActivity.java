package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.TextView;


public class SingleSyllabusActivity  extends Activity {

    // JSON node keys
    private static final String TAG_DEPARTMENT = "department";
    private static final String TAG_SEMESTER = "semester";
    private static final String TAG_LINK = "link";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_syllabus);

        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        String department = in.getStringExtra(TAG_DEPARTMENT);
        String semester = in.getStringExtra(TAG_SEMESTER);
        String link = in.getStringExtra(TAG_LINK);


        // Displaying all values on the screen
        TextView lblDepartment = (TextView) findViewById(R.id.department_label);
        TextView lblSemester = (TextView) findViewById(R.id.semester_label);
        TextView lblLink = (TextView) findViewById(R.id.link_label);

        lblDepartment.setText(department);
        lblSemester.setText(semester);
        lblLink.setText(link);

    }
}

