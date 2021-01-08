package com.lxa.demo.mydemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.lxa.demo.mydemo.R;

import java.util.ArrayList;
import java.util.Random;

public class KPActivity extends Activity {
	private Button btnplayagian;// ����һ��
	private ImageView puke1, puke2, puke3;// �����˿�
	private Drawable bg;// �˿˱��棨�����ж��Ƿ��ƣ�
	private int[] pks = { R.drawable.p01, R.drawable.p02, R.drawable.p03 };// ���3���˿�
	private ArrayList<Integer> ps = new ArrayList<Integer>();// ���ϴ�����˿ˣ�������У�

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kp);
		puke1 = (ImageView) findViewById(R.id.puke1);
		puke2 = (ImageView) findViewById(R.id.puke2);
		puke3 = (ImageView) findViewById(R.id.puke3);
		btnplayagian = (Button) findViewById(R.id.btnplayagian);
		puke1.setOnClickListener(l);
		puke2.setOnClickListener(l);
		puke3.setOnClickListener(l);
		btnplayagian.setOnClickListener(l);
	}

	protected void onResume() {
		randomPuke();
		super.onResume();
	}

	OnClickListener l = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.puke1:
				showPuke(0);
				// �����ûѡ��������ԻҰ�Ч������,����͸���ȣ�ȡֵ��ΧΪ0~255����ֵԽСԽ͸����
				puke2.setAlpha(100);
				puke3.setAlpha(100);
				break;
			case R.id.puke2:
				showPuke(1);
				puke1.setAlpha(100);
				puke3.setAlpha(100);
				break;
			case R.id.puke3:
				showPuke(2);
				puke1.setAlpha(100);
				puke2.setAlpha(100);
				break;
			case R.id.btnplayagian:
				randomPuke();
				break;
			}
		}
	};

	/** ϴ�� */
	public void randomPuke() {
		// �����Ʒ�������
		puke1.setImageResource(R.drawable.p04);
		puke2.setImageResource(R.drawable.p04);
		puke3.setImageResource(R.drawable.p04);
		bg = puke1.getDrawable();
		// ʵ������գ����ƣ�
		ps = new ArrayList<Integer>();
		// �����䣨���ƣ�
		do {
			int item = new Random().nextInt(3);// �������0,1��2��������һ����
			if (!ps.contains(pks[item])) {// �������û�������Ʋŷ�������
				ps.add(pks[item]);
			}
		} while (ps.size() < 3);// ����3���ƾͲ�������
	}


	@SuppressLint("WrongConstant")
	public void showPuke(int p) {
		// ����ƻ�û�����ܿ���
		if (puke1.getDrawable() == bg) {
			puke1.setImageResource((Integer) ps.get(0));
			puke2.setImageResource((Integer) ps.get(1));
			puke3.setImageResource((Integer) ps.get(2));
			Toast.makeText(
					KPActivity.this,
					R.drawable.p01 == (Integer) ps.get(p) ? "��ϲ��¶���" : "�ź���´���",
					1000).show();
		}
	}
}