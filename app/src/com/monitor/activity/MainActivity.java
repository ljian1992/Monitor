package com.monitor.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import com.monitor.R;
import com.monitor.evenbus.info.LoginSkip;
import com.monitor.util.HttpUtil;
import com.monitor.util.StatusUtil;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity{
		
	//¿Ø¼þ±äÁ¿
	private EditText loginUsername;
	private EditText loginPassword;
	private Button login;
	private Button register;
	
	//µÇÂ½·µ»ØÖµ
	private String responseStr;	
	
	/**
	 * µÇÂ½°´¼üµ¥»÷ÊÂ¼þ¼àÌýÆ÷
	 */
	private OnClickListener loginListenner = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
						
			String username = loginUsername.getText().toString();
			String password = loginPassword.getText().toString();
			
			Login(username, password);
		}
	};
	
	
	/**
	 * µ¥»÷×¢²á°´Å¥¼àÌýÆ÷
	 */
	private OnClickListener registerListenner = new OnClickListener(){
		
		public void onClick(View arg0) {
			
			Intent intent = new Intent(MainActivity.this, RegisterActivity.class);						
			startActivity(intent);	
		}
		
	};
			
			
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
      //»ñµÃ¿Ø¼þ
		loginUsername = (EditText) findViewById(R.id.LoginUsername);
		loginPassword = (EditText) findViewById(R.id.LoginPasswrod);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
        
      //Îª¿Õ¼äÉèÖÃ¼àÌýÆ÷
        login.setOnClickListener(loginListenner );
        register.setOnClickListener(registerListenner);
        
        EventBus.getDefault().register(this);
    }
	
 
	/**
	 * µÇÂ½·½·¨
	 * @param username
	 * @param password
	 */
	private void Login(String username, String password) {

		LoginRunnable loginRunnable = new LoginRunnable(username, password);
		new Thread(loginRunnable).start();

	}
	
	/**
	 * µÇÂ½
	 * @param even
	 */
	public void onEventMainThread(LoginSkip even) {
	
		 if(null == responseStr){
				Toast.makeText(getApplicationContext(), "ÍøÂç´íÎó",
						Toast.LENGTH_SHORT).show();
			}else if(  StatusUtil.LOGIN_SUCCESS ==  Integer.parseInt(responseStr)){  //µÇÂ½³É¹¦
				Intent intent = new Intent(MainActivity.this, InfoActivity.class);						
				startActivity(intent);	 
			}else if(StatusUtil.LOGIN_NAME_OR_PSW_ERROR ==  Integer.parseInt(responseStr)){
				Toast.makeText(getApplicationContext(), "ÓÃ»§ÃûorÃÜÂë´íÎó",
						Toast.LENGTH_SHORT).show();
			}
	}
	
			
	/**
	 * µÇÂ½Ïß³Ì	
	 */
	class LoginRunnable implements Runnable{	
		//µÇÂ½×´Ì¬
		private String username;
		private String password;
	
		public LoginRunnable(String username, String password){
			this.setUsername(username);
			this.setPassword(password);
		}
		
		@Override
		public void run() {
			
			String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/LoginServlet";
			Map<String, String> params = new HashMap<String, String>();
			
			params.put("username", username);
			params.put("password", password);
						
				try {
					//µÇÂ½
					responseStr = HttpUtil.sendHttpClientPostRequest(path, params, "UTF-8");
								
					EventBus.getDefault().post(new LoginSkip());
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();	
		EventBus.getDefault().unregister(this);
	}
}
	

	


