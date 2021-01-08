package com.lxa.demo.mydemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lxa.demo.mydemo.activity.util.CoreConst;
import com.lxa.demo.mydemo.adapter.FruitAdapter;
import com.lxa.demo.mydemo.adapter.RegistrationAdapter;
import com.lxa.demo.mydemo.lib.CycleViewPager;
import com.lxa.demo.mydemo.model.Fruit;
import com.lxa.demo.mydemo.phone.bean.ADInfo;
import com.lxa.demo.mydemo.phone.utils.ViewFactory;
import com.lxa.demo.mydemo.util.HttpUtlis;
import com.lxa.demo.mydemo.util.OnResponseListner;
import com.lxa.demo.mydemo.view.HorizontalListViewAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.lxa.demo.mydemo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * 首页
 * 
 * @author 
 *
 */
public class HomeActivity extends Fragment {
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    TextView txt_quanqiu;
    TextView txt_quanqiu2;
    TextView txt_quanqiu3;
    TextView txt_quanqiu4;
    TextView txt_quanqiu5;
    TextView txt_quanqiu6;
    TextView txt_quanqiu7;
    private CycleViewPager cycleViewPager;
    private String[] imageUrls = { "http://114.115.178.160:8004/files/96/31/5e335308-8f9d-431c-89d1-404fc49d6a81",
            "http://114.115.178.160:8004/files/96/31/5e335308-8f9d-431c-89d1-404fc49d6a81",
            "http://114.115.178.160:8004/files/96/31/5e335308-8f9d-431c-89d1-404fc49d6a81",
            "http://114.115.178.160:8004/files/96/31/5e335308-8f9d-431c-89d1-404fc49d6a81",
            "http://114.115.178.160:8004/files/96/31/5e335308-8f9d-431c-89d1-404fc49d6a81" };
    private Context context;
    private HorizontalListViewAdapter hListViewAdapter;
    private String[] titles = { "你好我是娜娜娜难你好我", "南怀瑾你好我是军校", "闭你好我是娜娜娜难你好我是娜娜娜难你好我是娜娜娜难你好关", "南怀瑾", "南公庄严照", "怀师法相" };
    private final int[] ids = { R.drawable.nanhuaijin_miss, R.drawable.nanhuaijin_school, R.drawable.nanhuaijin_biguan,
            R.drawable.nanhuaijin, R.drawable.nanhuaijin_zhuangyan, R.drawable.nanhuaijin_faxiang };
    private String code;
    private MyTask myTask;
    private String path = CoreConst.serverhost+"/api-app/app/loadimage";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_home, null);
        myTask = new MyTask();
        myTask.execute(path);
        initView(view);
        txt_quanqiu=view.findViewById(R.id.txt_quanqiu);
        txt_quanqiu.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SigndateActivity.class));
            }
        });
        txt_quanqiu2=view.findViewById(R.id.txt_quanqiu2);
        txt_quanqiu2.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), payActivity.class));
            }
        });
        txt_quanqiu3=view.findViewById(R.id.txt_quanqiu3);
        txt_quanqiu3.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), shopActivity.class));
            }
        });
        txt_quanqiu4=view.findViewById(R.id.txt_quanqiu4);
        txt_quanqiu4.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), DZPActivity.class));
            }
        });
        txt_quanqiu5=view.findViewById(R.id.txt_quanqiu5);
        txt_quanqiu5.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), KPActivity.class));
            }
        });

        txt_quanqiu7=view.findViewById(R.id.txt_quanqiu7);
        txt_quanqiu7.setOnClickListener(new View.OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), QHBActivity.class));
            }
        });
        return view;
    }

    private void initView(View view) {
        context = view.getContext();
        configImageLoader();
       /* initialize();*/
        initUI(view);
    }

    public void initUI(View view) {

    }

    @SuppressLint("NewApi")
    private void initialize() {
        cycleViewPager = (CycleViewPager) ((Activity) context).getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        for (int i = 0; i < imageUrls.length; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>++++="+imageUrls[i]);
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(context, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(context, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(context, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        // 设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        // 设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
            }

        }

    };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context.getApplicationContext())
                .defaultDisplayImageOptions(options).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    public class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO 后台运行, 执行耗时的操作

            try {

                String url = params[0];

                Map<String, String> map = new HashMap<>();

                map.put("userName", "");
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

                // 控件 spinner
                // 数据源 result
                // 适配器
                try {
                    Fruit fruit;
                    JSONObject jsonObject;
                    String newsUrl = null;
                    String newsTitle = null;
                    String newsContent = null;
                    System.out.println(">>>>>>>>>>>bbbbb"+result);
                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    System.out.println(">>>>>>>>>>>bbbbb"+jsonArray);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        newsUrl = jsonObject.getString("url");//图片网址

                        imageUrls[i] = newsUrl;
                      /*  fruitList.add(fruit);*/
                    } } catch (JSONException e) {
                    e.printStackTrace();
                }
                initialize();//初始化动动态图片
            } else {

            }
        }

    }
}
