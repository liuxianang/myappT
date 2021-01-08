package com.lxa.demo.mydemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.lxa.demo.mydemo.R;
import com.lxa.demo.mydemo.alipay.AuthResult;
import com.lxa.demo.mydemo.alipay.OrderInfoUtil2_0;
import com.lxa.demo.mydemo.alipay.PayResult;

import java.util.Map;


public class payActivity extends Activity {

	private Button mReturnButton1;


	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2018111162085924";

	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088721643971561";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIFWSV0dHsGRDm/Xh4mAl3DGq8R4fnqDio84+u8ic0LNfnbvtqDsX4HJvETAyI6yAZpjTCqeA9iIqzrnFm8HLBKk9xHKz5kL680+PxUq+YSuTI6SKR+V1pIfyVjNJauDApuD31gNjHNcxW1tqW3+dM1p0g226bWTsvxUC7ebKHTlATp/4lWH8dA86G7jE8JbhDA/nnl2ndinxPeMd8sNaMQvucog2wi2z4y6R9nL/g6D+Rd8gGKy4Ynk3Iy0J7s7zQdNinWZLH7Le6agk2O45b8FqJrMf2oBv0iV6Na6AY1xlHhBOesdv+Fbm4jUdtwuJjn6m+D/cWQ4ZrNt7fgxm1AgMBAAECggEBAIaC81NRctpR464W+5W/SKWzQFKBmFliG21iCNepTjfiaefaEcW0Vt09hm7KXabyw2ra9R9liy5zDCROA0kcH1nDcxLtH1DMQZ66qMSFPS2aNALp0OboCSdzxNZZjNWWfDKf/W9+Br6uaUh8OwoxTtO2eH4tZ0ejDopIBwPChgY1rHrOpAnFcTtAj+GfoGQ2sETns0h5w2Ylh7mtXMsC5gyWQT/GdSDxSi5Kbx4iTu4VCRdQqTatTRiC/ICw896fLtgh2C83/1n/Ysny3pQNvY7T4k4B/pJCWtR2YZTFS9B8cqpRZXpTVDxqVYZ9dkKaIJjJZi69ahRqbFCPtT3BeMECgYEA5xQyMnNVb7p0ke+IqvpYUGJgz2jK/453w/M9xiV1DcE7HyK8Ec8pCwT4WR+GwlffLPaVaxhLhyPfX44vpBKj8MzWCkB45yFdmtRI6vk34Py0l8kI0wFEzM1w+D/MoUdxBnUtM2x4Qnu9LJJ8B2E/S4wkJ8D+9rUhB3O8YWQwkBECgYEA3al0O8xzxp4QnybniyCIFz/Ca0NA2zGUeoHpDYFrM904pW8lIb0UHDIBLTtF+9wQ7WSrPhjogLIZpG7bq1XORrjRtZFhK3e2HQxOPRGXahuNK1uLMger5JpTZGt1SPW2Tu52FyKSq2+KSGgwkFeg5UtLkQB0ykkiiceqJewZE2UCgYEA40rDQUqevAJsDOvOw1bxrUfHL9xE6HBrL4u/tHo9k+UAUVTqJp5dIaCm44sWE205cVGukSPZjrjjS2thQwKuzPm+g2D9S2NPXi729mFi7GPFAxPUITC7SrO9LgIbNnw+GXsClfVYqRRrdD81zlCCYL4dBhlY8LQjI+BcaHOTLPECgYAc06vNFxK7ctUAjaOxIULxmxYisvVdTD5eDEUNv5X8gbmbMjAnChrya8JhaoL4dS8yYFQw2DZsfjz9eHlS9+7CnciQD18OsxvY/T+zi3UglIKwb9BTalH9geB0f9EZ8vKJo1NyQMnIR9I5BmvCadL/3uHt8pw9rnMybef/1IuSTQKBgDGg+sGFnkMU1pdfiB9TjgYKXik48xH/JxzQiHoEfwwIUJ0mm4+jSK1ZhxEjyBqxuWQWPKOiBQ8lWeP0nulXzvx+a81bjLnx/z/PrM5/tZkoRjQNp7KbyB0xcdEAp4CaX1mwe8Hml4f0MRLwI3XvtCyWG9ubr8gxL85cIkVoAolz";
	public static final String RSA_PRIVATE = "";

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);


		mReturnButton1 = (Button)findViewById(R.id.payV2);




		setContentView(R.layout.pay);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SDK_PAY_FLAG: {
					@SuppressWarnings("unchecked")
					PayResult payResult = new PayResult((Map<String, String>) msg.obj);
					/**
					 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
					 */
					String resultInfo = payResult.getResult();// 同步返回需要验证的信息
					String resultStatus = payResult.getResultStatus();
					// 判断resultStatus 为9000则代表支付成功
					if (TextUtils.equals(resultStatus, "9000")) {
						// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
						Toast.makeText(payActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
					} else {
						// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
						Toast.makeText(payActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
					}
					break;
				}
				case SDK_AUTH_FLAG: {
					@SuppressWarnings("unchecked")
					AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
					String resultStatus = authResult.getResultStatus();

					// 判断resultStatus 为“9000”且result_code
					// 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
					if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
						// 获取alipay_open_id，调支付时作为参数extern_token 的value
						// 传入，则支付账户为该授权账户
						Toast.makeText(payActivity.this,
								"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
								.show();
					} else {
						// 其他状态值则为授权失败
						Toast.makeText(payActivity.this,
								"授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

					}
					break;
				}
				default:
					break;
			}
		};
	};

	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}

		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
		 *
		 * orderInfo的获取必须来自服务端；
		 */
		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		final String orderInfo = orderParam + "&" + sign;

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(payActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();

	}




}
