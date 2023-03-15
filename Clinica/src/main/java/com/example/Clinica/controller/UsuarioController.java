package com.example.Clinica.controller;


import com.example.Clinica.model.Usuario;
import com.example.Clinica.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios") //vamos mapear os usuarios
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping ("/buscar")
    public List<Usuario> listarTodas() {
        return usuarioService.buscarTodos();
    }

    @DeleteMapping ("/{id}")
    public void deletarUsuario(Integer id) {
        usuarioService.excluir(id);
    }

    //exemplo: localhost:8080/usuarios/{1}
    //teste: http://localhost:8080/usuarios/buscar

}


