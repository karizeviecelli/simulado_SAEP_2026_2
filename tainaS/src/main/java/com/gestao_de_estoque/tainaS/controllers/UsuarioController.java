package com.gestao_de_estoque.tainaS.controllers;

import com.gestao_de_estoque.tainaS.dtos.UsuarioDto;
import com.gestao_de_estoque.tainaS.enums.UsuarioStatus;
import com.gestao_de_estoque.tainaS.services.UsuarioService;
import com.gestao_de_estoque.tainaS.sessoes.SessaoDto;
import com.gestao_de_estoque.tainaS.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

        private final UsuarioService service;

        public UsuarioController(UsuarioService service) {
            this.service = service;
        }

        @PostMapping("/login")
        public String realizarLogin(
                @RequestParam String email,
                @RequestParam String senha,
                Model model,
                RedirectAttributes redirectAttributes,
                HttpSession session) {

            UsuarioDto usuarioDto = new UsuarioDto();

            usuarioDto.setEmail(email);
            usuarioDto.setSenha(senha);

            UsuarioDto usuarioDtoRetorno = service.realizarLogin(usuarioDto);

            // Usuário não encontrado ou senha incorreta
            if (usuarioDtoRetorno == null || usuarioDtoRetorno.getNome() == null) {
                model.addAttribute("erro", "E-mail ou senha inválido.");
                return "login";
            }

            // Verifica se a conta está ativa
            if (usuarioDtoRetorno.getStatus() != UsuarioStatus.ATIVO) {
                model.addAttribute(
                        "erro",
                        "Sua conta está inativa. Entre em contato com o administrador."
                );
                return "login";
            }

            // Cria os dados da sessão
            SessaoDto sessaoDto = new SessaoDto();

            sessaoDto.setId(usuarioDtoRetorno.getId());
            sessaoDto.setNome(usuarioDtoRetorno.getNome());
            sessaoDto.setEmail(usuarioDtoRetorno.getEmail());
            sessaoDto.setCargo(usuarioDtoRetorno.getCargo());
            sessaoDto.setStatus(usuarioDtoRetorno.getStatus());

            // Salva o usuário na sessão
            session.setAttribute("usuarioLogado", sessaoDto);

            redirectAttributes.addFlashAttribute(
                    "mensagem",
                    "Bem-vindo, " + usuarioDtoRetorno.getNome()
            );

            return "redirect:/home";
        }

        @PostMapping("/logout")
        public String logout(HttpSession session) {

            SessaoUtil.RemoverSessao(session);

            return "redirect:/login";
        }
}
