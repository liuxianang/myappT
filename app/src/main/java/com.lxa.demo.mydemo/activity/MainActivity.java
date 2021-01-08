package com.lxa.demo.mydemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.mytools.IBtnCallListener;


/**
 * 底部
 *
 * @author
 *
 */
public class MainActivity extends FragmentActivity implements OnClickListener, IBtnCallListener {

    private RelativeLayout btn_home, btn_category, btn_shoppingcart, btn_my;
    /**
     * 首页
     */
    private HomeActivity home;
    /**
     * 类目
     */
    private CategoryActivity category;
    /**
     * 购物车
     *
     */
    private ShoppingcartActivity shoppingcart;
    /**
     * 我的
     */
    private MyActivity my;
    // 界面底部的菜单按钮id
    private int[] bt_menu_id = { R.id.btn_home, R.id.btn_category, R.id.btn_shoppingcart, R.id.btn_my };
    private ImageView img_shouye, img_leimu, img_gouwuche, img_wode;
    private TextView txt_shouye, txt_leimu, txt_gouwuche, txt_wode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btn_home = (RelativeLayout) findViewById(R.id.btn_home);
        btn_category = (RelativeLayout) findViewById(R.id.btn_category);
        btn_shoppingcart = (RelativeLayout) findViewById(R.id.btn_shoppingcart);
        btn_my = (RelativeLayout) findViewById(R.id.btn_my);

        img_shouye = (ImageView) findViewById(R.id.img_shouye);
        img_leimu = (ImageView) findViewById(R.id.img_leimu);
        img_gouwuche = (ImageView) findViewById(R.id.img_gouwuche);
        img_wode = (ImageView) findViewById(R.id.img_wode);

        txt_shouye = (TextView) findViewById(R.id.txt_shouye);
        txt_leimu = (TextView) findViewById(R.id.txt_leimu);
        txt_gouwuche = (TextView) findViewById(R.id.txt_gouwuche);
        txt_wode = (TextView) findViewById(R.id.txt_wode);

        btn_home.setOnClickListener(this);
        btn_category.setOnClickListener(this);
        btn_shoppingcart.setOnClickListener(this);
        btn_my.setOnClickListener(this);
        // 初始化默认显示的界面
        if (home == null) {
            home = new HomeActivity();
            addFragment(home);
            showFragment(home);
        } else {
            showFragment(home);
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            // 首页
            case R.id.btn_home:
                if (home == null) {
                    home = new HomeActivity();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(home);
                    showFragment(home);
                } else {
                    if (home.isHidden()) {
                        showFragment(home);
                    }
                }
                img_shouye.setImageResource(R.drawable.shouye2);
                img_leimu.setImageResource(R.drawable.leimu);
                img_gouwuche.setImageResource(R.drawable.gouwuche2);
                img_wode.setImageResource(R.drawable.wode);
                BackgroundColors(R.color.hongse, R.color.feiheise, R.color.feiheise, R.color.feiheise);
                textViewColors(R.color.white, R.color.danhuise, R.color.danhuise, R.color.danhuise);
                break;
            // 类目
            case R.id.btn_category:
                if (category == null) {
                    category = new CategoryActivity();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(category);
                    showFragment(category);
                } else {
                    if (category.isHidden()) {
                        showFragment(category);
                    }
                }
                img_shouye.setImageResource(R.drawable.shouye);
                img_leimu.setImageResource(R.drawable.liemu2);
                img_gouwuche.setImageResource(R.drawable.gouwuche2);
                img_wode.setImageResource(R.drawable.wode);
                BackgroundColors(R.color.feiheise, R.color.hongse, R.color.feiheise, R.color.feiheise);
                textViewColors(R.color.danhuise, R.color.white, R.color.danhuise, R.color.danhuise);
                break;
            // 购物车
            case R.id.btn_shoppingcart:
                if (shoppingcart == null) {
                    shoppingcart = new ShoppingcartActivity();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(shoppingcart);
                    showFragment(shoppingcart);
                } else {
                    if (shoppingcart.isHidden()) {
                        showFragment(shoppingcart);
                    }
                }
                img_shouye.setImageResource(R.drawable.shouye);
                img_leimu.setImageResource(R.drawable.leimu);
                img_gouwuche.setImageResource(R.drawable.gouwuche);
                img_wode.setImageResource(R.drawable.wode);
                BackgroundColors(R.color.feiheise, R.color.feiheise, R.color.hongse, R.color.feiheise);
                textViewColors(R.color.danhuise, R.color.danhuise, R.color.white, R.color.danhuise);
                break;
            // 我的
            case R.id.btn_my:
                if (my == null) {
                    my = new MyActivity();
                    // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
                    addFragment(my);
                    showFragment(my);
                } else {
                    if (my.isHidden()) {
                        showFragment(my);
                    }
                }
                img_shouye.setImageResource(R.drawable.shouye);
                img_leimu.setImageResource(R.drawable.leimu);
                img_gouwuche.setImageResource(R.drawable.gouwuche2);
                img_wode.setImageResource(R.drawable.wode2);
                BackgroundColors(R.color.feiheise, R.color.feiheise, R.color.feiheise, R.color.hongse);
                textViewColors(R.color.danhuise, R.color.danhuise, R.color.danhuise, R.color.white);
                break;
        }
    }

    private void BackgroundColors(int homeColor, int categoryColor, int shoppingcartColor, int myColor) {
        btn_home.setBackgroundColor(getResources().getColor(homeColor));
        btn_category.setBackgroundColor(getResources().getColor(categoryColor));
        btn_shoppingcart.setBackgroundColor(getResources().getColor(shoppingcartColor));
        btn_my.setBackgroundColor(getResources().getColor(myColor));
    }

    private void textViewColors(int s, int l, int g, int w) {
        txt_shouye.setTextColor(getResources().getColor(s));
        txt_leimu.setTextColor(getResources().getColor(l));
        txt_gouwuche.setTextColor(getResources().getColor(g));
        txt_wode.setTextColor(getResources().getColor(w));
    }

    /** 添加Fragment **/
    public void addFragment(Fragment fragment) {

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.show_layout, fragment);
        ft.commit();
    }

    /** 删除Fragment **/
    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    /** 显示Fragment **/
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        // 设置Fragment的切换动画
        ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);

        // 判断页面是否已经创建，如果已经创建，那么就隐藏掉
        if (home != null) {
            ft.hide(home);
        }
        if (category != null) {
            ft.hide(category);
        }
        if (shoppingcart != null) {
            ft.hide(shoppingcart);
        }
        if (my != null) {
            ft.hide(my);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();

    }

    /** 返回按钮的监听 */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /** Fragment的回调函数 */
    @SuppressWarnings("unused")
    private IBtnCallListener btnCallListener;

    @Override
    public void onAttachFragment(Fragment fragment) {
        try {
            btnCallListener = (IBtnCallListener) fragment;
        } catch (Exception e) {
        }

        super.onAttachFragment(fragment);
    }

    @Override
    public void transferMsg() {
        if (home == null) {
            home = new HomeActivity();
            addFragment(home);
            showFragment(home);
        } else {
            showFragment(home);
        }
        Log.i("junjie", "由Fragment中传送来的消息");
    }
}
