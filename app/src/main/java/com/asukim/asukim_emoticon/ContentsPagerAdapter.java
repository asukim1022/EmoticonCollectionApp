package com.asukim.asukim_emoticon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 *@ContentsPagerAdapter
 *@brief viewPager를 관리할 Adapter구현, 각 position때 각 Layout으로 연결
 *@date 2020.03.21
 *@details
 */
public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;
    Context context;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount, Context context) {
        super(fm);
        this.mPageCount = pageCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Layout_0 fragment_0 = new Layout_0();
                return fragment_0;
            case 1:
                Layout_1 fragment_1 = new Layout_1();
                return fragment_1;
            case 2:
                Layout_2 fragment_2 = new Layout_2();
                return fragment_2;
            case 3:
                Layout_3 fragment_3 = new Layout_3();
                return fragment_3;
            case 4:
                Layout_4 fragment_4 = new Layout_4();
                return fragment_4;
            case 5:
                Layout_5 fragment_5 = new Layout_5();
                return fragment_5;
            case 6:
                Layout_6 fragment_6 = new Layout_6();
                return fragment_6;
            case 7:
                Layout_7 fragment_7 = new Layout_7();
                return fragment_7;
            case 8:
                Layout_8 fragment_8 = new Layout_8();
                return fragment_8;
            case 9:
                Layout_9 fragment_9 = new Layout_9();
                return fragment_9;
            case 10:
                Layout_10 fragment_10 = new Layout_10();
                return fragment_10;
            case 11:
                Layout_11 fragment_11 = new Layout_11();
                return fragment_11;
            case 12:
                Layout_12 fragment_12 = new Layout_12();
                return fragment_12;
            case 13:
                Layout_13 fragment_13 = new Layout_13();
                return fragment_13;
            case 14:
                Layout_14 fragment_14 = new Layout_14();
                return fragment_14;
            case 15:
                Layout_15 Layout_15 = new Layout_15();
                return Layout_15;
            case 16:
                Layout_16 Layout_16 = new Layout_16();
                return Layout_16;
            case 17:
                Layout_17 Layout_17 = new Layout_17();
                return Layout_17;
            case 18:
                Layout_18 Layout_18 = new Layout_18();
                return Layout_18;
            case 19:
                Layout_19 Layout_19 = new Layout_19();
                return Layout_19;
            case 20:
                Layout_20 Layout_20 = new Layout_20();
                return Layout_20;
            case 21:
                Layout_21 Layout_21 = new Layout_21();
                return Layout_21;
            case 22:
                Layout_22 Layout_22 = new Layout_22();
                return Layout_22;
            case 23:
                Layout_23 Layout_23 = new Layout_23();
                return Layout_23;
            case 24:
                Layout_24 Layout_24 = new Layout_24();
                return Layout_24;
            case 25:
                Layout_25 Layout_25 = new Layout_25();
                return Layout_25;
            case 26:
                Layout_26 Layout_26 = new Layout_26();
                return Layout_26;
            case 27:
                Layout_27 Layout_27 = new Layout_27();
                return Layout_27;
            case 28:
                Layout_28 Layout_28 = new Layout_28();
                return Layout_28;
            case 29:
                Layout_29 Layout_29 = new Layout_29();
                return Layout_29;
            case 30:
                Layout_30 Layout_30 = new Layout_30();
                return Layout_30;
            case 31:
                Layout_31 Layout_31 = new Layout_31();
                return Layout_31;
            case 32:
                Layout_32 Layout_32 = new Layout_32();
                return Layout_32;
            case 33:
                Layout_33 Layout_33 = new Layout_33();
                return Layout_33;
            case 34:
                Layout_34 Layout_34 = new Layout_34();
                return Layout_34;
            case 35:
                Layout_35 Layout_35 = new Layout_35();
                return Layout_35;
            case 36:
                Layout_36 Layout_36 = new Layout_36();
                return Layout_36;
            case 37:
                Layout_37 Layout_37 = new Layout_37();
                return Layout_37;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}