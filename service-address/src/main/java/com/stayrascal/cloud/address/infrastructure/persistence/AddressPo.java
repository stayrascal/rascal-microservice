package com.stayrascal.cloud.address.infrastructure.persistence;

import com.stayrascal.cloud.common.jpa.BasePo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressPo extends BasePo {
    @Column(name = "language")
    private String language;

    @Column(name = "parent_id", length = 64)
    private Integer parentId;

    @Column(name = "path")
    private String path;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
