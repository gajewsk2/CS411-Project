<<<<<<< HEAD
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
	final static String getIdURL = "http://superserial.web.engr.illinois.edu/androidGetid.php";
	final static String getNameURL = "http://superserial.web.engr.illinois.edu/androidGetName.php";
	final static String getDescURL = "http://superserial.web.engr.illinois.edu/androidGetDesc.php";
	
	int loginStatus = -1;
	String loginResult;
	protected String finalResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Calls the function that will determine if the login information is correct
		final String[] sendToPost = new String[4];
		final String[] loginInformation = new String[6];
		loginInformation [0] = loginURL;
		loginInformation [1] = getIdURL;
		loginInformation [2] = getNameURL;
		loginInformation [3] = getDescURL;

		
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
		                loginInformation [4] = usernameInput.getText().toString();
		                loginInformation [5] = passwordInput.getText().toString();
		                loginInformation [4] = "brandon";////////
		                loginInformation [5] = "brandon";////////
		            	Log.i("LoginU", loginInformation[4]);
		                Log.i("LoginP", loginInformation[5]);
		                AsyncPost loginTask = new AsyncPost();
		                AsyncPost getIdTask = new AsyncPost();
		                AsyncPost getNameTask = new AsyncPost();
		                AsyncPost getDescTask = new AsyncPost();
		                sendToPost[0] = loginURL;
		                sendToPost[1] = loginInformation[4];
		                sendToPost[2] = loginInformation[5];
		                
							String result;
							try {
								finalResult = loginTask.execute(loginInformation[0], loginInformation[4], loginInformation[5]).get();
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
							try {
								finalResult = getIdTask.execute(loginInformation[1], loginInformation[4], loginInformation[5]).get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String [] idArray;// = new String[jsonStringParser(finalResult).length];
							idArray = jsonStringParser(finalResult).clone();
							int count = 1;
							for(count = 1; count<idArray.length; ++ count)
							{
								Log.i("id", idArray[count]);
							}
							
							
							try {
								finalResult = getNameTask.execute(loginInformation[2], loginInformation[4], loginInformation[5]).get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String [] nameArray;// = new String[jsonStringParser(finalResult).length];
							nameArray = jsonStringParser(finalResult).clone();
							//int count = 1;
							for(count = 1; count<nameArray.length; ++ count)
							{
								Log.i("name", nameArray[count]);
							}

							
							try {
								finalResult = getDescTask.execute(loginInformation[3], loginInformation[4], loginInformation[5]).get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String [] descArray;// = new String[jsonStringParser(finalResult).length];
							descArray = jsonStringParser(finalResult).clone();
							//int count = 1;
							for(count = 1; count<descArray.length; ++ count)
							{
								Log.i("desc", descArray[count]);
							}
							
							
							
			                //AsyncPost getidTask = new AsyncPost();
			                //AsyncPost getnameTask = new AsyncPost();
			                //AsyncPost getDescTask = new AsyncPost();
//							finalResult = loginTask.execute(loginInformation[0], loginInformation[4], loginInformation[5]).get();

			                
			                
			                
			                
			                /*Intent i = new Intent(getApplicationContext(), MenuActivity.class);
			                
			                i.putExtra("loginURL", loginInformation[0]);	//Passes the password to the next activity
			                i.putExtra("getIdURL", loginInformation[1]);	//Passes the password to the next activity
			                i.putExtra("getNameURL", loginInformation[2]);	//Passes the password to the next activity
			                i.putExtra("getDescURL", loginInformation[3]);	//Passes the password to the next activity
			                i.putExtra("username", loginInformation[4]);	//Passes the username to the next activity
			                i.putExtra("password", loginInformation[5]);	//Passes the password to the next activity

			                startActivity(i); // Start MenuActivity
							*/
			                Log.i("Here", "Before Listview");
			                Intent x = new Intent(getApplicationContext(), ListviewMainActivity.class);
			                x.putExtra("loginInformation", loginInformation);
			                x.putExtra("nameArray", nameArray);
			                x.putExtra("idArray", idArray);
			                x.putExtra("descArray", descArray);
			                Log.i("Here", "Before");

			                startActivity(x); // Start 
			                
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
			    		                        //usernameInput.setText("");	//Clears the Input Boxes
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
=======
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
>>>>>>> 6072a9b764e368a83bbd7893d61a856d19756a16
