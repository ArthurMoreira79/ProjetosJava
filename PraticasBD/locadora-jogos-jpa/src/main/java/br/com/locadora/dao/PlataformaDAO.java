package br.com.locadora.dao;

import br.com.locadora.modelo.Plataforma;
import javax.persistence.EntityManager;

public class PlataformaDAO {

    private EntityManager em;

    public PlataformaDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Plataforma plataforma) {
        this.em.persist(plataforma);
    }

    public Plataforma buscarPorId(Integer id) {
        return this.em.find(Plataforma.class, id);
    }
}
