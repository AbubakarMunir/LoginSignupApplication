package com.example.LoginSignup;

import antlr.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class MainController {

    String name;
    String pass;
    String email;

    Date dob = new Date(2020, 12, 9);
    String gender;
    UserModel UU;


    @Autowired
    UserService u;

    @RequestMapping
    public String greeting() {
        return "Welcome to Login Signup application";
    }

    @GetMapping("/user")
    public List<UserModel> getuser() {

        List<UserModel> ulist = u.getusers();
        return ulist;

    }

    @PostMapping("/Signup")
    public String Register() {
        return "If you want to register add '/enterInfo' after Signup and post your info ";
    }

    @PostMapping("/Signup/enterInfo")
    public String createUser(@RequestBody UserModel um) {
        u.createUser(um);
        return um.getName() + " You have successfully signed up";
    }

    @PostMapping("/login")
    public String Login(@RequestBody UserModel obj) {
        Optional<UserModel> o = u.loginCheck(obj.getEmail());        //service dunc called to get user from db if exists else null in optional obj

        if (o.isPresent()) {
            UserModel um = o.get();
            int ind_of_space = um.getPassword().indexOf(' ');
            String pw = um.getPassword().substring(0, ind_of_space);
            if (pw.equals(obj.getPassword())) {
                u.online(obj.getEmail());     //ie online set property to 1 in database of this email means property=online
                return "You have logged in successfully\n name:" + um.getName() + "\n email:" + um.getEmail() + "\n Gender:" + um.getGender() + "\n Date Of Birth:" + um.getDob();
            } else {
                System.out.println(obj.getPassword());
                System.out.println(pw);
                System.out.println(obj.getPassword().length());
                System.out.println(pw.length());
                return "incorrect password entered";

            }
        }
        return "incorrect email id provided. This email id isnt registered with us";
    }


    @PutMapping("/UpdateName/{id}")                 //id is email as said in assignment
    String UpdateName(@RequestBody UserModel um, @PathVariable String id) {
        if(u.checkSession(id)==1) {
            Optional<UserModel> o = u.updateName(um, id);     //id is email
            //There is no need to check as that optional obj is empty or not cz in real time a user ll be able to reach here if he/she is online
            UserModel ins = o.get();
            return "updated name is"+ins.getName();
        }
        return "You are offline login to update info";
    }


    @PutMapping("/UpdateEmail/{id}")                 //id is email as said in assignment
    String UpdateEmail(@RequestBody UserModel um, @PathVariable String id) {
        if(u.checkSession(id)==1) {
            Optional<UserModel> o = u.updateEmail(um, id);     //id is email
            //There is no need to check as that optional obj is empty or not cz in real time a user ll be able to reach here if he/she is online
            UserModel ins = o.get();
            return "updated email is"+ins.getEmail();
        }
        return "You are offline login to update info";
    }

    @PutMapping("/UpdatePassword/{id}")                 //id is email as said in assignment
    String UpdatePassword(@RequestBody UserModel um, @PathVariable String id) {
        if(u.checkSession(id)==1) {
            Optional<UserModel> o = u.updatePassword(um, id);     //id is email in the postman link field
            //There is no need to check as that optional obj is empty or not cz in real time a user ll be able to reach here if he/she is online
            UserModel ins = o.get();
            return "updated password is"+ ins.getPassword();
        }
        return "You are offline login to update info";
    }

    @PutMapping("/UpdateDOB/{id}")                 //id is email as said in assignment
    String UpdateDOB(@RequestBody UserModel um, @PathVariable String id) {
        if(u.checkSession(id)==1) {
            Optional<UserModel> o = u.updateDOB(um, id);     //id is email in the postman link field
            //There is no need to check as that optional obj is empty or not cz in real time a user ll be able to reach here if he/she is online
            UserModel ins = o.get();
            return "updated date of birth is" + ins.getDob();
        }
        return "You are offline login to update info";
    }

    @DeleteMapping("/DeleteProfile/{id}")
    String deleteProfile(@PathVariable String id)
    {
        if(u.checkSession(id)==1) {
            u.Delete(id);     //id is email in the postman link field
            return "Good Bye stay safe your profile is deleted";
        }
        return "You are offline login to delete your account";
    }

    @GetMapping("/logout/{id}")
    String Offline(@PathVariable String id)
    {
        if(u.checkSession(id)==1) {
            u.LogOut(id);
            return "You are offline now. Login to see your profile";
        }

        return "You are already offline.";
    }



}


