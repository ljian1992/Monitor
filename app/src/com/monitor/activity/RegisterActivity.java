package com.monitor.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import com.monitor.R;
import com.monitor.domain.UserInfo;
import com.monitor.evenbus.info.RegisterResult;
import com.monitor.exception.UserMsgException;
import com.monitor.util.HttpUtil;
import com.monitor.util.StatusUtil;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	public static final int REGISTER_SUCCESS = 2;
	//控件变量
	private EditText username;
	private EditText nickname;
	private EditText password;
	private EditText password2;
	private RadioGroup gender;
	private EditText phone;
	private EditText email;
	private Button comfirm;
	private Button cannel;
	
	
	private String responseStr;
	
	private OnClickListener comfirmListenner = new OnClickListener(){
		public void onClick(View arg0) {		
			//构建用户类
			UserInfo user = new UserInfo();
			user.setUsername(username.getText().toString());
			user.setNickname(nickname.getText().toString());
			user.setPassword(password.getText().toString());
			user.setPassword2(password2.getText().toString());
			user.setPhone(phone.getText().toString());
			user.setEmail(email.getText().toString());
			
			switch (gender.getCheckedRadioButtonId()) {
			case R.id.register_gender_m:
				user.setGender("M");
				break;
			case R.id.register_gender_f:
				user.setGender("F");
				break;
			default:
				break;
			}
			
			//用户输入合法性检查
			try {
				user.checkValue();
				//注册
				Register(user);
			} catch (UserMsgException e) {
				// TODO Auto-generated catch block
			
				checkUserMsgException(e);
			}								
		}				
	};
	
	private OnClickListener cannelListenner = new OnClickListener(){

		@Override
		public void onClick(View arg0) {			
			setResult(REGISTER_SUCCESS,null);
			finish();		
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		//获得控件
		username = (EditText) findViewById(R.id.ET_rgst_name);
		nickname = (EditText) findViewById(R.id.ET_rgst_nickname);
		password = (EditText) findViewById(R.id.ET_rgst_password);
		password2 = (EditText) findViewById(R.id.ET_rgst_password2);
		gender = (RadioGroup) findViewById(R.id.register_gender_group);
		phone = (EditText) findViewById(R.id.ET_rgst_phone);
		email = (EditText) findViewById(R.id.ET_rgst_email);
		comfirm = (Button) findViewById(R.id.btn_comfirm);
		cannel = (Button) findViewById(R.id.btn_cancel);
		
		//为控件设置监听器
		comfirm.setOnClickListener(comfirmListenner);
		cannel.setOnClickListener(cannelListenner );
		
		//注册接受消息
		EventBus.getDefault().register(this);
	}
	
	/**
	 * 检测 用户数据检验返回的错误信息，并做出相应的应对
	 * @param e
	 */
	private void checkUserMsgException(UserMsgException e) {
		if("用户名不能为空".equals(e.getMessage())){
			username.requestFocus();
			Toast.makeText(getApplicationContext(), "用户名不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("昵称不能为空".equals(e.getMessage())){
			nickname.requestFocus();
			Toast.makeText(getApplicationContext(), "昵称不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("密码不能为空".equals(e.getMessage())){
			password.requestFocus();
			Toast.makeText(getApplicationContext(), "密码不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("确认密码不能为空".equals(e.getMessage())){
			password2.requestFocus();
			Toast.makeText(getApplicationContext(), "确认密码不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("手机号码不能为空".equals(e.getMessage())){
			phone.requestFocus();
			Toast.makeText(getApplicationContext(), "手机号码不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("邮箱不能为空".equals(e.getMessage())){
			email.requestFocus();
			Toast.makeText(getApplicationContext(), "邮箱不能为空",
					Toast.LENGTH_SHORT).show();
		}else if("两次密码不一致".equals(e.getMessage())){
			password.setText("");
			password2.setText("");
			password.requestFocus();
			Toast.makeText(getApplicationContext(), "两次密码不一致",
					Toast.LENGTH_SHORT).show();
		}else if("手机号码长度不对".equals(e.getMessage())){
			phone.requestFocus();
			phone.setText("");
			Toast.makeText(getApplicationContext(), "手机号码长度不对",
					Toast.LENGTH_SHORT).show();
		}else if("邮箱格式不正确".equals(e.getMessage())){
			email.requestFocus();
			email.setText("");
			Toast.makeText(getApplicationContext(), "邮箱格式不正确",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 登陆方法
	 * @param username
	 * @param password
	 */
	private void Register(UserInfo user) {

		RegisterRunnable registerRunnable = new RegisterRunnable(user);
		
		new Thread(registerRunnable).start();

	}
	
	
	/**
	 * 注册结果提示
	 * 
	 * @param even
	 */
	public void onEventMainThread(RegisterResult even) {

		if (null == responseStr) {
			Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT)
					.show();
		} else {
			switch (Integer.valueOf(responseStr)) {
			case StatusUtil.REGISTER_SUCCESS:
				// 返回登陆界面
				Toast.makeText(getApplicationContext(), "注册成功",
						Toast.LENGTH_SHORT).show();
				setResult(REGISTER_SUCCESS, null);
				finish();
				break;

			case StatusUtil.REGISTER_NAME_ERROR:
				username.requestFocus();
				Toast.makeText(getApplicationContext(), "用户名已存在",
						Toast.LENGTH_SHORT).show();
				break;

			}
		}
	}
	

	
				
	/**
	 * 登陆线程	
	 */
		class RegisterRunnable implements Runnable {

			private UserInfo user;

			public RegisterRunnable(UserInfo user) {
				this.setUser(user);
			}

			@Override
			public void run() {

				String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/RegistServlet";
				Map<String, String> params = new HashMap<String, String>();

				// 构建实体内容
				params.put("username", user.getUsername());
				params.put("nickname", user.getNickname());
				params.put("password", user.getPassword());
				params.put("password2", user.getPassword2());
				params.put("gender", user.getGender());
				params.put("phone", user.getPhone());
				params.put("email", user.getEmail());

				try {
					// 注册
					responseStr = HttpUtil.sendHttpClientPostRequest(path,params, "UTF-8");

					EventBus.getDefault().post(new RegisterResult());

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			public void setUser(UserInfo user) {
				this.user = user;
			}
		}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
