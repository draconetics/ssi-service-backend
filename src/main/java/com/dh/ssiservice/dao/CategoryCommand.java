package com.dh.ssiservice.dao;

import com.dh.ssiservice.model.Category;
import com.dh.ssiservice.model.ModelBase;

public class CategoryCommand extends ModelBase {

    private String name;
    private String code;

    public CategoryCommand(){}

    public CategoryCommand(Category category){
        setId(category.getId());
        setVersion(category.getVersion());
        setCreatedOn(category.getCreatedOn());
        setUpdatedOn(category.getUpdatedOn());
        this.name = category.getName();
        this.code = category.getCode();
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


}
