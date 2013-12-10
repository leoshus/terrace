package com.sdw.soft.core.service;
/**
 * @author syd
 * @Date 2013年12月2日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public enum R2OperationEnum {

    add("添加关联"),

    delete("删除关联"),

    update("更新关联");

    private String label;

    private R2OperationEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
