package com.carol.model;

import com.carol.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
/**
 * 用户宝宝表
 * Generate By CodeGenerator
 * @author chris
 */
@Entity
@Table(name="USER_BABY")
public class UserBaby extends BaseEntity {

    /**
     * 名字
     */
   	@Column(name="NAME",columnDefinition="varchar(32) COMMENT '名字'",nullable=false)
   	private String name;

    /**
     * 英文名
     */
   	@Column(name="EN_NAME",columnDefinition="varchar(32) COMMENT '英文名'")
   	private String enName;

    /**
     * 性别
     */
   	@Column(name="SEX",columnDefinition="varchar(32) COMMENT '性别'")
   	private String sex;

    /**
     * 生日
     */
   	@Column(name="BIRTH",columnDefinition="date COMMENT '生日'")
   	private Date birth;

    /**
     * 父母id
     */
   	@Column(name="PARENT_ID",columnDefinition="varchar(32) COMMENT '父母id'")
   	private String parentId;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEnName(){
        return this.enName;
    }
    public void setEnName(String enName){
        this.enName=enName;
    }
    public String getSex(){
        return this.sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public Date getBirth(){
        return this.birth;
    }
    public void setBirth(Date birth){
        this.birth=birth;
    }
    public String getParentId(){
        return this.parentId;
    }
    public void setParentId(String parentId){
        this.parentId=parentId;
    }

}
