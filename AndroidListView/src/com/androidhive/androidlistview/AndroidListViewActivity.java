package com.androidhive.androidlistview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidListViewActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // storing string resources into Array
        //String[] adobe_products = getResources().getStringArray(R.array.adobe_products);
        String[] newString = new String[20];
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
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, newString));
        
        ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  
        	  // selected item 
        	  String product = ((TextView) view).getText().toString();
        	  
        	  // Launching new Activity on selecting single List Item
        	  Intent i = new Intent(getApplicationContext(), SingleListItem.class);
        	  // sending data to new activity
        	  i.putExtra("product", product);
        	  startActivity(i);
        	
          }
        });
    }
}