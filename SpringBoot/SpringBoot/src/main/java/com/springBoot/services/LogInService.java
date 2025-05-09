package com.springBoot.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.model.LogIn;
import com.springBoot.repository.LoginRepo;

@Service
public class LogInService {

    private static final Logger LOG = Logger.getLogger(LogInService.class.getName());

    @Autowired
    LoginRepo loginRepo;

    public boolean login(String user, String password) {
        try {
            // Log the login attempt details
            LOG.info("User Name -------------> " + user);
            LOG.info("Password  -------------> " + password);
            
            // Check login credentials
            int count = loginRepo.logIn(user, password);
            LOG.info("count  ----------------> " + count);
            
            // Return true if credentials are valid, else false
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            // Log the exception if something goes wrong during the login attempt
            LOG.error("Error occurred while attempting to log in with user: " + user, e);
        }
        return false;
    }

    public LogIn loginDetails(String userName) {
        try {
            // Retrieve login details by userName
            LogIn login = loginRepo.findByUserName(userName);
            
            // Return login details
            return login;
        } catch (Exception e) {
            // Log the exception if something goes wrong while retrieving login details
            LOG.error("Error occurred while retrieving login details for user: " + userName, e);
        }
        return null;
    }

    public List<LogIn> getAllLogin() {
        List<LogIn> logInList = new ArrayList<>();
        try {
            // Retrieve all login records
            logInList = loginRepo.findAll();
        } catch (Exception e) {
            // Log the exception if something goes wrong while fetching all login records
            LOG.error("Error occurred while fetching all login records.", e);
        }
        return logInList;
    }
}
