/**
 * @author: Edson A. Terceros T.
 */

package com.dh.ssiservice.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Item extends ModelBase {
    private String name;
    private String code;
    @Lob
    private Byte[] image;
    @OneToOne(targetEntity = SubCategory.class)
    //@PrimaryKeyJoinColumn(name="subCategory_id", referencedColumnName="EMP_ID")
    private SubCategory subCategory;

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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
