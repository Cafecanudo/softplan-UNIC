package com.softplan.unic.user.configs;

import com.softplan.unic.core.beans.UserBean;
import com.softplan.unic.core.beans.enums.ProfileType;
import com.softplan.unic.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializerData {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void inserirDadosPadrao() {
        if (!userService.buscarUsuarioPorEmail("cafecanudo@gmail.com").isPresent()) {
            userService.salvarNovoUsuario(UserBean.builder()
                    .nome("Wellton S. Barros")
                    .email("cafecanudo@gmail.com")
                    .senha("123456")
                    .perfil(ProfileType.ROLE_ADMIN)
                    .build());
        }
    }

}