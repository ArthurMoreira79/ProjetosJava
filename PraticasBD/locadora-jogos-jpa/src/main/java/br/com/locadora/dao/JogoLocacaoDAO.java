package br.com.locadora.dao;

import br.com.locadora.modelo.JogoLocacao;
import javax.persistence.EntityManager;

public class JogoLocacaoDAO {

    private EntityManager em;

    public JogoLocacaoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(JogoLocacao jl) {
        this.em.persist(jl);
    }

    public JogoLocacao buscarPorId(Integer id) {
        return this.em.find(JogoLocacao.class, id);
    }
}