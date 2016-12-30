package com.example.controltest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvName = (TextView) findViewById(R.id.tvName);
		tvName.setText("Name");
		
		//TextBox txtName = (TextBox) findViewById(R.id.txtName);
		 
		TextView tvSex = (TextView) findViewById(R.id.tvSex);
		tvSex.setText("Sex");
		
		RadioGroup group = (RadioGroup)findViewById(R.id.radioGroup1);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == R.id.radio0)
				{
					Toast toast = Toast.makeText( MainActivity.this, "Radio0", Toast.LENGTH_SHORT); 
					toast.show(); 
				}
				else
				{
					Toast toast = Toast.makeText( MainActivity.this, "Radio1", Toast.LENGTH_SHORT); 
					toast.show();
				}
			}
		});
		
		RadioButton rBtn0 = (RadioButton) findViewById(R.id.radio0);
		RadioButton rBtn1 = (RadioButton) findViewById(R.id.radio1);
		
		rBtn0.setText("ÄÐ");
		rBtn1.setText("Å® ");

		
		CheckBox check1 = (CheckBox) findViewById(R.id.check1);
		CheckBox check2 = (CheckBox) findViewById(R.id.check2);
		CheckBox check3 = (CheckBox) findViewById(R.id.check3);
		
		check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){  
            @Override  
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  
                // TODO Auto-generated method stub  
                if(isChecked)
                {  
					Toast toast = Toast.makeText( MainActivity.this, "check1", Toast.LENGTH_SHORT); 
					toast.show(); 
                }
                else
                {  

                }  
            }  
        });  
		
		check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){  
            @Override  
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  
                // TODO Auto-generated method stub  
                if(isChecked)
                {  
					Toast toast = Toast.makeText( MainActivity.this, "check2", Toast.LENGTH_SHORT); 
					toast.show(); 
                }
                else
                {  

                }  
            }  
        });  
		
		check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){  
            @Override  
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {  
                // TODO Auto-generated method stub  
                if(isChecked)
                {  
					Toast toast = Toast.makeText( MainActivity.this, "check3", Toast.LENGTH_SHORT); 
					toast.show(); 
                }
                else
                {  

                }  
            }  
        });  
		
		Button btnBar = (Button) findViewById(R.id.btnBar);
		btnBar.setText("ProcessBar");
		btnBar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  Intent intent = new Intent();
                                          
              intent.setClass(MainActivity. this, ProBar.class);
              startActivity(intent);
              finish();

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
