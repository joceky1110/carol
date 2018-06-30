package com.carol.model;

import com.carol.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
/**
 * 用户表
 * Generate By CodeGenerator
 * @author chris
 */
@Entity
@Table(name="USER_INFO")
public class UserInfo extends BaseEntity {

    /**
     * 用户名
     */
   	@Column(name="NAME",columnDefinition="varchar(32) COMMENT '用户名'",nullable=false)
   	private String name;

    /**
     * 头像
     */
   	@Column(name="IMG",columnDefinition="varchar(256) COMMENT '头像'")
   	private String img;

    /**
     * 城市
     */
   	@Column(name="CITY",columnDefinition="varchar(128) COMMENT '城市'")
   	private String city;

    /**
     * 性别
     */
   	@Column(name="SEX",columnDefinition="varchar(4) COMMENT '性别'")
   	private String sex;

    /**
     * 注册时间
     */
   	@Column(name="REGISTER_TIME",columnDefinition="date COMMENT '注册时间'")
   	private Date registerTime;

    /**
     * 最近登录
     */
   	@Column(name="LAST_LOGIN",columnDefinition="date COMMENT '最近登录'")
   	private Date lastLogin;

    /**
     * 等级
     */
   	@Column(name="GRADE",columnDefinition="varchar(32) COMMENT '等级'")
   	private String grade;

    /**
     * 头衔
     */
   	@Column(name="TITLE",columnDefinition="varchar(32) COMMENT '头衔'")
   	private String title;

    /**
     * 出生日期
     */
   	@Column(name="BIRTH",columnDefinition="varchar(32) COMMENT '出生日期'")
   	private String birth;

    /**
     * 手机
     */
   	@Column(name="PHONE",columnDefinition="varchar(32) COMMENT '手机'")
   	private String phone;

    /**
     * 邮箱
     */
   	@Column(name="EMAIL",columnDefinition="varchar(32) COMMENT '邮箱'")
   	private String email;

    /**
     * 密码
     */
   	@Column(name="PASSWORD",columnDefinition="varchar(32) COMMENT '密码'")
   	private String password;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getImg(){
        return this.img;
    }
    public void setImg(String img){
        this.img=img;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getSex(){
        return this.sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public Date getRegisterTime(){
        return this.registerTime;
    }
    public void setRegisterTime(Date registerTime){
        this.registerTime=registerTime;
    }
    public Date getLastLogin(){
        return this.lastLogin;
    }
    public void setLastLogin(Date lastLogin){
        this.lastLogin=lastLogin;
    }
    public String getGrade(){
        return this.grade;
    }
    public void setGrade(String grade){
        this.grade=grade;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getBirth(){
        return this.birth;
    }
    public void setBirth(String birth){
        this.birth=birth;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }

}
