/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.movies.users;

import jdk.jfr.Description;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import model.exceptions.UserStorageException;
import model.storage.users.UserStorage;
import model.storage.users.UserStorageInMemoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Robert Szlufik #2020358
 */
public class UserTest {
    
     UserStorage  userStorage = new UserStorageInMemoryImpl();
 
    
     @Test
    @Description("Should register and log in user to the system")
      public void registerAndLogIn() {
        try {
            assertEquals(true, userStorage.register("test", "test"));
            assertNotNull(userStorage.logIn("test", "test"));
        } catch (UserStorageException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
