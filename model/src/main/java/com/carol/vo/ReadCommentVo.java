package com.carol.vo;

import com.carol.model.ReadComment;

import java.util.List;

public class ReadCommentVo extends ReadComment {

    private List<ReadComment> commList;
    private String userImg;

    public List<ReadComment> getCommList() {
        return commList;
    }

    public void setCommList(List<ReadComment> commList) {
        this.commList = commList;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
