package com.example.sweater.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idevent;
    @NotBlank(message = "Поле не должно быть пустым")
    private String place;
    private String user;
    private String date;
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    private String filename;
    @NotNull(message = "Поле не должно быть пустым")
    private String requiredage;

    private int numberofguests;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRequiredage() {
        return requiredage;
    }

    public void setRequiredage(String requiredage) {
        this.requiredage = requiredage;
    }

    public int getNumberofguests() {
        return numberofguests;
    }

    public void setNumberofguests(int numberofguests) {
        this.numberofguests = numberofguests;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getIdevent() {
        return idevent;
    }

    public void setIdevent(Long idevent) {
        this.idevent = idevent;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
