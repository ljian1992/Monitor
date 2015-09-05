package com.monitor.activity;



import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.client.ClientProtocolException;
import org.xmlpull.v1.XmlPullParserException;

import com.monitor.R;
import com.monitor.adapter.MonitorInfoAdapter;
import com.monitor.domain.MonitorInfo;
import com.monitor.evenbus.info.GetLastMonitorInfoEven;
import com.monitor.evenbus.info.UpdateMonitorFragmentUI;
import com.monitor.util.HttpUtil;
import com.monitor.util.StatusUtil;
import com.monitor.util.XMlParserUtils;

import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MonitorInfoFragment extends Fragment {

	private final int CARID_DEFAULT = -1; 
	
	//控件变量
	private ListView listView;
	private SimpleAdapter monitorInfoAdapter;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private List<MonitorInfo> monitorDataList = new ArrayList<MonitorInfo>();
	
	private Integer carId = CARID_DEFAULT;
	private Lock lock = new ReentrantLock(); //锁
	private Condition condition = lock.newCondition();//条件变量
	private String responseStr = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.monitor_info_fragment, container, false);
		listView = (ListView) view.findViewById(R.id.monitor_list_view);
		
		//注册接受消息
		EventBus.getDefault().register(this);
		
		getLastMonitorInfo();
		//设置适配器
		monitorInfoAdapter = new MonitorInfoAdapter(this.getActivity().getApplicationContext(),list,
			R.layout.monitor_info_item, new String[] { 
			"t_m_time", "t_m_temperature", "t_m_humidity", 
			"t_m_carbon_dioxide","t_m_oxygen","t_m_nitrogen",
			"t_m_ethylene","t_m_ammonia","t_m_sulfur_dioxide"},
			new int[] {
			R.id.t_m_time, R.id.t_m_temperature, R.id.t_m_humidity, 
			R.id.t_m_carbon_dioxide, R.id.t_m_oxygen, R.id.t_m_nitrogen, 
			R.id.t_m_ethylene, R.id.t_m_ammonia, R.id.t_m_sulfur_dioxide });
				
		//设置适配器
		listView.setAdapter(monitorInfoAdapter);
		return view;
	}
	
	private void getData() {
		list.clear();		
		for(MonitorInfo tmp : monitorDataList){
			Map<String, Object> map = new HashMap<String, Object>();
				
			//保留散位小数
			/*DecimalFormat dfThree = new DecimalFormat("#.000");
			map.put("t_m_time", tmp.getTime());
			map.put("t_m_temperature", tmp.getTemperature());
			map.put("t_m_humidity", dfThree.format(tmp.getHumidity()*100) +"%");
			map.put("t_m_carbon_dioxide", dfThree.format(tmp.getCarbon_dioxide()*100)+"%");
			map.put("t_m_oxygen", dfThree.format(tmp.getOxygen()*100)+"%");
			map.put("t_m_nitrogen", dfThree.format(tmp.getNitrogen()*100)+"%");
			map.put("t_m_ethylene", dfThree.format(tmp.getEthylene()*100)+"%");
			map.put("t_m_ammonia", dfThree.format(tmp.getAmmonia()*100)+"%");
			map.put("t_m_sulfur_dioxide", dfThree.format(tmp.getSulfur_dioxide()*100)+"%");*/
			
		
			map.put("t_m_time", tmp.getTime());
			map.put("t_m_temperature", tmp.getTemperature());
			map.put("t_m_humidity", tmp.getHumidity() +"%");
			map.put("t_m_carbon_dioxide", tmp.getCarbon_dioxide()+"%");
			map.put("t_m_oxygen", tmp.getOxygen()+"%");
			map.put("t_m_nitrogen", tmp.getNitrogen()+"%");
			map.put("t_m_ethylene", tmp.getEthylene()+"%");
			map.put("t_m_ammonia", tmp.getAmmonia()+"%");
			map.put("t_m_sulfur_dioxide", tmp.getSulfur_dioxide()+"%");
			
			list.add(map);
		}
		
	}
	
	void getLastMonitorInfo(){
		new Thread(new getLastMonitorInfoThread()).start();
	}
		
	class getLastMonitorInfoThread implements Runnable{
		@Override
		public void run() {
			String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/MonitorDataServlet";
			Map<String, String> params = new HashMap<String, String>();
			
			while(true){
				try {
					lock.lock();
					//等待carId被改变
									
					if (CARID_DEFAULT == carId) {
						condition.await();						
					}				
					params.put("car_id", carId.toString());
					params.put("type", StatusUtil.LAST_MONITOR_DATA+"");
					
					responseStr = HttpUtil.sendHttpClientPostRequest(path, params,
							"UTF-8");
					
					//解析responseStr生成监控信息集合
					if(null != responseStr){
						monitorDataList = XMlParserUtils.XMLMDataParserStoMonitorDataList(responseStr);
					}
					
					//更新要显示的信息
					getData();
					
					//发送消息通知更新fragment的显示
					EventBus.getDefault().post(new UpdateMonitorFragmentUI());
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
				
				
				try { //每两秒访问一次网络去获取数据
					Thread.sleep(1000*2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
					
		}
	}
	
	
	
	//消息处理
	public void onEventMainThread(GetLastMonitorInfoEven even){
		
		lock.lock();
			 carId = even.getCarId();	
			 condition.signal();
		lock.unlock();			 
	}
	
	public void onEventMainThread(UpdateMonitorFragmentUI even){
		listView.setAdapter(monitorInfoAdapter);
		monitorInfoAdapter.notifyDataSetChanged();
	}
	

}
