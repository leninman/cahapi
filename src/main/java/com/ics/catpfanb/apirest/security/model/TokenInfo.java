package com.bdv.microservicios.Msvctblcheques.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class TokenInfo implements Serializable {

    private static final Long serialVersionUID=1l;

    private final String jwtToken;



}
