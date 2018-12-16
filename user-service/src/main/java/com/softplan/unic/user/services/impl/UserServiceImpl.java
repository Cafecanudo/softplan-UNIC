package com.softplan.unic.user.services.impl;

import com.softplan.unic.core.beans.UserBean;
import com.softplan.unic.user.documents.UserDocument;
import com.softplan.unic.user.repositories.UserRepository;
import com.softplan.unic.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void autenticarUsuario(UserBean user) {

    }

    @Override
    public Optional<UserBean> salvarNovoUsuario(UserBean user) {
        UserDocument document = userRepository.save(toDocument(user));
        return Optional.ofNullable(toBean(document));
    }

    @Override
    public Optional<UserBean> buscarUsuarioPorEmail(String email) {
        UserDocument document = userRepository.findUserDocumentByEmail(email);
        return Optional.ofNullable(toBean(document));
    }

    /**
     * Converter Documento para Bean
     *
     * @param document
     * @return
     */
    private UserBean toBean(UserDocument document) {
        return UserBean.builder().id(document.getId()).nome(document.getNome()).email(document.getEmail())
                .perfil(document.getPerfil())
                .build();
    }

    /**
     * Converte Bean para Documento
     *
     * @param user
     * @return
     */
    private UserDocument toDocument(UserBean user) {
        return UserDocument.builder().id(user.getId()).nome(user.getNome()).email(user.getEmail())
                .perfil(user.getPerfil())
                .build();
    }
}
