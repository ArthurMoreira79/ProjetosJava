package br.com.locadoraImoveis.dao;

import br.com.locadoraImoveis.model.Imovel;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class ImovelDAO {

    //Inserir Imovel
    public void inserir(Imovel imovel){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(imovel);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Atualizar Imovel
    public void atualizar(Imovel imovel){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(imovel);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Lista todos os Imoveis
    public List<Imovel> listarTodos(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("SELECT i FROM Imovel i", Imovel.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //Métodos de Busca via CEP e Faixa de Preço
    public List<Imovel> buscarPorCep(String cep){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT i FROM Imovel i WHERE i.cep = :cep", Imovel.class)
                    .setParameter("cep", cep)
                    .getResultList();
        }finally {
            em.close();
        }
    }

    public List<Imovel> buscarPorFaixaDePreco(BigDecimal min, BigDecimal max){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT i FROM Imovel i WHERE i.valorAluguelSugerido BETWEEN :min AND :max", Imovel.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        }finally {
            em.close();
        }
    }

    //Lista por Tipo e Proprietario
    public List<Imovel> listarPorTipo(Integer tipoImovelId){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT i FROM Imovel i WHERE i.tipoImovel.id = :tipoId", Imovel.class)
                    .setParameter("tipoId", tipoImovelId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Imovel> listarPorProprietario(Integer proprietarioId){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT i FROM Imovel i WHERE i.proprietario.id = :propId", Imovel.class)
                    .setParameter("propId", proprietarioId)
                    .getResultList();
        }finally {
            em.close();
        }
    }
}
