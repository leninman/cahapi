package com.ics.catpfanb.apirest.security.controller;

import com.ics.catpfanb.apirest.models.entity.Afiliado;
import com.ics.catpfanb.apirest.security.entity.UsuarioDto;
import com.ics.catpfanb.apirest.security.entity.UsuarioDtoToUsuarioMapper;
import com.ics.catpfanb.apirest.security.service.UsuarioService;
import com.ics.catpfanb.apirest.security.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

    private final UsuarioService usuarioService;


    @GetMapping("listar")
    public List<Usuario> usuarios(){
        return usuarioService.usuarios();
    }

    @PostMapping("crear")//RUTA PRIVADA PARA REGISTRAR USUARIOS CON ROLE_ADMIN
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario,BindingResult result){
        Map<String,Object> response=new HashMap<>();
        Usuario nuevoUsuario;
        if(result.hasErrors()){
            return validation(result);
        }
        if(usuarioService.consultarPorUsuario(usuario.getNombreUsuario()).isPresent()){
            response.put("mensaje","El usuario ya existe");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
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

    @GetMapping("consultar/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();

        try{
            Optional<Usuario> usuario=usuarioService.findById(id);
            if(usuario.isPresent()){
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            }else{
                response.put("mensaje","El usuario con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                log.error("Afiliado con  id {} no existe en  la base de datos",id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("registrar") //RUTA PUBLICA PARA REGISTRAR CUALQUIER USUARIO CON ROL_USER
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuario usuario,BindingResult result){
        Map<String,Object> response=new HashMap<>();
        if(result.hasErrors()){
            return validation(result);
        }
        if(usuarioService.consultarPorUsuario(usuario.getNombreUsuario()).isPresent()){
            response.put("mensaje","El usuario " + usuario.getNombreUsuario()+" ya existe!");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        usuario.getRoles().stream().forEach(rol->{
            if(rol.getNombreRol().equals("ROLE_ADMIN")){
                rol.setNombreRol("ROLE_USER");
            }
        });

        Usuario nuevoUsuario;

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

    @PutMapping("modificar/{id}")
    public ResponseEntity<?> modificar(@Valid @RequestBody UsuarioDto usuarioDto,BindingResult result,@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();
        Usuario usuarioActualizado;
        Optional<Usuario> usuarioConsultado;


        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors().stream().map(e-> "El campo: " + e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
            errors.stream().forEach(e->{
                response.put("error",e);
                log.error("Error al realizar la insercion en la base de datos {}",e);
            });
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            usuarioConsultado=usuarioService.findById(id);
            if (!usuarioConsultado.isPresent()){
                response.put("mensaje","El usuario con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch(DataAccessException  e){
            response.put("mensaje","Error al realizar la consulta a la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            Usuario usuario=new UsuarioDtoToUsuarioMapper().apply(usuarioDto);
            usuario.setId(id);
            usuario.setRoles(usuarioConsultado.get().getRoles());
            usuario.setFechaCreacion(usuarioConsultado.get().getFechaCreacion());
            usuario.setFechaModificacion(usuarioConsultado.get().getFechaModificacion());
            usuario.setEnabled(usuarioConsultado.get().getEnabled());
            usuario.setLocked(usuarioConsultado.get().getLocked());
            usuario.setFechaModificacion(LocalDate.now());
            usuarioActualizado =usuarioService.guardar(usuario);
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la insercion en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El usuario ha sido actualizado con exito");
        response.put("usuario",usuarioActualizado);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }



    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();
        try{

            Optional<Usuario> usuario=usuarioService.findById(id);
            if(usuario.isPresent()){
                usuarioService.delete(id);
            }else{
                response.put("mensaje","El usuario con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }


        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar usuario en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El usuario ha sido eliminado exitosamente de la base de datos");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
