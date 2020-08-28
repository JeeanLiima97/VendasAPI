package br.com.jean.rest.controller;

import br.com.jean.domain.entity.Usuario;
import br.com.jean.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){

        String senhaCript = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCript);
        return usuarioService.salvar(usuario);
    }
}
