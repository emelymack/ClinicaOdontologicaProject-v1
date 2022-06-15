package com.dh.clase23.controller;

import com.dh.clase23.dominio.Odontologo;
import com.dh.clase23.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Odontologo buscarOdontologoXId(@PathVariable int id){ return odontologoService.buscarOdontologoXId(id); }

    @PostMapping
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){ return odontologoService.guardarOdontologo(odontologo); }

    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){ return odontologoService.actualizarOdontologo(odontologo); }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable int id){ odontologoService.eliminarOdontologo(id); }

    @GetMapping
    public List<Odontologo> listarAllOdontologos(){ return odontologoService.listarOdontologos(); }

    /*
    PARA LAS VISTAS
    @GetMapping
    public String traerOdontologoXId(Model model, @RequestParam("id") int id){
        Odontologo odontologo = odontologoService.buscarOdontologoXId(id);
        model.addAttribute("matricula",odontologo.getMatricula());
        return "odontologo";
    }*/
}
