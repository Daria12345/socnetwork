package com.example.sweater.domain;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long catId;
    String nameOfCategory;
    String kindOfCategory;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getKindOfCategory() {
        return kindOfCategory;
    }

    public void setKindOfCategory(String kindOfCategory) {
        this.kindOfCategory = kindOfCategory;
    }
}
