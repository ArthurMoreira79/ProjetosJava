package com.transportadora.dao;

import com.transportadora.model.Cliente;
import jakarta.persistence.EntityManager;

public class ClienteDAO extends GenericDAOImpl<Cliente, Long> {

    public ClienteDAO(EntityManager em) {
        super(em, Cliente.class);
    }
}