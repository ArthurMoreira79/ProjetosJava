package com.transportadora.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    void salvar(T entidade);
    void atualizar(T entidade);
    T buscarPorId(ID id);
    List<T> buscarTodos();
    void excluir(ID id);
}