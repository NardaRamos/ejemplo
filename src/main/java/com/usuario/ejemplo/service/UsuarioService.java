package com.usuario.ejemplo.service;

import com.usuario.ejemplo.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    public List<UsuarioEntity> getUsuarios(){
      ResponseEntity<List<UsuarioEntity>> response = restTemplate.exchange("https://jsonplaceholder.typcode.com/users",
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<UsuarioEntity>>() {
              }
      );
      return response.getBody();
    }

    public UsuarioEntity getById(Integer id) {
        return  restTemplate.getForEntity("https://jsonplaceholder.typcode.com/users/" + id,
                UsuarioEntity.class).getBody();
    }

    public UsuarioEntity save(UsuarioEntity usuarioEntity){
        return  restTemplate.postForObject("https://jsonplaceholder.typcode.com/users", usuarioEntity, UsuarioEntity.class);
    }

    public UsuarioEntity  update(Integer id, UsuarioEntity usuarioEntity) {
        HttpEntity<UsuarioEntity> httpEntity = new HttpEntity<>(usuarioEntity);
        ResponseEntity<UsuarioEntity> response = restTemplate.exchange("https://jsonplaceholder.typcode.com/users/" + id,
                HttpMethod.PUT, httpEntity, UsuarioEntity.class);
        return  response.getBody();
    }

    public void delete(Integer id) {
        restTemplate.delete("https://jsonplaceholder.typcode.com/users" + id );
    }
}
