package br.com.locadora.dao;

import br.com.locadora.modelo.Console;
import javax.persistence.EntityManager;

public class ConsoleDAO {

    private EntityManager em;

    public ConsoleDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Console console) {
        this.em.persist(console);
    }

    public Console buscarPorId(Integer id) {
        return this.em.find(Console.class, id);
    }
}