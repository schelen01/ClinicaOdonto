package com.example.Clinica.service;

import java.util.List;

public interface IService <T>{

    T salvar(T t); //devolve o objeto e recebe um objeto como parâmetro

    List<T> buscarTodos(); //na lista é uma representação genérica

    void excluir(Integer id);
}
