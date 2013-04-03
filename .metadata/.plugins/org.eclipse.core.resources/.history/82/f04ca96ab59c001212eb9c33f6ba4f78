package com.ScarFromTheLionKing.superserialz;

import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Button edit_itemButton = (Button)findViewById(R.id.addItemButton);
		Button add_itemButton = (Button)findViewById(R.id.editItemButton);
		Button delete_itemButton = (Button)findViewById(R.id.deleteItemButton);
        
		final String[] loginInformation = new String[3];
		loginInformation[0] = getIntent().getExtras().getString("username");
        loginInformation[1] = getIntent().getExtras().getString("password");
        loginInformation[2] = "24";	//Dynamically add value to delete later

	
		delete_itemButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            @SuppressWarnings("deprecation")
					public void onClick(View view)
		            {
		               Toast.makeText(getApplicationContext(), "Clicked Delete Item", Toast.LENGTH_SHORT).show();
		                AsyncDeleteItem loginTask = new AsyncDeleteItem();
						loginTask.execute(loginInformation);	//ID of number to delete, will add later
		            }
		        });

		
		
		add_itemButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            @SuppressWarnings("deprecation")
					public void onClick(View view)
		            {
		               Toast.makeText(getApplicationContext(), "Clicked Add Item", Toast.LENGTH_SHORT).show();
		            }
		        });
		
		
		
		edit_itemButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            @SuppressWarnings("deprecation")
					public void onClick(View view)
		            {
		               Toast.makeText(getApplicationContext(), "Clicked Edit Item", Toast.LENGTH_SHORT).show();
		            }
		        });

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	
	
	
	
	
}
