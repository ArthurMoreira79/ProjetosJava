package br.com.locadoraImoveis.dao;

import br.com.locadoraImoveis.model.Locacao;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.*;
import java.util.List;

public class LocacaoDAO {

    //Inserir Locação
    public void inserir(Locacao locacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(locacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Atualizar Locação
    public void atualizar(Locacao locacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(locacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Lista todas as locações ativas
    public List<Locacao> listarAtivas(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT l FROM Locacao l WHERE l.ativo = true", Locacao.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //Lista todas as locações de um cliente em específico
    public List<Locacao> listarPorInquilino(Integer inquilinoId){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT l FROM Locacao l WHERE l.inquilino.id = :inquilinoId", Locacao.class)
                    .setParameter("inquilinoId", inquilinoId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
