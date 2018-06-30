package com.carol.vo;

import com.carol.model.UserBaby;
import com.carol.model.UserInfo;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class UserInfoVo extends UserInfo {

    public List<UserBaby> getBabies() {
        return babies;
    }

    public void setBabies(List<UserBaby> babies) {
        this.babies = babies;
    }

    private List<UserBaby> babies;


}
