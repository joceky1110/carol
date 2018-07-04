package com.carol.vo;

import com.carol.model.ReadComment;
import com.carol.model.ReadRecord;

import java.util.List;

public class ReadRecordVo extends ReadRecord {

    private String userImg;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
