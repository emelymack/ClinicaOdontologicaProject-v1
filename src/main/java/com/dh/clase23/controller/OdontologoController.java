package com.dh.clase23.controller;

import com.dh.clase23.dominio.Odontologo;
import com.dh.clase23.dominio.Paciente;
import com.dh.clase23.dominio.Turno;
import com.dh.clase23.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    // inyección x constructor
    private IOdontologoService odontologoService;
    @Autowired
    public OdontologoController(IOdontologoService odontologoService){ this.odontologoService = odontologoService; }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarAllOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoXId(@PathVariable int id){
        ResponseEntity<Odontologo> response;
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado != null){
            response = ResponseEntity.ok(odontologoBuscado);
        } else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> response;
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(odontologo.getId());
        if(odontologoBuscado != null){
            response = ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        }
        else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologo(@PathVariable int id){
        ResponseEntity response = null;
        if(odontologoService.buscarOdontologoXId(id) != null){
            odontologoService.eliminarOdontologo(id);
            response = ResponseEntity.status(HttpStatus.OK).build();
        } else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    /*
    PARA LAS VISTAS
    @GetMapping
    public String traerOdontologoXId(Model model, @RequestParam("id") int id){
        Odontologo odontologo = odontologoService.buscarOdontologoXId(id);
        model.addAttribute("matricula",odontologo.getMatricula());
        return "odontologo";
    }*/
}
