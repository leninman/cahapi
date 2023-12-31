package com.ics.catpfanb.apirest.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name="usuarios")
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    @Column(name="doc_type")
    private String docType;
    @Column(name="num_doc")
    private String numDoc;
    @Column(name="user_name")
    private String userName;

    private String password;
    @Column(name="created_at")
    private LocalDate createdAt;
   // @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
  //  private Rol rol;

    @ManyToMany(fetch=FetchType.LAZY)
    private List<Rol> roles;

    private Boolean enabled;


    public Usuario() {
    }

    public Usuario(Long id, String firstName, String lastName, String docType, String numDoc, String userName, String password, LocalDate createdAt, List<Rol> roles, Boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.docType = docType;
        this.numDoc = numDoc;
        this.userName = userName;
        this.password = password;
        this.createdAt = createdAt;
        this.roles = roles;
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
