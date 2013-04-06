package com.ScarFromTheLionKing.superserialz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SelectedListviewItem extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Here", "5");

        this.setContentView(R.layout.clickeditemview);
        
        TextView txtProduct = (TextView) findViewById(R.id.product_label);
        
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        txtProduct.setText(product);
        
	}
}
