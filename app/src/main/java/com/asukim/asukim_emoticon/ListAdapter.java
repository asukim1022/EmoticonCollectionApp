package com.asukim.asukim_emoticon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *@ListAdapter
 *@brief MainActivity에서 사용할 listview의 adapter 설정
 *@date 2016.02.18
 *@details
 */
public class ListAdapter extends ArrayAdapter<ListData> {

    Context mContext;
    private ViewHolder viewHolder = null;
    private LayoutInflater inflater = null;
    private ArrayList<ListData> arrayDateList = new ArrayList<ListData>();

    public ListAdapter(Context context, int viewId, ArrayList<ListData> alarmlist, int gridPosition) {
        super(context, viewId, alarmlist);
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.arrayDateList = alarmlist;
    }

    @Override
    public int getCount() {
        return arrayDateList.size();
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);

            viewHolder.textView = (TextView) convertView.findViewById(R.id.itemText);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ListData memoData = arrayDateList.get(position);

        if (null != memoData) {
            viewHolder.textView.setText(memoData.getmData());
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView textView = null;
    }
}
