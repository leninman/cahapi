package com.ics.catpfanb.apirest.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ics.catpfanb.apirest.security.entity.Rol;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clave;
    private String grupo;
    private String correo;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private Boolean locked;
    private Boolean enabled;
    @JsonIgnoreProperties({"usuarios", "handler", "hibernateLazyInitializer"})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})}
    )
    private List<Rol> roles;


    public Usuario(String nombres, String apellidos, String nombreUsuario, String clave, String grupo, String correo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.grupo = grupo;
        this.correo = correo;
    }

    @PrePersist
    public void prePersist(){
        this.fechaCreacion=LocalDate.now();
        this.fechaModificacion=LocalDate.now();
        this.locked=false;
        this.enabled=true;
    }



}
