package com.carol.tool.domain;

import com.carol.tool.BeetlTool;

public class Column {
    /**
     * 中文显示
     */
    private String title ;
    /**
     * 英文字段
     */
    private String column;

    /**
     * SQL字段
     */
    private String columnSql;
    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据长度
     */
    private String dataLength;
    /**
     * 是否非空
     */
    private String isNotNull;
    /**
     * 字段说明
     */
    private String columnDesc;

    /**
     * 补充说明
     */
    private String columnRemark;

    /**
     * 首字母大写
     */
    private String upperColumn;

    /**
     * 是否
     */
    private boolean isSearch;
    /**
     * 是否列表
     */
    private boolean isList;
    /**
     * 过滤规则
     */
    private String filterType;
    /**
     * 表单类型
     */
    private String inputType;
    /**
     * 验证规则
     */
    private String validates;
    /**
     * SQL类型（长度）
     */
    private String definition;

    /**
     * 是否显示
     */
    private boolean show;

    public boolean isShow() {
        return show;
    }
    public void setShow(boolean show) {
        this.show = show;
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    public boolean getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(boolean search) {
        isSearch = search;
    }

    public boolean getIsList() {
        return isList;
    }

    public void setIsList(boolean list) {
        isList = list;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getValidates() {
        return validates;
    }

    public void setValidates(String validates) {
        this.validates = validates;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUpperColumn() {
        return BeetlTool.normalizeEnity(this.column);
    }

    public void setUpperColumn(String upperColumn) {
        this.upperColumn = upperColumn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(String isNotNull) {
        this.isNotNull = isNotNull;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    public String getColumnRemark() {
        return columnRemark;
    }

    public void setColumnRemark(String columnRemark) {
        this.columnRemark = columnRemark;
    }

    public String getColumnSql() {
        return columnSql;
    }

    public void setColumnSql(String columnSql) {
        this.columnSql = columnSql;
    }



    public String toString() {
        return "Column{" +
                "title='" + title + '\'' +
                ", column='" + column + '\'' +
                ", columnSql='" + columnSql + '\'' +
                ", dataType='" + dataType + '\'' +
                ", isNotNull='" + isNotNull + '\'' +
                ", columnDesc='" + columnDesc + '\'' +
                ", columnRemark='" + columnRemark + '\'' +
                '}';
    }
}
