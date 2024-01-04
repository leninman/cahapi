package com.ics.catpfanb.apirest.models.entity;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "afiliados")
public class Afiliado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Size(min=4,max=20)
    @Column(nullable = false)
    private String nombre;
    @NotEmpty
    @Size(min=4,max=20)
    @Column(nullable = false)
    private String apellido;
    @NotEmpty
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    private LocalDate createAt;

    public Afiliado() {
    }

    @PrePersist
    public void prePersist(){
        this.createAt=LocalDate.now();
    }

    public Afiliado(Long id, String nombre, String apellido, String email, LocalDate createAt) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
