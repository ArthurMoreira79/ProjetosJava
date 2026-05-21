package br.com.locadora.dao;

import br.com.locadora.modelo.Locacao;
import javax.persistence.EntityManager;

public class LocacaoDAO {

    private EntityManager em;

    public LocacaoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Locacao locacao) {
        this.em.persist(locacao);
    }

    public Locacao buscarPorId(Integer id) {
        return this.em.find(Locacao.class, id);
    }
}
