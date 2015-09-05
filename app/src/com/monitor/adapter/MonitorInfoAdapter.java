package com.monitor.adapter;

import java.util.List;
import java.util.Map;

import com.monitor.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MonitorInfoAdapter extends SimpleAdapter {

	private LayoutInflater mInflater;
	public MonitorInfoAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.monitor_info_item, null);
		}
		
		//…Ë÷√◊÷ÃÂ—’…´
		((TextView)convertView.findViewById(R.id.m_time)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_temperature)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_humidity)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_carbon_dioxide)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_oxygen)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_nitrogen)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_ethylene)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_ammonia)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.m_sulfur_dioxide)).setTextColor(R.string.blue_green);
		
		((TextView)convertView.findViewById(R.id.t_m_time)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_temperature)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_humidity)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_carbon_dioxide)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_oxygen)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_nitrogen)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_ethylene)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_ammonia)).setTextColor(R.string.blue_green);
		((TextView)convertView.findViewById(R.id.t_m_sulfur_dioxide)).setTextColor(R.string.blue_green);
		
		return super.getView(position, convertView, parent);
	}
}
