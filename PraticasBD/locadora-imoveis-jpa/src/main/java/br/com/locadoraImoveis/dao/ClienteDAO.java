package br.com.locadoraImoveis.dao;

import br.com.locadoraImoveis.model.Cliente;
import br.com.locadoraImoveis.util.JPAUtil;
import jakarta.persistence.*;

public class ClienteDAO {

    //Inserção de Cliente
    public void inserir(Cliente cliente){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Atualiza um Cliente
    public void atualizar(Cliente cliente){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    //Métodos auxiliares de busca via cpf e email
    public Cliente buscarPorCPF(String cpf){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    public Cliente buscaPorEmail(String email){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.email = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }
}
