package com.gestao_de_estoque.tainaS.services;

import com.gestao_de_estoque.tainaS.dtos.UsuarioDto;
import com.gestao_de_estoque.tainaS.entities.UsuarioEntity;
import com.gestao_de_estoque.tainaS.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

        private final UsuarioRepository repository;

        public UsuarioService(UsuarioRepository repository) {
            this.repository = repository;
        }

        public UsuarioDto realizarLogin(UsuarioDto usuarioDto) {

            Optional<UsuarioEntity> usuarioOP =
                    this.repository.findByEmailAndSenha(
                            usuarioDto.getEmail(),
                            usuarioDto.getSenha()
                    );

            if (usuarioOP.isPresent()) {
                return this.converterEntityParaDto(usuarioOP.get());
            }

            return new UsuarioDto();
        }

        private UsuarioDto converterEntityParaDto(UsuarioEntity usuario) {

            UsuarioDto usuarioDto = new UsuarioDto();

            usuarioDto.setId(usuario.getId());
            usuarioDto.setNome(usuario.getNome());
            usuarioDto.setEmail(usuario.getEmail());
            usuarioDto.setCpf(usuario.getCpf());
            usuarioDto.setCargo(usuario.getCargo());
            usuarioDto.setStatus(usuario.getStatus());

            return usuarioDto;
        }
}
