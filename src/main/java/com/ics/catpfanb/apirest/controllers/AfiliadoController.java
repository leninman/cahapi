package com.ics.catpfanb.apirest.controllers;

import com.ics.catpfanb.apirest.models.entity.Afiliado;
import com.ics.catpfanb.apirest.services.IAfiliadoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("afiliado")
@Slf4j
public class AfiliadoController {

    private final IAfiliadoService afiliadoService;

    public AfiliadoController(IAfiliadoService afiliadoService) {

        this.afiliadoService = afiliadoService;
    }

    @GetMapping("listar")
    public List<Afiliado> listarAfiliados(){
        return afiliadoService.findAll();
    }

    @GetMapping("consultar/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();

        try{
            Optional<Afiliado> cliente=afiliadoService.findById(id);
            if(cliente.isPresent()){
                return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            }else{
                response.put("mensaje","El afiliado con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                log.error("Afiliado con  id {} no existe en  la base de datos",id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("crear")
    public ResponseEntity<?> crear(@Valid @RequestBody Afiliado afiliado, BindingResult result){
        Map<String,Object> response=new HashMap<>();
        Afiliado nuevoAfiliado;

        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors().stream().map(e-> "El campo " + e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
            errors.stream().forEach(error->{
                log.error("Error al realizar la insercion en la base de datos {} ",error);
                response.put("error",error);
            });

            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        try{
           nuevoAfiliado =afiliadoService.save(afiliado);
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la insercion en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido creado con exito");
        response.put("cliente",nuevoAfiliado);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();
        try{

            Optional<Afiliado> afiliado=afiliadoService.findById(id);
            if(afiliado.isPresent()){
                afiliadoService.delete(id);
            }else{
                response.put("mensaje","El afiliado con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }


        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar afiliado en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El afiliado ha sido eliminado exitosamente de la base de datos");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PutMapping("modificar/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Afiliado afiliado, BindingResult result, @PathVariable Long id){
        Map<String,Object> response=new HashMap<>();
        Afiliado afiliadoActualizado;
        Optional<Afiliado> afiliadoConsultado;


        if(result.hasErrors()){
            List<String> errors=result.getFieldErrors().stream().map(e-> "El campo: " + e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
            errors.stream().forEach(e->{
                response.put("error",e);
                log.error("Error al realizar la insercion en la base de datos {}",e);
            });
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        try{
            afiliadoConsultado=afiliadoService.findById(id);
            if (!afiliadoConsultado.isPresent()){
                response.put("mensaje","El afiliado con Id: ".concat(id.toString().concat(" no existe en la base de datos")));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch(DataAccessException  e){
            response.put("mensaje","Error al realizar la consulta a la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try{
            afiliado.setId(id);
            afiliado.setCreateAt(LocalDate.now());
            afiliadoActualizado =afiliadoService.save(afiliado);
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la insercion en la base de datos:");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido actualizado con exito");
        response.put("cliente",afiliadoActualizado);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


}
