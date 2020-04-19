package com.example.LoginSignup;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "users_table")

public class UserModel implements Serializable {

    @Id
    private String email;
    private String name;
    private String password;
    private Date dob;
    private String gender;
    private int property;



    public UserModel(@JsonProperty("name") String n, @JsonProperty("email")String e, @JsonProperty("password") String p, @JsonProperty("dob") Date d, @JsonProperty("gender")String g ) {
        this.name=n;
        this.email= e;
        this.password=p;
        this.dob=d;
        this.gender = g;
    }


    public UserModel()
    {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getProperty() {
        return property;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
