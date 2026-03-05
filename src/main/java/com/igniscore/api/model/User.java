package com.igniscore.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_user")
    private Integer id;

    @Column(name = "name_user", nullable = false)
    private String name;

    @Column(name = "email_user", nullable = false)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    @Column(name = "role_user", nullable = true)
    private String role;

    @ManyToOne
    @JoinColumn(name = "fk_id_company")
    private Integer company;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Integer getCompany() {
        return company;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }
}