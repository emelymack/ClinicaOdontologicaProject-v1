package com.dh.clase23.service;

import com.dh.clase23.repository.IDao;
import com.dh.clase23.dominio.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteServiceImpl implements IPacienteService {
    private IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteServiceImpl(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarElementos();
    }

    @Override
    public Paciente buscarXEmail(String email) {
        return pacienteIDao.buscarXCriterio(email);
    }

    @Override
    public Paciente buscarXId(int id) { return pacienteIDao.buscarXId(id); }

    @Override
    public void eliminarPaciente(int id) { pacienteIDao.eliminar(id); }

    @Override
    public Paciente guardarPaciente(Paciente paciente) { return pacienteIDao.guardar(paciente); }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) { return pacienteIDao.actualizar(paciente); }

}
