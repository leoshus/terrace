package com.sdw.soft.core.web.action;

import java.io.Serializable;

import com.sdw.soft.core.entity.BaseEntity;


/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public abstract class BaseController<T extends BaseEntity<ID>,ID extends Serializable> extends AncestorController<T, ID> {

	private static final long serialVersionUID = 4834741931065242566L;

}
