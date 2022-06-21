package com.dh.clase23.service;

import com.dh.clase23.dominio.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente guardarPaciente(Paciente paciente);
    Paciente buscarXEmail(String email);
    Paciente buscarXId(int id);
    void eliminarPaciente(int id);
    Paciente actualizarPaciente(Paciente paciente);
}
