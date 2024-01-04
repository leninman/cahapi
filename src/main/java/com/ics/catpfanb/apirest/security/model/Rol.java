package com.ics.catpfanb.apirest.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
//@Table(name = "authorities", uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id", "authority"})})
@Table(name = "roles")

public class Rol implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String rolname;

	@ManyToOne()
	//@JoinColumn(name="user_id")
	//private Usuario usuario;



	private static final long serialVersionUID = 1L;

}
