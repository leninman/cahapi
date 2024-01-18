package com.ics.catpfanb.apirest.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

    private final UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> usuarios(){
        return usuarioService.usuarios();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario,BindingResult result){
        Map<String,Object> response=new HashMap<>();
        Usuario nuevoUsuario;
        if(result.hasErrors()){
            return validation(result);
        }
        try{
            nuevoUsuario=usuarioService.guardar(usuario);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la insercion en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje","El usuario ha sido creado con exito");
        response.put("usuario",nuevoUsuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
