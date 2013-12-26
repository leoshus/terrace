package com.sdw.soft.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author syd
 * @Date 2013年12月2日
 * @version 1.0.0
 */
public interface BaseDao <T,ID extends Serializable> extends PagingAndSortingRepository<T,ID> ,JpaSpecificationExecutor<T>{
	
}
