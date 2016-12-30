package com.example.json;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Button btnJson1 = (Button) findViewById(R.id.btnJson1);
		Button btnJson2 = (Button) findViewById(R.id.btnJson2);
		final TextView tvShow = (TextView) findViewById(R.id.tvShow);
		
		btnJson1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StringBuilder builder = new StringBuilder(); 

				InputStreamReader inputReader = new InputStreamReader(getResources().openRawResource(R.raw.simple));
				BufferedReader bufReader = new BufferedReader(inputReader);


				try {
					String strJson = "";
					String strLine = "";

					while((strLine = bufReader.readLine()) != null){
						strJson += strLine;
					}
					
					JSONObject jsonObject = new JSONObject(strJson).getJSONObject("userbean");
					
	                String Uid; 
	                String Showname; 
	                String Avtar; 
	                String State; 

	                Uid = jsonObject.getString("Uid"); 
	                Showname = jsonObject.getString("Showname"); 
	                Avtar = jsonObject.getString("Avtar"); 
	                State = jsonObject.getString("State");
	                
	                StringBuilder sb = new StringBuilder();
	                sb.append(Uid);
	                sb.append("\n");
	                sb.append(Showname);
	                sb.append("\n");
	                sb.append(Avtar);
	                sb.append("\n");
	                sb.append(State);
	                
	                tvShow.setText(sb.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				

			}
		});
		
		btnJson2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StringBuilder sb = new StringBuilder(); 
				
				InputStreamReader inputReader = new InputStreamReader(getResources().openRawResource(R.raw.complex));
                BufferedReader bufferedReader2 = new BufferedReader(inputReader); 
                String str = ""; 
                try {
					while ((str = bufferedReader2.readLine()) != null) { 
					    sb.append(str); 
					}
					
					 JSONObject jsonObject = new JSONObject(sb.toString()).getJSONObject("calendar"); 
		                JSONArray jsonArray = jsonObject.getJSONArray("calendarlist"); 
		                
		                sb.setLength(0);
		                for(int i = 0; i < jsonArray.length(); i++){ 
		                	JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i); 
		                    sb.append(jsonObject2.getString("calendar_id"));
		                    sb.append("\n");
		                    sb.append(jsonObject2.getString("title")); 
		                    sb.append("\n");
		                    sb.append(jsonObject2.getString("category_name"));
		                    sb.append("\n"); 
		                    sb.append(jsonObject2.getString("showtime")); 
		                    sb.append("\n");
		                    sb.append(jsonObject2.getString("endshowtime")); 
		                    sb.append("\n");
		                    sb.append(jsonObject2.getBoolean("allDay")); 
		                    sb.append("\n----------------------------------\n");
		                }
		                
		                tvShow.setText(sb.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                
               
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
