package com.ics.catpfanb.apirest.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    @Column(unique = true)
    @NotEmpty
    private String nombreUsuario;
    @NotEmpty
    private String clave;
    private String grupo;
    private String correo;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private Boolean bloqueado;
    private Boolean habilitado;
    @ManyToMany
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})}
    )
    private List<Rol> roles;

    @PrePersist
    public void prePersist(){

        this.fechaCreacion=LocalDate.now();
        this.fechaModificacion=LocalDate.now();
    }



}
