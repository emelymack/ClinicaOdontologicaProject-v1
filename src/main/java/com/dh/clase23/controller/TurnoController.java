package com.dh.clase23.controller;

import com.dh.clase23.repository.OdontologoDAOH2;
import com.dh.clase23.repository.PacienteDAOH2;
import com.dh.clase23.repository.TurnoDAO;
import com.dh.clase23.dominio.Odontologo;
import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.dominio.Turno;
import com.dh.clase23.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService = new TurnoServiceImpl(new TurnoDAO());
    private IPacienteService pacienteService = new PacienteServiceImpl(new PacienteDAOH2());
    private IOdontologoService odontologoService = new OdontologoServiceImpl(new OdontologoDAOH2());

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id){
        ResponseEntity<Turno> response;
        Turno turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado != null){
            response = ResponseEntity.ok(turnoBuscado);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        Paciente pacienteBuscado = pacienteService.buscarXId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());
        if(pacienteBuscado != null && odontologoBuscado != null){
            response = ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        Paciente pacienteBuscado = pacienteService.buscarXId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());
        if(pacienteBuscado != null && odontologoBuscado != null){
            response = ResponseEntity.ok(turnoService.actualizarTurno(turno));
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id) {
        ResponseEntity<String> response = null;
        if(buscarTurno(id) != null){
            turnoService.eliminarTurno(id);
            response = ResponseEntity.ok("Se eliminó el turno con id: "+id);
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se eliminó el turno con id: "+id+" porque no fue encontrado");
        }
        return response;
    }
}
