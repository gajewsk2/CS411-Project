package com.ScarFromTheLionKing.superserialz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListviewMainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Here", "Listview");
        String [] loginInformation;// = new String[jsonStringParser(finalResult).length];
        String [] nameArray;
        String [] idArray;
        String [] descArray;
        //descArray = jsonStringParser(finalResult).clone();
        loginInformation = ((String []) getIntent().getSerializableExtra("loginInformation")).clone();
        nameArray = ((String []) getIntent().getSerializableExtra("nameArray")).clone();
        idArray = ((String []) getIntent().getSerializableExtra("idArray")).clone();
        descArray = ((String []) getIntent().getSerializableExtra("descArray")).clone();
        
        int count = 1;
		for(count = 1; count<loginInformation.length; ++ count)
		{
			Log.i("loginInfo1", loginInformation[count]);
		}
        
		for(count = 1; count<nameArray.length; ++ count)
		{
			Log.i("name1", nameArray[count]);
		}
        
        for(count = 1; count<idArray.length; ++ count)
		{
			Log.i("id1", idArray[count]);
		}
        
        for(count = 1; count<descArray.length; ++ count)
		{
			Log.i("desc1", descArray[count]);
		}
        // storing string resources into Array
        //String[] adobe_products = getResources().getStringArray(R.array.adobe_products);
        String[] newString = new String[40];
        newString[0] = "1";
        newString[1] = "2";
        newString[2] = "3";
        newString[3] = "4";
        newString[4] = "5";
        newString[5] = "6";
        newString[6] = "7";
        newString[7] = "8";
        newString[8] = "9";
        newString[9] = "10";
        newString[10] = "11";
        newString[11] = "12";
        newString[12] = "13";
        newString[13] = "14";
        newString[14] = "15";
        newString[15] = "16";
        newString[16] = "17";
        newString[17] = "18";
        newString[18] = "19";
        newString[19] = "20";
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.listview_main, R.id.label, nameArray));
        Log.i("Here", "view");

        ListView lv = getListView();
        Log.i("Here", "2");

        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
              Log.i("Here", "1");

        	  // selected item 
        	  String product = ((TextView) view).getText().toString();
        	  Log.i("Here", "Here");
        	  // Launching new Activity on selecting single List Item
        	  Intent i = new Intent(getApplicationContext(), SelectedItemActivity.class);
        	  // sending data to new activity
              Log.i("Here", "3");

        	  i.putExtra("product", product);
              Log.i("Here", "4");

        	  startActivity(i);
        	
          }
        });
    }
}