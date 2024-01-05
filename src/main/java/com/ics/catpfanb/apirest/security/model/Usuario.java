package com.ics.catpfanb.apirest.security.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ics.catpfanb.apirest.security.model.repo.Group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String firstname;

	private String lastname;

	private String email;




	@Column(length = 10, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	private LocalDate issuedDate;

	private LocalDate lastModifiedDate;

	private Integer numAttempts;

	private Boolean enabled;

	private Boolean locked;

	@ManyToOne
	private Group group;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Rol> roles;




	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
