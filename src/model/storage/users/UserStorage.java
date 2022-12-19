/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.users;

import model.exceptions.UserStorageException;
import model.models.User;

/**
 *
 * @author Robert Szlufik #2020358

 */
public interface UserStorage {

    User logIn(String email, String password) throws UserStorageException;

    boolean register(String email, String password) throws UserStorageException;
}
