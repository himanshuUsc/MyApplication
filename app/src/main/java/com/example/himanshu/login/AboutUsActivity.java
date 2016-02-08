package com.example.himanshu.login;

/**
 * Created by Himanshu on 7/20/2015.
 */

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;

public class AboutUsActivity extends Activity {

    // Array of strings storing country names
    String[] countries = new String[]{
            "Himanshu singh",
            "Jateen mittal",
            "Shashwat priyadarshi ",
            "Rachana.B.N",

    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{

            R.drawable.himanshu,
            R.drawable.rachanar,
            R.drawable.shashwat,
            R.drawable.rachanar,

    };

    // Array of strings to store currencies
    String[] currency = new String[]{

            "Designer and developer"+"   " +
                    "1PI13IS042" +
                    "Information Science" +
                    "kishu0495@gmail.com" ,
            "Designer and developer"+"   " +
                    "1PI13IS044" +
                    "Information Science" +
                    "jateenmittal0994@gmail.com",
            "Designer and developer"+"   " +
                    "1PI13EC0" +
                    "Electronics and Communication" +
                    "splgahp.m@gmail.com",
            "Designer and developer"+"   " +
                    "1PI13IS0" +
                    "Information Science" +
                    "rachanabnagaraju@gmail.com",
    };



    // Keys used in Hashmap
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_activity);

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<4;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", " " + countries[i]);
            hm.put("cur","" + currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
    }

}
