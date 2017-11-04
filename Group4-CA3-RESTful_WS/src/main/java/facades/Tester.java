/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordStorage;

/**
 *
 * @author Sean
 */
public class Tester {
    

    public static void main (String[] args) throws PasswordStorage.CannotPerformOperationException
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_development");
        EntityManager em = emf.createEntityManager();
        UserFacade facade = new UserFacade(emf);
        User user = new User("Sean", "Blob");
        facade.createUser(user);
    }
}
