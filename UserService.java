package com.example.LoginSignup;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<UserModel> getusers(){

        List<UserModel> employeeList = userRepository.findAll();
        return employeeList;
    }


    UserModel createUser(UserModel um){
        return userRepository.save(new UserModel(um.getName(),um.getEmail(),um.getPassword(),um.getDob(),um.getGender()));
    }

    Optional<UserModel> online (String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setProperty(1);
            userRepository.save(userModel);
            return userRepository.save(userModel);
        });
    }
    Optional<UserModel> loginCheck(String n)
    {
        Optional<UserModel> um=userRepository.findById(n);
        return um;
    }

    int checkSession(String e)
    {
        int session=userRepository.findById(e).get().getProperty();
        return session;
    }

    Optional<UserModel> updateName(UserModel um,String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setName(um.getName());
            return userRepository.save(userModel);
        });
    }

    Optional<UserModel> updateEmail(UserModel um,String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setEmail(um.getEmail());
            return userRepository.save(userModel);
        });
    }

    Optional<UserModel> updatePassword(UserModel um,String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setPassword(um.getPassword());
            return userRepository.save(userModel);
        });
    }

    Optional<UserModel> updateDOB(UserModel um,String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setDob(um.getDob());
            return userRepository.save(userModel);
        });
    }

    void Delete(String id)
    {
        userRepository.deleteById(id);
    }

    Optional<UserModel> LogOut(String email)
    {
        return userRepository.findById(email).map(userModel -> {
            userModel.setProperty(0);
            return userRepository.save(userModel);
        });
    }







}
