package com.lxa.demo.mydemo.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.activity.util.CoreConst;
import com.lxa.demo.mydemo.adapter.RegistrationAdapter;
import com.lxa.demo.mydemo.util.HttpUtlis;
import com.lxa.demo.mydemo.util.OnResponseListner;
import com.lxa.demo.mydemo.util.SpecialCalendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 本月日历
 *
 *  @author aiyang
 *
 */
public class SigndateActivity extends Activity implements GridView.OnItemClickListener{
    private GridView registration_calendar_gv;
    private TextView today;
    private String code;
    private String sign;
    View myview;
    int mDays;
    int week;
    private RegistrationAdapter adapter;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    private MyTask3 myTask;
    private MyTask4 myTask4;
    private int[] sginNumber;
    int mYear=0;//年
    int mMonth=0;//月
    int mDay=0;//日
    private String path = CoreConst.serverhost+"/api-app/app/signrecord";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signdate );
        registration_calendar_gv=(GridView)findViewById(R.id.registration_calendar_gv);
        today=(TextView)findViewById(R.id.today);
        Calendar calendar= Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR); // 获取当前年份
        mMonth = calendar.get(Calendar.MONTH) ;// 获取当前月份以（0开头）
        mDay = calendar.get(Calendar.DAY_OF_MONTH) ;// 获取当前天以（0开头）
        SpecialCalendar mCalendar=new SpecialCalendar();
        boolean isLeapYear =mCalendar.isLeapYear(mYear);
         mDays=mCalendar.getDaysOfMonth(isLeapYear,mMonth+1);
         week =mCalendar.getWeekdayOfMonth(mYear,mMonth);
        myTask = new MyTask3();
        myTask.execute(path);
        sginNumber=new int[42];
        for (int i=0;i<42;i++){
            sginNumber[i]=0;
        }
        adapter=new RegistrationAdapter(this,mDays,week,mDay,sginNumber);
        registration_calendar_gv.setAdapter(adapter);
        registration_calendar_gv.setOnItemClickListener(this);
        today.setText(mYear+"年"+mMonth+"月"+mDay+"日");
        View btButton2 = findViewById(R.id.img_fanhui);
        btButton2.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                finish();
            }
        });;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String today=mYear+"-"+mMonth+"-"+l;
        if (l!=0){
            if (l==mDay&&sginNumber[i]==0){
                TextView today_tv= (TextView) view.findViewById(R.id.day);
                today_tv.setBackgroundResource(R.mipmap.member_ok);
                today_tv.setTextColor(Color.BLACK);
                today_tv.setText(l+"");
                myTask4 = new MyTask4();
                myTask4.execute("http://114.115.178.160:8002/api-app/app/dosign");
                 myview=view;
              /*  view.setBackgroundColor(Color.parseColor("#ffffff"));
                Toast.makeText(view.getContext(),"签到成功",Toast.LENGTH_SHORT).show();*/
            }else if(l==mDay&&sginNumber[i]==1){
                Toast.makeText(view.getContext(),"已签到", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(view.getContext(),"时间不对不可签到", Toast.LENGTH_SHORT).show();

            }
        }
    }
    public class MyTask3 extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO 后台运行, 执行耗时的操作

            try {

                String url = params[0];

                Map<String, String> map = new HashMap<>();

                map.put("phone", "15070938483");
                map.put("pwd", "");
                HttpUtlis.getRequest(url, map, "utf-8", new OnResponseListner() {
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
            if (result != null && result.length() > 0) {
                System.out.println(result);
                sginNumber=new int[42];
                try {

                    JSONObject jsonObject;
                    String newsUrl = null;


                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        newsUrl = jsonObject.getString("date");//图片网址
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(Long.parseLong(newsUrl)+43200000);//传输时候可能是12小时制导致时间相差12小时补齐相差时间
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                    System.out.println("aaaaaaaaaaaaaaaa"+newsUrl);
                    int aa=day+week-1;
                        sginNumber[aa]=1;
                    } } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter=new RegistrationAdapter(getBaseContext(),mDays,week,mDay,sginNumber);
                registration_calendar_gv.setAdapter(adapter);


            } else {

            }
        }

    }
    public class MyTask4 extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO 后台运行, 执行耗时的操作

            try {

                String url = params[0];

                Map<String, String> map = new HashMap<>();

                Date date = new Date(); //获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String datetime = sdf.format(date);       //将Date类型转换成String类型
                map.put("phone", "15070938483");
                map.put("time", datetime);
                map.put("name", "刘献盎");
                HttpUtlis.getRequest(url, map, "utf-8", new OnResponseListner() {
                    @Override
                    public void onSucess(String response) {
                        /*   spinner.setText(response);*/
                        sign=response;

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
                publishProgress(100);
                Thread.sleep(100);
                return sign;

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
            if (result != null && result.length() > 0) {
                System.out.println(result);



                myview.setBackgroundColor(Color.parseColor("#ffffff"));
                Toast.makeText(myview.getContext(),"签到成功", Toast.LENGTH_SHORT).show();

            } else {

            }
        }
    }
}
