package org.danieska.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "User")
    private String user;

    @Column(name = "password")
    private String password;

    public User (String User, String password){
        this.user = User;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String toString(){
        return "User{" + "id =" + id + ",user= '" + user + '\'' + ",password= '" + password + '\'' + '}';
    }

}
