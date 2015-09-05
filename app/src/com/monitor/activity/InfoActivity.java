package com.monitor.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.xmlpull.v1.XmlPullParserException;

import com.monitor.R;
import com.monitor.adapter.CarAutoCompleteAdaper;
import com.monitor.adapter.MLFragmentAdapter;
import com.monitor.dao.CarDao;
import com.monitor.dao.SqliteCarDao;
import com.monitor.domain.CarInfo;
import com.monitor.evenbus.info.GetLastLocationInfoEven;
import com.monitor.evenbus.info.GetLastMonitorInfoEven;
import com.monitor.evenbus.info.UpdateCarInfo;
import com.monitor.util.HttpUtil;
import com.monitor.util.XMlParserUtils;

import de.greenrobot.event.EventBus;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class InfoActivity extends FragmentActivity {
	
	//控件变量
	private ViewPager pager;     
	private List<android.support.v4.app.Fragment> fragList;
	private List<String> titleList;  //标题
	private PagerTabStrip tab;       //界面切换
	CarAutoCompleteAdaper autoAdapter;
	
	private Button bntCarConfirm;    //确认车辆查询按钮
	private AutoCompleteTextView autoCptText;
	List<CarInfo> carList = null;
	
	Thread getCarInfo;
	
	private OnClickListener carConfirmLst = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
			//获取输入信息
			String inputTxt = autoCptText.getText().toString();
			
			//分割输入信息
			String[] carInfo = inputTxt.trim().split(":");
			
			CarDao dao = new SqliteCarDao(getApplicationContext());
			
			//根据司机名查找数据库中的车辆信息
			CarInfo car = dao.findByDriverNameAndNumber(carInfo[0], carInfo[1]);
			if(null != car){
				//传递车辆的ID信息给MonitorInfoFragment，让它不断地从网上获取最新的监控信息
				EventBus.getDefault().post(new GetLastMonitorInfoEven(car.getCar_id()));
				
				//传递车辆的ID信息给LocationInfoFragment3，让它不断地从网上获取最新的地理位置信息
				EventBus.getDefault().post(new GetLastLocationInfoEven(car.getCar_id()));
			}
					
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_info);
		
		//获取控件
		tab = (PagerTabStrip) findViewById(R.id.tab);
		pager = (ViewPager) findViewById(R.id.pager);
		autoCptText = (AutoCompleteTextView) findViewById(R.id.car_auto_text);
		bntCarConfirm = (Button) findViewById(R.id.car_info_comfirm);
			
		//fragment标题设置
		titleList = new ArrayList<String>();
		titleList.add("监测信息");
		titleList.add("地理位置信息");
		
		//标题美化		
		tab.setDrawFullUnderline(false);
		tab.setBackgroundColor(Color.WHITE);
		tab.setTabIndicatorColor(Color.BLUE);
		
		//添加fragment	
		fragList = new ArrayList<android.support.v4.app.Fragment>();
		fragList.add(new MonitorInfoFragment());
		fragList.add(new LocationInfoFragment3());
		
		//获取监测平台数据中的车辆信息
		GetAllCarInfo();
			
		//设置autoCptText适配器
		autoAdapter = new CarAutoCompleteAdaper(this, null, 1);
		autoCptText.setAdapter(autoAdapter);
		
		//设置pager的适配器
		MLFragmentAdapter adapter = new MLFragmentAdapter(getSupportFragmentManager(), fragList, titleList);	
		pager.setAdapter(adapter);
		
		//设置确认按键单击事件监听
		bntCarConfirm.setOnClickListener(carConfirmLst);
		
		//注册接受消息
		EventBus.getDefault().register(this);
	}


	
	private void GetAllCarInfo() {

		GetAllCarInfoRunnable getAllCarInfoRunnable = new GetAllCarInfoRunnable();
		getCarInfo = new Thread(getAllCarInfoRunnable);
		getCarInfo.start();
		try {
			getCarInfo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	class GetAllCarInfoRunnable implements Runnable{
				
		public GetAllCarInfoRunnable(){
			
		}
		
		@Override
		public void run() {
		String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/CarServlet";
		
			
			try {
				//获取所有的车辆信息s
				String responseStr = HttpUtil.sendHttpClientPostRequest(path, null, "UTF-8");
				
				//将保存车辆信息的XML字符串进行解析
				carList = XMlParserUtils.XMLCarParserStoCarList(responseStr);
				
				//把车辆信息导入本地数据库
				CarDao dao = new SqliteCarDao(getApplicationContext());
				
				for (CarInfo tmp : carList){
					dao.save(tmp);
				}
				
				//更新适配器
				EventBus.getDefault().post(new UpdateCarInfo());
					
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}

			
	}
		
	//更新车辆信息适配器
	public void onEventMainThread(UpdateCarInfo even) {
		autoCptText.setAdapter(autoAdapter);
		autoAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
