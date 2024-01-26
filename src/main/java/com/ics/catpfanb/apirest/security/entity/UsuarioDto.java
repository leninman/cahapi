package com.ics.catpfanb.apirest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String nombres;
    private String apellidos;
    private String nombreUsuario;
    private String clave;
    private String grupo;
    private String correo;
}
