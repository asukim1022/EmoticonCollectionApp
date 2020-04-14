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
public class Layout_11 extends Fragment {

    GridView gridList;
    public ArrayList<ListData> listDataArrayList = new ArrayList<>();
    public ListAdapter adapter;

    public static Layout_11 newInstance() {
        Layout_11 fragment = new Layout_11();
        return fragment;
    }

    @SuppressLint("ValidFragment")
    public Layout_11() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page, null);
        final String[] str11 = getResources().getStringArray(R.array.str11);

        for (int i = 0; i < str11.length; i++) {
            listDataArrayList.add(new ListData(str11[i], true));
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
                ClipData clip = ClipData.newPlainText("", str11[i]);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "copy : " + str11[i], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}