package com.asukim.asukim_emoticon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 *@MainActivity
 *@brief listView생성, adapter생성, ArrayList제어, viewpager설정, tabLayout설정
 *@date 2020.03.21
 *@details
 */
public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> historyList = new ArrayList();
    String SETTINGS_PLAYER_JSON = "settings_item_json";
    public static Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FloatingActionButton totalBtn, historyBtn;
    BottomSheetDialog mBottomSheetDialog;
    BottomSheetBehavior behavior;
    String[] name = {
            "신나", "행복", "사랑", "혼란", "배고픔", "부끄", "우쭐", "놀란", "화난", "아픈", "슬픈", "무서운", "걱정",//기분
            "새", "고양이", "강아지", "돼지", "토끼", "양", "곰", "물고기", "원숭이",//동물
            "댄스", "포옹", "키스", "음악", "졸린", "달리기", "충성", "감사", "손흔들다",
            "윙크", "쓰기", "사과", "눈물", "화이팅", "총/칼", "숨기"};//액션

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        /*
        historyList = getStringArrayPref(SETTINGS_PLAYER_JSON);
        if (historyList != null) {
            for (String value : historyList) {
                //Log.d("asukim", "Get json : " + value);
            }
        }
        */

        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        totalBtn = (FloatingActionButton) findViewById(R.id.totalBtn);
        historyBtn = (FloatingActionButton) findViewById(R.id.historyBtn);


        //mTabLayout에 addTab으로 name배열 입력
        for (int i = 0; i < name.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(name[i]));
        }

        //viewPager설정
        mViewPager = (ViewPager) findViewById(R.id.pager_content);

        //viewPager을 제어할 ContentsPagerAdapter생성
        ContentsPagerAdapter mContentsPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), mContext);

        //viewPager에 adapter설정
        mViewPager.setAdapter(mContentsPagerAdapter);

        //viewPager에 tabLayout설정
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //tabLayout에 tab선택시 이벤트(탭 버튼을 클릭하여 탭 선택 상태에 변화가 생기면, 리스너를 통해 어떤 탭의 선택 상태가 바뀌었는지 확인)
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            //tab의 상태가 선택 상태로 변경.
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            //tab의 상태가 선택되지 않음으로 변경.
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            //이미 선택된 상태의 tab이 사용자에 의해 다시 선택됨.
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //totalBtn : totalBtn버튼 클릭시 bottomSheet로 전체 메뉴보기 화면 띄우기
        totalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });


        /*
        미완성(추후 업데이트)
        historyBtn : historyBtn버튼 클릭시 HistoryActivity로 이동
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        */

        //bottomSheet 설정
        View bottomSheet = findViewById(R.id.bottom_sheet);

        /*
        CoordinatorLayout 자식뷰에서 BottomSheet를 사용하고 싶은 레이아웃에 behavior속성을 주면된다
        BottomSheet의 상태가 확장된 상태인지 완전히 숨겨져있는지등에 대한 상태를 알 수 있도록 Listener를 지원
        */
        behavior = BottomSheetBehavior.from(bottomSheet);

        //BottomSheetCallback을 구현한 이벤트 핸들러를 setBottomSheetCallback ( ) 함수로 등록하여 이벤트를 처리
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // newState = 상태값
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }


    /** @brief showBottomSheetDialog
     *  @date 2016-02-18
     *  @detail 전체 카테고리명 보여주는 BottomSheetDialog 띄우기
     *
     *  전체 카테고리명 layout을 동적으로 설정
    */
    private void showBottomSheetDialog() {

        //STATE_EXPANDED : 확장된 상태
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

            //STATE_COLLAPSED : 기본적인 상태이며, 일부분의 레이아웃만 보여지고 있는 상태.
            //이 높이는 behavior_peekHeight속성을 통해 변경 가능
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        //Dialog 에 표시 될 View 를 setContentView() 메소드를 호출하여 설정 한뒤 show() 를 호출
        mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        FloatingActionButton closeBtn = (FloatingActionButton) view.findViewById(R.id.closeBtn);

        //Dialog 에 표시 될 View 를 setContentView() 메소드를 호출
        mBottomSheetDialog.setContentView(view);

        //show() 를 호출
        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });


        //전체 카테고리명 버튼을 동적으로 설정
        int resourceId;
        for (int a = 0; a < 38; a++) {
            resourceId = getResources().getIdentifier("btn" + a, "id", getPackageName());

            final int i;
            i = a;

            RelativeLayout btn = (RelativeLayout) view.findViewById(resourceId);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //해당 버튼 클릭시 해당 viewpager로 이동
                    mViewPager.setCurrentItem(i);
                    mBottomSheetDialog.dismiss();
                }
            });
        }

        //카테고리에 각각의 name배열의 값 출력
        for (int a = 0; a < 38; a++) {
            resourceId = getResources().getIdentifier("text" + a, "id", getPackageName());

            final int i;
            i = a;
            TextView textView = (TextView) view.findViewById(resourceId);
            textView.setText(name[i] + "");
        }

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
    }


    //Todo 미완성 앱 출시 후 History기능 업데이트
    public void setStringArrayPref(String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    //Todo 미완성 앱 출시 후 History기능 업데이트
    public ArrayList<String> getStringArrayPref(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }


    //액티비티가 화면에 나타나고 상호작용이 가능해짐
    @Override
    protected void onResume() {
        super.onResume();
    }


    //다른 액티비티가 시작되려함, 이 액티비티는 중단되려하고 백그라운드로 들어감.
    @Override
    protected void onPause() {
        super.onPause();
        //setStringArrayPref(SETTINGS_PLAYER_JSON, historyList);
    }
}

