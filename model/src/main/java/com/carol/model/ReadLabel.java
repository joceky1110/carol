package com.carol.model;

import com.carol.model.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;
/**
 * 悦读标签表
 * Generate By CodeGenerator
 * @author wwb
 */
@Entity
@Table(name="READ_LABEL")
public class ReadLabel extends BaseEntity {

    /**
     * 标签代码
     */
   	@Column(name="LABEL_CODE",columnDefinition="varchar(32) COMMENT '标签代码'",nullable=false)
   	private String labelCode;

    /**
     * 标签名字
     */
   	@Column(name="LABEL_NAME",columnDefinition="varchar(50) COMMENT '标签名字'",nullable=false)
   	private String labelName;

    /**
     * 标签类型
     */
   	@Column(name="TYPE",columnDefinition="varchar(5) COMMENT '标签类型'",nullable=false)
   	private String type;

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
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }

}
