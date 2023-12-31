package com.ics.catpfanb.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.CascadeType.REMOVE;

@Table(name="roles")
@Entity
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name="rol_name")
    @NotEmpty
    private String rolName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

   // @OneToMany(mappedBy="rol", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
  //  private List<Usuario> usuarios;
    @JsonIgnore
    @ManyToMany(mappedBy="roles",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Usuario> usuarios;

    public Rol() {
    }

    public Rol(String rolName, Long id, List<Usuario> usuarios) {
        this.rolName = rolName;
        this.id = id;
        this.usuarios = usuarios;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
