package com.senai.sistema_almoxarifado_limpeza.repository;

import com.senai.sistema_almoxarifado_limpeza.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    Optional<UsuarioEntity> findByLogin(String login);
}
