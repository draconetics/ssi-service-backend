/**
 * (C) 2017 Agilysys NV, LLC.  All Rights Reserved.  Confidential Information of Agilysys NV, LLC.
 */
package com.dh.ssiservice.dao;


import com.dh.ssiservice.model.Item;
import com.dh.ssiservice.model.ModelBase;

import org.apache.tomcat.util.codec.binary.Base64;

public class ItemCommand extends ModelBase{

    private String name;
    private String code;
    private String label;
    private String image;
    private String category;
    private Long subCategoryId;
    private String price;
    private Object[] comments = new Object[0];
    private String description;
    private Boolean featured;

    public ItemCommand(Item item) {
    	setId(item.getId());
        setVersion(item.getVersion());
        setCreatedOn(item.getCreatedOn());
        setUpdatedOn(item.getUpdatedOn());
        
        this.setCategory(item.getSubCategory().getCategory().getName());
        this.setSubCategoryId(item.getSubCategory().getId());
        this.setDescription("This is all about " + item.getName());
        this.setFeatured(true);//TRUE para todos los objetos
        
        this.setName(item.getName());
        this.setCode(item.getCode());
        this.setLabel(item.getName());
        if (item.getImage() != null) {
            byte[] bytes = new byte[item.getImage().length];
            for (int i = 0; i < item.getImage().length; i++) {
                bytes[i] = item.getImage()[i];
            }
            String imageStr = Base64.encodeBase64String(bytes);
            this.setImage(imageStr);
        }
        this.setPrice("5000");//5000 para todos los objetos
    }

    public ItemCommand() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object[] getComments() {
        return comments;
    }

    public void setComments(Object[] comments) {
        this.comments = comments;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Item toDomain() {
        Item item = new Item();
        item.setCode(getCode());
        item.setName(getName());
        
        item.setId(getId());
        item.setVersion(getVersion());
        item.setCreatedOn(getCreatedOn());
        item.setUpdatedOn(getUpdatedOn());
        return item;
    }
}
