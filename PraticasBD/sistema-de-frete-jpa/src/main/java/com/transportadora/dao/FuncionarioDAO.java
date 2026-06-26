package com.transportadora.dao;

import com.transportadora.model.Funcionario;
import jakarta.persistence.EntityManager;

public class FuncionarioDAO extends GenericDAOImpl<Funcionario, Long> {

    public FuncionarioDAO(EntityManager em) {
        super(em, Funcionario.class);
    }
}