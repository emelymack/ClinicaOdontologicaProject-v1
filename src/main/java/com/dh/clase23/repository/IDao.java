package com.dh.clase23.repository;

import java.util.List;

public interface IDao <T>{
    List<T> listarElementos();
    T buscarXCriterio(String criterio);
    T buscarXId(int id);
    T guardar(T t);
    void eliminar(int id);
    T actualizar(T t);
}
