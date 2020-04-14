package com.asukim.asukim_emoticon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 *@UserData
 *@date 2017-03-22
 *@brief listDataArrayList에서 사용할 UserData 구성
 */
public class ListData implements Serializable {
    private static final String JSON_ID = "id";
    private static final String JSON_DATA = "data";
    private static final String JSON_CHKED = "chked";

    private UUID mId;
    private String mData;
    private boolean mChked;

    public ListData() {
        mId = UUID.randomUUID();
    }

    ListData(String data, boolean chked) {
        mId = UUID.randomUUID();
        mChked = chked;
        mData = data;
    }

    /** @brief ListData
     *  @date 2017-03-22
     *  @detail json.getString(JSON_DATA) : 저장된 String값으로 해당 값 가져오기
     */
    public ListData(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mData = json.getString(JSON_DATA);
        mChked = json.getBoolean(JSON_CHKED);
    }

    /** @brief toJSON
     *  @date 2017-03-22
     *  @detail json으로 각각의 id에 데이터 입력
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());

        json.put(JSON_DATA, mData);
        json.put(JSON_CHKED, mChked);
        return json;
    }

    public UUID getId() {
        return mId;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public boolean getmChked() {
        return mChked;
    }

    public void setmChked(boolean mChked) {
        this.mChked = mChked;
    }
}
