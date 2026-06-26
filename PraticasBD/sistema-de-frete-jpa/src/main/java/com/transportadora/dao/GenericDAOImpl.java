package com.transportadora.dao;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected EntityManager em;
    private final Class<T> classeEntidade;

    public GenericDAOImpl(EntityManager em, Class<T> classeEntidade) {
        this.em = em;
        this.classeEntidade = classeEntidade;
    }

    @Override
    public void salvar(T entidade) {
        em.persist(entidade);
    }

    @Override
    public void atualizar(T entidade) {
        em.merge(entidade);
    }

    @Override
    public T buscarPorId(ID id) {
        return em.find(classeEntidade, id);
    }

    @Override
    public List<T> buscarTodos() {
        String jpql = "SELECT e FROM " + classeEntidade.getSimpleName() + " e";
        return em.createQuery(jpql, classeEntidade).getResultList();
    }

    @Override
    public void excluir(ID id) {
        T entidade = buscarPorId(id);
        if (entidade != null) {
            em.remove(entidade);
        }
    }
}