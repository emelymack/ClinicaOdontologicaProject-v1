package com.dh.clase23.service;

import com.dh.clase23.repository.IDao;
import com.dh.clase23.dominio.Turno;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TurnoServiceImpl implements ITurnoService{
    private IDao<Turno> turnoIDao;

    @Autowired
    public TurnoServiceImpl(IDao<Turno> turnoIDao){ this.turnoIDao = turnoIDao; }

    @Override
    public Turno guardarTurno(Turno turno) { return turnoIDao.guardar(turno); }

    @Override
    public List<Turno> listarTurnos() { return turnoIDao.listarElementos(); }

    @Override
    public Turno buscarTurno(int id) { return turnoIDao.buscarXId(id); }

    @Override
    public Turno actualizarTurno(Turno turno) { return turnoIDao.actualizar(turno); }

    @Override
    public void eliminarTurno(int id) { turnoIDao.eliminar(id); }
}
