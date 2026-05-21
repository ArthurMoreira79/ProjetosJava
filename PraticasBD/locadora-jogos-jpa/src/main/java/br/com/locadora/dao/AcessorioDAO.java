package br.com.locadora.dao;

import br.com.locadora.modelo.Acessorio;
import javax.persistence.EntityManager;

public class AcessorioDAO {

    private EntityManager em;

    public AcessorioDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Acessorio acessorio) {
        this.em.persist(acessorio);
    }

    public Acessorio buscarPorId(Integer id) {
        return this.em.find(Acessorio.class, id);
    }
}