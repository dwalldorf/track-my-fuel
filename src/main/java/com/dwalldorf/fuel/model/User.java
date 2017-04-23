package com.dwalldorf.fuel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users", indexes = @Index(columnList = "username, email"))
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime registration;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime firstLogin;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime lastLogin;

    @Column(nullable = false)
    private Boolean confirmedEmail = false;

    @OneToMany(mappedBy = "user")
    private List<Car> cars;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public DateTime getRegistration() {
        return registration;
    }

    public User setRegistration(DateTime registration) {
        this.registration = registration;
        return this;
    }

    public DateTime getFirstLogin() {
        return firstLogin;
    }

    public User setFirstLogin(DateTime firstLogin) {
        this.firstLogin = firstLogin;
        return this;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public User setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public Boolean getConfirmedEmail() {
        return confirmedEmail;
    }

    public User setConfirmedEmail(Boolean confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
        return this;
    }
}