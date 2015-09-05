package com.monitor.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class MLFragmentAdapter extends FragmentStatePagerAdapter{

	private List<Fragment> fragList;
	private List<String> titleList;
	
	public MLFragmentAdapter(FragmentManager fm, List<android.support.v4.app.Fragment> fragList, List<String> titleList) {
		super(fm);	
		setFragList(fragList);
		setTitleList(titleList);
	}

	@Override
	public Fragment getItem(int arg0) {	
		return getFragList().get(arg0);
	}

	@Override
	public int getCount() {	
		return getFragList().size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return getTitleList().get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {	
		super.destroyItem(container, position, object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {	
		return super.instantiateItem(arg0, arg1);
	}
		
	public List<Fragment> getFragList() {
		return fragList;
	}

	public void setFragList(List<Fragment> fragList) {
		this.fragList = fragList;
	}

	public List<String> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}
}
