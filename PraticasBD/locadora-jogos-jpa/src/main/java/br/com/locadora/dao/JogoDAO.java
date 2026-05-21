package br.com.locadora.dao;

import br.com.locadora.modelo.Jogo;
import javax.persistence.EntityManager;

public class JogoDAO {

    private EntityManager em;

    //O DAO recebe o EntityManager para saber em qual conexão trabalhar
    public JogoDAO(EntityManager em) {
        this.em = em;
    }

    //Metodo para salvar o jogo no BD
    public void cadastrar(Jogo jogo) {
        this.em.persist(jogo);
    }
}
