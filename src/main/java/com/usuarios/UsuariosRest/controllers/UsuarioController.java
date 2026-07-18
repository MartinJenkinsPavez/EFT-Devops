package com.usuarios.UsuariosRest.controllers;


import com.usuarios.UsuariosRest.models.UsuarioModel;
import com.usuarios.UsuariosRest.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;





    @GetMapping
    public ArrayList<UsuarioModel> getUsers(){
        return this.userService.getUsuario();
    }



    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        Optional<UsuarioModel> user = this.userService.getbyId(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.badRequest().body("no se encontró el usuario que busca con el id " + id);
        }
    }



    @PostMapping
    public UsuarioModel saveUser(@RequestBody UsuarioModel user){
        return  this.userService.saveUser(user);
    }








    @PutMapping(path = "/{id}")
    public UsuarioModel actualizarUsuario(@RequestBody UsuarioModel request, @PathVariable("id") Long id) {
        request.setId(id);
        return this.userService.actualizarUsuario(request);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.userService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id " + id;
        }
    }

}
