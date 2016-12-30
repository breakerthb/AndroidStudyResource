package com.example.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class SubMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
        menu.add(0, 1, 1, "ƻ��");
        menu.add(0, 2, 2, "�㽶");
        
        SubMenu subMenu = menu.addSubMenu(1, 100, 100, "����");
        subMenu.add(2, 101, 101, "������");
        subMenu.add(2, 102, 102, "С����");
        
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
            Toast.makeText(this, "��ѡ����ƻ��", Toast.LENGTH_SHORT).show();
			break;
		case 2:
            Toast.makeText(this, "��ѡ�����㽶", Toast.LENGTH_SHORT).show();
			break;
		case 101:
			Toast.makeText(this, "������", Toast.LENGTH_SHORT).show();
			break;
		case 102:
			Toast.makeText(this, "С����", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}



}
