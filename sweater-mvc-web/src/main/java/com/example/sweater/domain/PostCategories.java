package com.example.sweater.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "postcategories")
public class PostCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    @NotBlank(message = "Поле не должно быть пустым")
    private String nameOfCategory;
    @NotBlank(message = "Поле не должно быть пустым")
    private String kindOfCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event")
    private Event event;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
