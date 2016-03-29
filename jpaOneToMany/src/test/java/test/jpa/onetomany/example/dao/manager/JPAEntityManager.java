package test.jpa.onetomany.example.dao.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManager {

    public static EntityManagerFactory getManager() {
        return Persistence.createEntityManagerFactory("JPAOneToManyTest");
    }
}
