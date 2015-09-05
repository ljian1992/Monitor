package com.monitor.adapter;

import com.monitor.dao.CarDao;
import com.monitor.dao.SqliteCarDao;
import com.monitor.service.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CarAutoCompleteAdaper extends CursorAdapter {

	private int columnIndex;
	private Context context;
	private Cursor cursor;
	DBOpenHelper dbOpenHelper;
	
	SQLiteDatabase sqlite;
	
	public CarAutoCompleteAdaper(Context context, Cursor c, int col) {
		super(context, c, col);
		this.columnIndex = col;
		this.context = context;
		this.cursor = c;
		
		dbOpenHelper = new DBOpenHelper(context);		
		sqlite = dbOpenHelper.getReadableDatabase();
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		 ((TextView) view).setText(cursor.getString(columnIndex)+":"+cursor.getString(columnIndex+1)); 
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final LayoutInflater inflater = LayoutInflater.from(context);  
        final TextView view = (TextView) inflater.inflate(  
                android.R.layout.simple_dropdown_item_1line, parent, false);  
        
        view.setText(cursor.getString(columnIndex)+":"+cursor.getString(columnIndex+1));  
        
		return view;
	}

	@Override
	public CharSequence convertToString(Cursor cursor) {
		
		return cursor.getString(columnIndex)+":"+cursor.getString(columnIndex+1);
	}

	@Override
	public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
		if (constraint != null) {  
				
			SqliteCarDao dao = new SqliteCarDao(context);
			Cursor c= dao.query(constraint.toString());
			return c;
        }  
        else {  
            return null;  
        }  
	}

	
}
