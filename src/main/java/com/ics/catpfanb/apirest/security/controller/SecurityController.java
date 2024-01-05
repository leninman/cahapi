package com.ics.catpfanb.apirest.security.controller;


import com.bdv.microservicios.Msvctblcheques.security.model.AuthenticationRequest;
import com.bdv.microservicios.Msvctblcheques.security.model.TokenInfo;


import com.ics.catpfanb.apirest.security.model.Usuario;
import com.ics.catpfanb.apirest.security.services.IUsuarioService;
import com.ics.catpfanb.apirest.security.services.JwtUtilService;
import com.ics.catpfanb.apirest.security.services.UsuarioDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("security")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UsuarioDetailsService usuarioDetailsService;
    @Autowired
    JwtUtilService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    IUsuarioService usuarioService;




    @PostMapping("authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
            logger.info("Autenticando al usuario {}",authenticationRequest.getUsuario());
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsuario(),
                authenticationRequest.getClave()));

            final UserDetails userDetails=usuarioDetailsService.loadUserByUsername(authenticationRequest.getUsuario());

            final String jwt = jwtService.generateToken(userDetails);

            TokenInfo tokenInfo=new TokenInfo(jwt);

            return ResponseEntity.ok(tokenInfo);

    }


    @PostMapping("user/create")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        Usuario usuarioguardado=usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioguardado);
    }

    @GetMapping("user/get")
    public ResponseEntity<Usuario> getUsuario(@RequestParam String nombredeusuario){
        Usuario usuario=usuarioService.consultarUsuario(nombredeusuario);
        if (usuario==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("user/delete")
    public ResponseEntity<?> deleteUsuario(@RequestParam Long idusuario){
        usuarioService.eliminarUsuario(idusuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("user/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario){
        Usuario usuariomodificado=usuarioService.modificarusuario(usuario);
        return ResponseEntity.ok(usuariomodificado);
    }




}
