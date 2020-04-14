package com.asukim.asukim_emoticon;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class Layout_25 extends Fragment {

    GridView gridList;
    public ArrayList<ListData> listDataArrayList = new ArrayList<>();
    public ListAdapter adapter;

    public static Layout_25 newInstance() {
        Layout_25 fragment = new Layout_25();
        return fragment;
    }

    @SuppressLint("ValidFragment")
    public Layout_25() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page, null);
        final String[] str25 = getResources().getStringArray(R.array.str25);

        for (int i = 0; i < str25.length; i++) {
            listDataArrayList.add(new ListData(str25[i], true));
        }

        gridList = (GridView) view.findViewById(R.id.gridList);
        adapter = new ListAdapter(getActivity(), R.layout.list_item, listDataArrayList, 1);
        gridList.setAdapter(adapter);
        gridList.setNumColumns(2);
        adapter.notifyDataSetChanged();

        gridList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", str25[i]);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "copy : " + str25[i], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}