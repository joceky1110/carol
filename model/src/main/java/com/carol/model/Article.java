package com.carol.model;

import com.carol.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
/**
 * 专栏文章表
 * Generate By CodeGenerator
 * @author wwb
 */
@Entity
@Table(name="ARTICLE")
public class Article extends BaseEntity {

    /**
     * 用户id
     */
   	@Column(name="USER_ID",columnDefinition="varchar(32) COMMENT '用户id'",nullable=false)
   	private String userId;

    /**
     * 用户名
     */
   	@Column(name="USER_NAME",columnDefinition="varchar(100) COMMENT '用户名'")
   	private String userName;

    /**
     * 标题
     */
   	@Column(name="TITLE",columnDefinition="varchar(100) COMMENT '标题'")
   	private String title;

    /**
     * 内容
     */
   	@Column(name="CONTENT",columnDefinition="text COMMENT '内容'",nullable=false)
   	private String content;

    /**
     * 标签代码
     */
   	@Column(name="LABEL_CODE",columnDefinition="varchar(32) COMMENT '标签代码'")
   	private String labelCode;

    /**
     * 标签名字
     */
   	@Column(name="LABEL_NAME",columnDefinition="varchar(50) COMMENT '标签名字'")
   	private String labelName;

    /**
     * 摘要
     */
   	@Column(name="SUMMARY",columnDefinition="varchar(500) COMMENT '摘要'",nullable=false)
   	private String summary;

    /**
     * 封面图片
     */
   	@Column(name="IMG",columnDefinition="varchar(500) COMMENT '封面图片'")
   	private String img;

    /**
     * 音频
     */
   	@Column(name="AUDIO ",columnDefinition="varchar(200) COMMENT '音频'")
   	private String audio ;

    /**
     * 备注
     */
   	@Column(name="REMARK",columnDefinition="varchar(256) COMMENT '备注'")
   	private String remark;

    /**
     * 浏览数
     */
   	@Column(name="VISIT",columnDefinition="int COMMENT '浏览数'")
   	private Integer visit;

    /**
     * 点赞数
     */
   	@Column(name="LOVE",columnDefinition="int COMMENT '点赞数'")
   	private Integer love;

    /**
     * 评论数
     */
   	@Column(name="COMM",columnDefinition="int COMMENT '评论数'")
   	private Integer comm;

    /**
     * 分享数
     */
   	@Column(name="SHARE",columnDefinition="int COMMENT '分享数'")
   	private Integer share;

    /**
     * 状态
     */
   	@Column(name="STATUS",columnDefinition="varchar(10) COMMENT '状态'")
   	private String status;

    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getLabelCode(){
        return this.labelCode;
    }
    public void setLabelCode(String labelCode){
        this.labelCode=labelCode;
    }
    public String getLabelName(){
        return this.labelName;
    }
    public void setLabelName(String labelName){
        this.labelName=labelName;
    }
    public String getSummary(){
        return this.summary;
    }
    public void setSummary(String summary){
        this.summary=summary;
    }
    public String getImg(){
        return this.img;
    }
    public void setImg(String img){
        this.img=img;
    }
    public String getAudio (){
        return this.audio ;
    }
    public void setAudio (String audio ){
        this.audio =audio ;
    }
    public String getRemark(){
        return this.remark;
    }
    public void setRemark(String remark){
        this.remark=remark;
    }
    public Integer getVisit(){
        return this.visit;
    }
    public void setVisit(Integer visit){
        this.visit=visit;
    }
    public Integer getLove(){
        return this.love;
    }
    public void setLove(Integer love){
        this.love=love;
    }
    public Integer getComm(){
        return this.comm;
    }
    public void setComm(Integer comm){
        this.comm=comm;
    }
    public Integer getShare(){
        return this.share;
    }
    public void setShare(Integer share){
        this.share=share;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status=status;
    }

}
