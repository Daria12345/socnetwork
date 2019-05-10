package com.example.sweater.domain;

import com.example.sweater.Services.RolesService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;
    @NotBlank(message = "Поле пароля не должно быть пустым")
    private String password;



    @Transient
    @NotBlank(message = "Поле пароля не должно быть пустым")
    private String password2;
    private boolean enabled;
  //  @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "username"))
//    private Set<Roles> roles;
//
//    public Set<Roles> getRoles() {
//        return roles;
 //   }

 ///   public void setRoles(Set<Roles> roles) {
 //       this.roles = roles;
 //   }

    public Long getId() {
       return id;
   }

   public void setId(Long id) {
      this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


}
