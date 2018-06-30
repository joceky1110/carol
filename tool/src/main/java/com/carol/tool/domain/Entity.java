package com.carol.tool.domain;

import java.util.List;

public class Entity {
    /**
     * 类注释
     */
    private String title;
    /**
     * 类名称
     */
    private String modelName;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表字段
     */
    private List<Column> columns;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }


    public String toString() {
        return "Entity{" +
                "title='" + title + '\'' +
                ", modelName='" + modelName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
