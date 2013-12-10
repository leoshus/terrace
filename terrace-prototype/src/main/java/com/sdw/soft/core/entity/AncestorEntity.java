package com.sdw.soft.core.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;

/**
 * @author syd
 * @Date 2013年12月1日
 * @version 1.0.0
 */
@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class AncestorEntity <ID extends Serializable> implements Persistable<ID>{

	private static final long serialVersionUID = 2982275856287402106L;
	
	private Map<String,Object> extraAttributes;//为前端UI提供额外的属性，方便数据传输
	
	@Transient
	public abstract String getDisplayLabel();
	
	@Transient
	public Map<String,Object> getExtraAttributes(){
		return extraAttributes;
	}
	
	@Transient
	public void setExtraAttributes(Map<String, Object> extraAttributes) {
		this.extraAttributes = extraAttributes;
	}
	
	public void addExtraAttribute(String key,Object value){
		if(extraAttributes == null){
			extraAttributes = Maps.newHashMap();
		}
		extraAttributes.put(key, value);
	}

	@Transient
	public String getDisplayId(){
		Serializable id = getId();
		if(id == null){
			return "";
		}
		if(id != null && id instanceof String){
			String idStr = (String)id;
			if(StringUtils.isNotBlank(idStr)){
				int length = idStr.length();
				return idStr.substring(length - 10,length);//FIXME id的设置规则
			}
		}
		return id.toString();
	}

	@Override
	@Transient
	@JsonIgnore
	public boolean isNew() {
		Serializable id = getId();
		return id == null || StringUtils.isBlank(String.valueOf(id));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if(null == obj){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(!getClass().equals(obj.getClass())){
			return false;
		}
		Persistable that = (Persistable)obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}
	
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
	
	@Override
	public String toString() {
		return String.format("Entity of type %s with id:%s", this.getClass().getName(),getId());
	}
	
	
}
