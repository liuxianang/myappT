package com.lxa.demo.mydemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.activity.util.CoreConst;
import com.lxa.demo.mydemo.adapter.FruitAdapter;
import com.lxa.demo.mydemo.model.Fruit;
import com.lxa.demo.mydemo.util.HttpUtlis;
import com.lxa.demo.mydemo.util.OnResponseListner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目
 * 
 * @author 
 *
 */
public class CategoryActivity extends  Fragment{
    private MyTask2 myTask;
    private SharedPreferences sp ;
    private String code;
    ListView listView;

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private String path = CoreConst.serverhost+"/api-app/app/home";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.activity_category, null);

        myTask = new MyTask2();
        myTask.execute(path);



        FruitAdapter adapter = new FruitAdapter(getActivity(), R.layout.fruit_item, fruitList);
         listView =view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i);
                sp = getActivity().getSharedPreferences("setting", 0);
                final SharedPreferences.Editor editor = sp.edit();
                editor.putString("name","ATAAW");
                editor.putString("URL",fruit.getName());
                editor.commit();
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
  /*  private void initFruits() {

        Fruit apple = new Fruit("Apple", R.drawable.meishi);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.meishi);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.meishi);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.meishi);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.meishi);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.meishi);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.meishi);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.meishi);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.meishi);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.meishi);
        fruitList.add(mango);
    }*/
    public class MyTask2 extends AsyncTask<String, Integer, String> {

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

                    jsonObject = new JSONObject(result);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        newsUrl = jsonObject.getString("content");//图片网址

                        fruit = new Fruit(newsUrl,  R.drawable.meishi);
                        fruitList.add(fruit);
                    } } catch (JSONException e) {
                    e.printStackTrace();
                }
               FruitAdapter adapter = new FruitAdapter(getActivity(), R.layout.fruit_item, fruitList);
                listView.setAdapter(adapter);
            } else {

            }
        }

    }
}
