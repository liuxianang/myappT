package com.lxa.demo.mydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.lxa.demo.mydemo.R;
public class QHBActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qhb);

		findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				open();
			}
		});
	}

	private void open()
	{
		try
		{
			Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到“抢红包外挂”，然后开启服务即可", Toast.LENGTH_LONG).show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
