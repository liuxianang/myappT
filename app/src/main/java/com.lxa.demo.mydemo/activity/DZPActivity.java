package com.lxa.demo.mydemo.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.activity.bean.LuckyPanView;


public class DZPActivity extends Activity
{
	private LuckyPanView mLuckyPanView;
	private ImageView mStartBtn;
	private ImageView img_fanhui;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mydzp);

		mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
		mStartBtn = (ImageView) findViewById(R.id.id_start_btn);
		img_fanhui = (ImageView) findViewById(R.id.img_fanhui);
		mStartBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

					mStartBtn.setImageResource(R.drawable.stop);
					mLuckyPanView.luckyStart(1);

						mStartBtn.setImageResource(R.drawable.start);
						mLuckyPanView.luckyEnd();


			}
		});
		img_fanhui.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();

			}});
	}

}
