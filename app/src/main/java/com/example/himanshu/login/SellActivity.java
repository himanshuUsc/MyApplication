package com.example.himanshu.login;



        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class SellActivity extends Activity{

    Button btnInsertProduct;
    Button btnDeleteProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        // Buttons
        btnInsertProduct = (Button) findViewById(R.id.btnInsertProduct);
        btnDeleteProduct = (Button) findViewById(R.id.btnDeleteProduct);

        // view products click event
        btnInsertProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(i);

            }
        });

        // view products click event
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), DeleteProductActivity.class);
                startActivity(i);

            }
        });
    }
}