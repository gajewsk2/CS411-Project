package com.android.phpclient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EmployeesListActivity extends Activity {

	ListView listEmployeeDetails;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employeeslist);
		
		listEmployeeDetails=(ListView)findViewById(R.id.listViewEmployees);
		//Get the employee array from the server access class
		ServerAccess ss=new ServerAccess();
		Employee[] employeesArray= ss.getEmployees(Constants.USER_ID);
		//Set the adapter with our custom view so that the Employee name and his/her address will be shown
		listEmployeeDetails.setAdapter(new EmployeeAdapter(this, R.layout.employeeitem, employeesArray));
		
	}

	private class EmployeeAdapter extends ArrayAdapter<Employee>
	{
		//Array to have the objects
		private Employee[] array;
		
		public EmployeeAdapter(Context context, int textViewResourceId,
				Employee[] objects) {
			super(context, textViewResourceId, objects);
			array=objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Set the view for each item in the list view
			View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.employeeitem, null);
            }
            //Get the Textviews from the row view and set the appropriate values for them
            TextView labelName=(TextView) v.findViewById(R.id.labelName);
            TextView labelAddress=(TextView)v.findViewById(R.id.labelAddress);
            labelName.setText(array[position].name);
            labelAddress.setText(array[position].address);
			return v;
		}
	}
}
