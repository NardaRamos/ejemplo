package com.usuario.ejemplo.controller;

import com.usuario.ejemplo.entity.UsuarioEntity;
import com.usuario.ejemplo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("external/users")
    public ResponseEntity<List<UsuarioEntity>> getEntity(){
        return  new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
        public ResponseEntity<UsuarioEntity> get(@PathVariable Integer id){
         return new ResponseEntity<>(usuarioService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public  ResponseEntity<UsuarioEntity> save(@RequestBody UsuarioEntity usuarioEntity){
        return  new ResponseEntity<>(usuarioService.save(usuarioEntity), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsuarioEntity> update (@PathVariable Integer id, @RequestBody UsuarioEntity usuarioEntity){
        return new ResponseEntity<>(usuarioService.update(id, usuarioEntity), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public  ResponseEntity<?> delete(@PathVariable Integer id){
        usuarioService.delete(id);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
}
