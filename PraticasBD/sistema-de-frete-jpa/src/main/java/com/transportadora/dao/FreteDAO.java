package com.transportadora.dao;

import com.transportadora.model.Frete;
import jakarta.persistence.EntityManager;

public class FreteDAO extends GenericDAOImpl<Frete, Long> {

    public FreteDAO(EntityManager em) {
        super(em, Frete.class);
    }
}