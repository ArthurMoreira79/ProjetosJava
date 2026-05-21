package br.com.locadora.dao;

import br.com.locadora.modelo.ClienteConsole;
import javax.persistence.EntityManager;

public class ClienteConsoleDAO {

    private EntityManager em;

    public ClienteConsoleDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(ClienteConsole cc) {
        this.em.persist(cc);
    }

    public ClienteConsole buscarPorId(Integer id) {
        return this.em.find(ClienteConsole.class, id);
    }
}