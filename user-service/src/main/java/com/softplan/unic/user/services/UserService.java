package com.softplan.unic.user.services;

import com.softplan.unic.core.beans.UserBean;

import java.util.Optional;

public interface UserService {

    void autenticarUsuario(UserBean user);

    Optional<UserBean> salvarNovoUsuario(UserBean user);

    Optional<UserBean> buscarUsuarioPorEmail(String email);

}
