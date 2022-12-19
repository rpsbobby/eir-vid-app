/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.users;

import java.util.HashMap;
import java.util.Map;
import model.exceptions.UserStorageException;
import model.models.User;

/**
 *
 * @author Robert Szlufik #2020358

 */
public class UserStorageInMemoryImpl implements UserStorage {

    private Map<String, User> users;

    public UserStorageInMemoryImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public User logIn(String email, String password) throws UserStorageException {
        User user = users.get(email);
        if(user != null) {
            if(user.validatePassword(password)){
                return user;
            }
        }
        throw new UserStorageException("Upss, looks Like password is incorrect");
    }

    @Override
    public boolean register(String email, String password) throws UserStorageException {
        if(users.containsKey(email)) {
            throw new UserStorageException("User Already Exists in Database. Please Choose Different Email");
        }
        User temp = new User(email, password);
        users.put(email,temp);
        return users.get(email).validatePassword(password);
    }
}
