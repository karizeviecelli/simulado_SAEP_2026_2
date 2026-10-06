package com.gestao_de_estoque.tainaS.repositories;

import com.gestao_de_estoque.tainaS.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmailAndSenha(String email, String senha);

    Optional<UsuarioEntity> findByEmail(String email);
}
