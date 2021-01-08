package com.lxa.demo.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.model.ListOfGoodsModel;

import java.util.ArrayList;

/**
 * 商品列表LIstView
 * 
 * @author 
 *
 */
public class ListOfGoodsAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<ListOfGoodsModel> list;

	public ListOfGoodsAdapter(Context mContext, ArrayList<ListOfGoodsModel> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHeol heol;
		if (arg1==null) {
			heol = new ViewHeol();
			arg1 = LayoutInflater.from(mContext).inflate(R.layout.activity_listofgoogds_listview, null);
			heol.txt_biaoti= (TextView) arg1.findViewById(R.id.txt_biaoti);
			heol.txt_jiage = (TextView) arg1.findViewById(R.id.txt_jiage);
			heol.txt_yuanjia = (TextView) arg1.findViewById(R.id.txt_yuanjia);
			arg1.setTag(heol);
		}else {
			heol = (ViewHeol) arg1.getTag();
		}
		heol.txt_biaoti.setText(list.get(arg0).getBiaoti());
		heol.txt_jiage.setText(list.get(arg0).getJiage());
		heol.txt_yuanjia.setText("原价"+list.get(arg0).getYuanjia());
		
		return arg1;
	}

	private class ViewHeol {
		TextView txt_biaoti, txt_jiage, txt_yuanjia;
	}

}
