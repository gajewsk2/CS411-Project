package com.ScarFromTheLionKing.superserialz;


import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	//URL of the PHP script that we use to login users -> will return 1 if correct login information, else 0
	final static String loginURL = "http://superserial.web.engr.illinois.edu/androidLogin.php";
	int loginStatus = -1;
	String loginResult;
	protected String finalResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Calls the function that will determine if the login information is correct
		final String[] loginInformation = new String[3];
		loginInformation [0] = loginURL;
		//loginInformation [0] = loginURL;
		//new AsyncPost().execute(loginInformation);  
		final EditText usernameInput = (EditText)findViewById(R.id.usernameTextField);
		final EditText passwordInput = (EditText)findViewById(R.id.passwordTextField);
		
		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            @SuppressWarnings("deprecation")
					public void onClick(View view)
		            {
		                loginInformation [1] = usernameInput.getText().toString();
		                loginInformation [2] = passwordInput.getText().toString();
		                //loginInformation [1] = "username";////////
		                //loginInformation [2] = "password";////////
		            	Log.i("LoginU", loginInformation[1]);
		                Log.i("LoginP", loginInformation[2]);
		                AsyncPost loginTask = new AsyncPost();
		                
							String result;
							try {
								finalResult = loginTask.execute(loginInformation[0], loginInformation[1], loginInformation[2]).get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			                Log.i("fin", finalResult);
		                if(finalResult.equalsIgnoreCase("{\"status\":1,\"txt\":\"profile.php\"}"))
		                {
		                	loginStatus = 1;
			                Log.i("loginResult", "Login was succesful!!!");
			                Toast.makeText(getApplicationContext(), "Login was Succesfull", Toast.LENGTH_SHORT).show();
			                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
			                i.putExtra("username", loginInformation[1]);	//Passes the username to the next activity
			                i.putExtra("password", loginInformation[2]);	//Passes the password to the next activity

			                startActivity(i); // Start MenuActivity

		                }
		                else
		                {
		                	loginStatus = 0;
			                Log.i("loginResult", "Login Did not happen");
			                //Toast.makeText(getApplicationContext(), "Login Failed\nUserName or Password did not match", Toast.LENGTH_SHORT).show();
			        		if(loginStatus == 0)
			    			{
			    		        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			    		        builder.setMessage("Login was Unsuccesful")
			    		        		.setTitle("Login")
			    		               .setCancelable(false)
			    		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			    		                   public void onClick(DialogInterface dialog, int id) {
			    		                        usernameInput.setText("");	//Clears the Input Boxes
			    		                        passwordInput.setText("");	//
			    		                   }
			    		               });
			    		        AlertDialog alert = builder.create();
			    		        alert.show();
			    			}

		                }

		                //new AsyncPost().execute(loginInformation[0], loginInformation[1], loginInformation[2]);  
		        		//int tempint = -1;
		        		//tempint = new AsyncPost().returnLoginStatus();
		                //Log.i("qwert", Integer.toString(tempint));

		            }
		        });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	  public String[] jsonStringParser(String jsonString)
		{	
			
			String delims = "[\\[\\],\"]+";
			String[] tokens = jsonString.split(delims);
//			int i = 0;
//			Log.i("string1", jsonString);
//			if(tokens.length == 0)
//				Log.i("jsonS", "empty");
//			for (String t:tokens)
//			{
//				++i;
//				Log.i("tokens", i+t);
//			}		
			return tokens;
			
		}
	//new RequestTask().execute("http://stackoverflow.com");
}
