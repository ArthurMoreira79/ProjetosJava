package br.com.locadora.dao;

import br.com.locadora.modelo.Cliente;
import javax.persistence.EntityManager;

public class ClienteDAO {

    private EntityManager em;

    //DAO recebe apenas a conexão ativa
    public ClienteDAO(EntityManager em){
        this.em = em;
    }

    //metodo para salvar o cliente
    public void cadastrar(Cliente cliente){
        this.em.persist(cliente);
    }

    //metodo para buscar um cliente por id
    public Cliente buscarPorId(Integer id){
        return this.em.find(Cliente.class, id);
    }
}
