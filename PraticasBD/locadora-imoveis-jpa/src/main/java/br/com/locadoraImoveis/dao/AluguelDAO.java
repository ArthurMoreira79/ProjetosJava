package br.com.locadoraImoveis.dao;

import br.com.locadoraImoveis.model.Aluguel;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.*;
import java.util.List;

public class AluguelDAO {

    //Inserir Aluguel
    public void inserir(Aluguel aluguel) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluguel);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Atualizar Aluguel
    public void atualizar(Aluguel aluguel) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluguel);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Lista todos os aluguéis de determinada locação em ordem decrescente pela data de vencimento
    public List<Aluguel> listarPorLocacaoOrdenadoPorVencimento(Integer locacaoId){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT a FROM Aluguel a WHERE a.locacao.id = :locacaoId " +
                            "ORDER BY a.dtVencimento DESC", Aluguel.class)
                    .setParameter("locacaoId", locacaoId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
