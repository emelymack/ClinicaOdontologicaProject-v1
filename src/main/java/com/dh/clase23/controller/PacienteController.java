package com.dh.clase23.controller;

import com.dh.clase23.dominio.Odontologo;
import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Controller*/
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    // inyección x setter
    private PacienteServiceImpl pacienteService;
    @Autowired
    public void setPacienteService(PacienteServiceImpl pacienteService) { this.pacienteService = pacienteService; }


    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<Paciente> buscarXId(@PathVariable("pacienteId") int id){
        ResponseEntity<Paciente> response;
        if(pacienteService.buscarXId(id) != null){
            response = ResponseEntity.ok(pacienteService.buscarXId(id));
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> response;
        Paciente pacienteBuscado = pacienteService.buscarXId(paciente.getId());
        if(pacienteBuscado != null){
            response = ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable("pacienteId") int id){
        if(pacienteService.buscarXId(id) != null){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se eliminó al paciente con id: "+id);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se eliminó el turno con id: "+id);
        }
    }
    /*
    pensado para las VISTAS
    @GetMapping
    public String traerPacienteXEmail(Model model, @RequestParam("email") String email){
        //buscar al paciente con el correo ingresando por parametro
        Paciente paciente=pacienteService.buscarXEmail(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "paciente";
    }*/
}
