package com.dh.ssiservice.dao;

import javax.persistence.OneToOne;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.model.ModelBase;
import com.dh.ssiservice.model.SubCategory;

public class SubCategoryCommand extends ModelBase{

    private String name;
    private String code;
    private Long categoryId;
    
    public SubCategoryCommand() {  	
    }
    
    public SubCategoryCommand(SubCategory subCategory) {
    	setId(subCategory.getId());
    	setCreatedOn(subCategory.getCreatedOn());
    	setVersion(subCategory.getVersion());
    	setUpdatedOn(subCategory.getUpdatedOn());
    	name = subCategory.getName();
    	code = subCategory.getCode();
    	categoryId = subCategory.getCategory().getId();
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
	
	public SubCategory toDomain() {
		SubCategory sub = new SubCategory();
		sub.setCode(getCode());
		sub.setName(getName());
		
    	
		sub.setId(getId());
    	sub.setCreatedOn(getCreatedOn());
    	sub.setVersion(getVersion());
    	sub.setUpdatedOn(getUpdatedOn());
		return sub;
	}
}
