package com.asukim.asukim_emoticon;

/**
 * Created by AdminPond on 21/6/2558.
 */

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


/**
 *@Layout_0
 *@brief Layout_0 ~ Layout_37까지 class생성(향후에 동적으로 수정해야함, 지금은 너무 비효율적임)
 *@date 2020.03.21
 *@details viewpager에 해당 string배열을 gridView에 출력
 */
@SuppressLint("ValidFragment")
public class Layout_0 extends Fragment {

    GridView gridList;
    public ArrayList<ListData> listDataArrayList = new ArrayList<>();
    public ListAdapter adapter;

    public static Layout_0 newInstance() {
        Layout_0 fragment = new Layout_0();
        return fragment;
    }

    @SuppressLint("ValidFragment")
    public Layout_0() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page, null);

        //화면에 보여줄 string배열 변수에 저장
        final String[] str0 = getResources().getStringArray(R.array.str0);

        //listDataArrayList에 해당 배열 데이터 추가
        for (int i = 0; i < str0.length; i++) {
            listDataArrayList.add(new ListData(str0[i], true));
        }

        //화면에 보여줄 GridView 생성
        gridList = (GridView) view.findViewById(R.id.gridList);

        //adapter에 ListAdapter 설정 (layout과 보여줄 listDataArrayList, grid행 설정)
        adapter = new ListAdapter(getActivity(), R.layout.list_item, listDataArrayList, 1);

        //gridList에 adapter설정
        gridList.setAdapter(adapter);

        //gridList의 setNumColumns설정
        gridList.setNumColumns(2);

        //adapter갱신
        adapter.notifyDataSetChanged();

        //gridList 클릭시 텍스트 복사
        gridList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //ClipboardManager을 사용하여 clipBoard에 텍스트 복사
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", str0[i]);
                clipboard.setPrimaryClip(clip);

                //Todo 히스토리 배열에 복사한 데이터 저장, 히스토리 activity을 만들어서 제작해야하
                ((MainActivity) MainActivity.mContext).historyList.add(str0[i]);

                //복사한 데이터 Toast 띄우기
                Toast.makeText(getContext(), "copy : " + str0[i], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}