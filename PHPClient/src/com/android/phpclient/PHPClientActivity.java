package com.android.phpclient;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PHPClientActivity extends Activity implements OnClickListener{
	
	Button btnLogin;
	EditText txtUsername,txtPassword;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Get the username ans password text boxes 
        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btnLogin:
			//The login button
			ServerAccess ss=new ServerAccess();
			Constants.USER_ID=ss.getUserID(txtUsername.getText().toString(), txtPassword.getText().toString());
			if(Constants.USER_ID==0)
			{
				Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
			}
			else
			{
				Intent employeeListActivity=new Intent(getApplicationContext(),EmployeesListActivity.class);
				startActivity(employeeListActivity);
			}
			break;
		}
		
	}
}