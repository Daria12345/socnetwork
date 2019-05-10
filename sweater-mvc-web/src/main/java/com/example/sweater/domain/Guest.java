package com.example.sweater.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_guesr;
    @NotBlank(message = "Поле не должно быть пустым")
    private String guestname;
    @NotNull
    private int age;
    @Email(message = "Некорректный email")
    @NotBlank(message = "Поле не должно быть пустым")
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event")
    private Event event;

    public Long getId_guesr() {
        return id_guesr;
    }

    public void setId_guesr(Long id_guesr) {
        this.id_guesr = id_guesr;
    }

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
