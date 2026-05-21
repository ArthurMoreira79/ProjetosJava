package br.com.locadora.dao;

import br.com.locadora.modelo.JogoPlataforma;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class JogoPlataformaDAO {

    private EntityManager em;

    public JogoPlataformaDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(JogoPlataforma jp){
        this.em.persist(jp);
    }

    public JogoPlataforma buscarPorId(Integer id){
        return this.em.find(JogoPlataforma.class, id);
    }
}
