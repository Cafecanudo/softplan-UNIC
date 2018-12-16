package com.softplan.unic.user.controllers;

import com.softplan.unic.core.beans.UserBean;
import com.softplan.unic.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void autenticarUsuario(@RequestBody @Valid UserBean user) {
        log.info("Autenticando usuario...");
        userService.autenticarUsuario(user);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserBean save(@RequestBody @Valid UserBean user) {
        log.info("Salvando novo usuario...");
        return userService.salvarNovoUsuario(user).get();
    }

    @GetMapping("/email/{email}")
    public UserBean buscarUsuarioPorEmail(@Valid @NotBlank(message = "Informe o email") @PathVariable String email) {
        log.info("Consulting usuario por email...");
        return userService.buscarUsuarioPorEmail(email).get();
    }

}
