package com.carol.model;

import com.carol.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
/**
 * 悦读评论
 * Generate By CodeGenerator
 * @author wwb
 */
@Entity
@Table(name="READ_COMMENT")
public class ReadComment extends BaseEntity {

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
     * 悦读记录id
     */
   	@Column(name="RECORD_ID",columnDefinition="varchar(32) COMMENT '悦读记录id'")
   	private String recordId;

    /**
     * 楼层
     */
   	@Column(name="FLOOR",columnDefinition="varchar(32) COMMENT '楼层'")
   	private String floor;

    /**
     * 回复评论id
     */
   	@Column(name="REPLY_ID",columnDefinition="varchar(32) COMMENT '回复评论id'")
   	private String replyId;

    /**
     * 回复用户
     */
   	@Column(name="REPLY_TO",columnDefinition="varchar(100) COMMENT '回复用户'")
   	private String replyTo;

    /**
     * 内容
     */
   	@Column(name="CONTENT",columnDefinition="varchar(500) COMMENT '内容'",nullable=false)
   	private String content;

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
    public String getRecordId(){
        return this.recordId;
    }
    public void setRecordId(String recordId){
        this.recordId=recordId;
    }
    public String getFloor(){
        return this.floor;
    }
    public void setFloor(String floor){
        this.floor=floor;
    }
    public String getReplyId(){
        return this.replyId;
    }
    public void setReplyId(String replyId){
        this.replyId=replyId;
    }
    public String getReplyTo(){
        return this.replyTo;
    }
    public void setReplyTo(String replyTo){
        this.replyTo=replyTo;
    }
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content=content;
    }

}
