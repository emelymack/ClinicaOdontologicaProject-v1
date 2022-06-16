package com.dh.clase23.controller;

import com.dh.clase23.dominio.Odontologo;
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
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService){ this.odontologoService = odontologoService; }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoXId(@PathVariable int id){
        return ResponseEntity.ok(odontologoService.buscarOdontologoXId(id));
    }

    @PostMapping
    public ResponseEntity registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity actualizarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
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

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarAllOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
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
