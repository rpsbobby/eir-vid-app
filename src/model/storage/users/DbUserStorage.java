/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.storage.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.exceptions.UserStorageException;
import model.models.User;
import model.storage.database.DbConnection;

/**
 * * @author Robert Szlufik #2020358
 */
public class DbUserStorage implements UserStorage {

    private DbConnection dbConnection;
    private Statement statement;

    public DbUserStorage(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.statement = dbConnection.getStatement();
    }

 

    @Override
    public boolean register(String email, String password) throws UserStorageException {
        try {
            statement.execute("INSERT INTO eir_vid.users (email, password) VALUES (\'" + email + "\',\'" + password + "\');");
            return true;
        } catch (SQLException e) {
            throw new UserStorageException("User already exists in Database, try different email");
        }
    }

    @Override
    public User logIn(String email, String password) throws UserStorageException {
        try {

            ResultSet rs = statement.executeQuery("SELECT password FROM users WHERE email=\'" + email + "\';");
            rs.next();
            String tempPassword = rs.getString("password");
            if (!password.equals(tempPassword)) throw new UserStorageException("Password doesn't match");
            return new User(email, tempPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new UserStorageException("User not found in database");
        }
    }

}