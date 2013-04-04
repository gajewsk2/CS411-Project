package com.ScarFromTheLionKing.superserialz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncPost extends AsyncTask<String, String, String>{
	public int loginStatus = -2;
    @Override
    protected String doInBackground(String... uri) {
    	
        Log.i("loginTag", uri[0]);
        Log.i("loginTag", uri[1]);
        Log.i("loginTag", uri[2]);
        return postData(uri);

       
    	

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
        Log.i("Results", result);
        returnLoginStatus();

    }
    
    
    public String postData(String... uri) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(uri[0]);//"http://superserial.web.engr.illinois.edu/login.php");
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username",uri[1]));	//Need to get input 
            nameValuePairs.add(new BasicNameValuePair("pass", uri[2]));		//From textfields
            //nameValuePairs.add(new BasicNameValuePair("user","username"));////////	
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            String strResponse = EntityUtils.toString(entity);
            Log.i("loginTag", strResponse);
            return strResponse;

        	

            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return "there was an error or something";
    } 
    
    
    
    
    int returnLoginStatus(){
          return loginStatus; 
        }
}
