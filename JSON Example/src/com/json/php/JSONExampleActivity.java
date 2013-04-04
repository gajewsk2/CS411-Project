package com.json.php;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class JSONExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://superserial.web.engr.illinois.edu/androidTest.php");
        TextView textView = (TextView)findViewById(R.id.textView1);
		try {
			HttpResponse response = httpclient.execute(httppost);	//Send the POST request
			String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();	//Convert the Jason to string
			Log.i("string", jsonResult);	//Log results in logcat
	    	textView.setText(jsonResult);	//Show results on screen
		} 
		
		catch (ClientProtocolException e) {
			Log.i("String","Error Protocol");
			e.printStackTrace();
		} 
		catch (IOException e) {
			Log.i("String","Error Other");
			e.printStackTrace();
		}


       }
    /*Still need to learn how this function works*/
    private StringBuilder inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
         
        try {
         while ((rLine = rd.readLine()) != null) {
          answer.append(rLine);
           }
        }
        catch (IOException e) {
            e.printStackTrace();
         }
        return answer;
       }
}
    	
        
        
