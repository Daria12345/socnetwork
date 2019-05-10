package com.example.sweater.domain;
import javax.persistence.*;

@Entity
@Table(name="favorites")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_favorites;
    private String event;
    private String user;

    public Long getId_favorites() {
        return id_favorites;
    }

    public void setId_favorites(Long id_favorites) {
        this.id_favorites = id_favorites;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
