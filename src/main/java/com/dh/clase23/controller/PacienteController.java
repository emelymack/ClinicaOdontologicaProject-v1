package com.dh.clase23.controller;

import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Controller*/
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteServiceImpl pacienteService;

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{pacienteId}")
    public Paciente buscarXId(@PathVariable("pacienteId") int id){
        return pacienteService.buscarXId(id);
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @DeleteMapping("/{pacienteId}")
    public void eliminarPaciente(@PathVariable("pacienteId") int id){
        pacienteService.eliminarPaciente(id);
        // agregar algun msj para avisar que se eliminó correctamente?
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
