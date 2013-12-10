package com.sdw.soft.sys.vo;

import com.sdw.soft.core.annotation.MetaData;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class TreeNodeConstant {
	public static enum TreeNodeDragType {

        @MetaData(title = "成为子节点")
        inner,

        @MetaData(title = "成为同级前一个节点")
        prev,

        @MetaData(title = "成为同级后一个节点")
        next;

    }
}
