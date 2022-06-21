package com.dh.clase23.repository;

import com.dh.clase23.dominio.Turno;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDAO implements IDao<Turno> {

    /* Se utiliza una lista p/ guardar los diferentes turnos
    Queda PENDIENTE el tratamiento con BDD
    vamos a usar un atributo para trabajar */
    private List<Turno> turnos;

    public TurnoDAO(){ turnos = new ArrayList<>(); }

    @Override
    public List<Turno> listarElementos() {
        return turnos;
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscarXId(int id) {
        Turno turnoBuscado = null;
        for (Turno turno:turnos) {
            if(turno.getId() == id){
                turnoBuscado = turno;
            }
        }
        System.out.println(turnoBuscado);
        return turnoBuscado;
    }

    @Override
    public void eliminar(int id) {
        Turno turnoBuscado = buscarXId(id);
        turnos.remove(turnoBuscado);
    }

    @Override
    public Turno actualizar(Turno turno) {
        turnos.set(turnos.indexOf(buscarXId(turno.getId())),turno);
        return turno;
        /* otra forma de hacerlo:
        eliminar(turno.getId());
        guardar(turno);
        return turno;*/
    }

    @Override
    public Turno buscarXCriterio(String criterio) {
        return null;
    }
}




















