package com.lxa.demo.mydemo.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.lxa.demo.mydemo.activity.util.CoreConst;
import com.lxa.demo.mydemo.util.HttpUtlis;
import com.lxa.demo.mydemo.util.OnResponseListner;
import com.lxa.demo.mydemo.R;

import java.util.HashMap;
import java.util.Map;


/**
 * 商品详情
 * 
 * @author 
 *
 */
public class ProductDetailsActivity extends Activity implements OnClickListener {
	/*private SharedPreferences login_sp;*/
	private MyTask myTask;
	private TextView spinner;
    private String code;
	private String path = CoreConst.serverhost+"/api-app/app/home";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productdetails);
		View btButton2 = findViewById(R.id.img_fanhui);
		btButton2.setOnClickListener(this);
		spinner =  findViewById(R.id.tv);
		myTask = new MyTask();
		myTask.execute(path);
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.img_fanhui:
				SharedPreferences userSettings= getSharedPreferences("setting", 0);
				String name = userSettings.getString("name","默认值");
				String url = userSettings.getString("URL","default");
				Toast.makeText(this, "aaaaaaa"+url+name, Toast.LENGTH_SHORT).show();//登录成功提示
				finish();
				break;
		}
	}
	public class MyTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO 后台运行, 执行耗时的操作

			try {
/*
				// 向网络获取数据json
				URL url = new URL(params[0]);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setRequestMethod("GET");

				// 请求数据时不能以压缩的方式返回
				conn.setRequestProperty("Accept-Encoding", "identity");

				conn.connect();

				if (conn.getResponseCode() == 200) {
					InputStream is = conn.getInputStream();

					// 得到数据的总长度
					long maxLen = conn.getContentLength();

					Log.i("info", "maxLen = " + maxLen);

					// 得到数据当前的加载长度
					long curLen = 0;

					// 读取数据
					byte[] buffer = new byte[1024];
					int len = 0;

					StringBuilder sBuilder = new StringBuilder();

					while ((len = is.read(buffer)) != -1) {

						sBuilder.append(new String(buffer, 0, len));

						// 计算当前加载的进度值 百分比
						// int progress = (int)(当前加载的长度 *100 / 总长度);


						// 异步更新进度条(onProgressUpdate(Integer... values))
						publishProgress(100);


					}

					// 解析数据,并且返回
					return sBuilder.toString();
				}*/
				String url = params[0];

				Map<String, String> map=new HashMap<>();

				map.put("userName","");
				map.put("pwd","");
				HttpUtlis.getRequest(url, map,"utf-8", new OnResponseListner() {
					@Override
					public void onSucess(String response) {
                     /*   spinner.setText(response);*/
                        code=response;
					}

					@Override
					public void onError(String error) {

					}
				});
				publishProgress(100);
                Thread.sleep(100);
				return code;

			} catch (Exception e) {
				// TODO: handle exception
			}

			return null;
		}


		@Override
		protected void onPostExecute(String result) {
			// TODO 事后方法: 更新UI
			super.onPostExecute(result);


			// 通过适配器, 将获取到的数据填充到Spinner中
			if (result != null && result.length()> 0) {

				// 控件 spinner
				// 数据源 result
				// 适配器
				spinner.setText(result);


			} else {

			}
		}
	}

}
