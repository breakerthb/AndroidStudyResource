package com.example.controltest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProBar extends Activity {

	int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_probar);
		
		final ProgressBar bar1 = (ProgressBar) findViewById(R.id.ProgressBar1);
		final ProgressBar bar2 = (ProgressBar) findViewById(R.id.progressBar2);
		Button btn = (Button) findViewById(R.id.btnStart);

		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (i == 0){
					bar1.setVisibility(View.VISIBLE);//设置进度条可视
					//设置进度条的最大值，默认为100，该值也可在布局文件中设置,
					//如果即在布局文件中设置了此值，又在程序中设置了，那么程序中设置的值会覆盖布局文件中设置的值
					bar1.setMax(150);	
					bar2.setVisibility(View.VISIBLE);//设置进度条可视
				}else if (i < bar1.getMax()){
					//设置主进度条的当前值
					bar1.setProgress(i);
					//设置进度条第二进度的当前值
					bar1.setSecondaryProgress(i+10);
					
					//默认进度条无法显示进行的状态
					bar2.setProgress(i);
				}else{
					//设置进度条不可视
					bar1.setVisibility(View.GONE);
					bar2.setVisibility(View.GONE);
				}
				
				i+=10;
			}
		});
	}

}
