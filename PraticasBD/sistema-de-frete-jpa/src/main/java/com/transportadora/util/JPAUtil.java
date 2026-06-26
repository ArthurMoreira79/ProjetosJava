package com.transportadora.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("db_frete_transportadora"); // Deve bater com o nome no seu persistence.xml

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}