package br.com.locadora.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    //Cria uma única fábrica de conexões para toda a aplicação (pesada para criar)
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("locadoraPU");

    //Metodo estático para pegar um EntityManeger (leve para criar a cada operação)
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
