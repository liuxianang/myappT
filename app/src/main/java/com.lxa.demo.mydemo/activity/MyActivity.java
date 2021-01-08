package com.lxa.demo.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.util.SharedPreferencesUtils;


/**
 * 我的
 * 
 * @author 
 * 
 */
public class MyActivity extends Fragment{
    Button lond;
    ImageView shezhi;
    @InjectView(R.id.tvUserName)
    TextView tvUserName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.activity_my, null);
        tvUserName=view.findViewById(R.id.tvUserName);
        tvUserName.setText("" + getLocalName());
        TextView tvLoginOut = view.findViewById(R.id.tvLoginOut);
        tvLoginOut.setOnClickListener(new OnClickListener(){
            /**
             * 返回登录界面，不消除用户和密码
             */

            @Override
            public void onClick(View v) {
                //不用自动登录就可
                //获取SharedPreferences对象，使用自定义类的方法来获取对象
                SharedPreferencesUtils helper = new SharedPreferencesUtils(getActivity(), "setting");
                //创建记住密码和自动登录是默认不选,密码为空
                helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", false));
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    /**
     * 获得保存在本地的用户名
     */
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        SharedPreferencesUtils helper = new SharedPreferencesUtils(getActivity(),"setting");
        String name = helper.getString("loginname");
        return name;
    }

}
