package com.gestao_de_estoque.tainaS.controllers;

import com.gestao_de_estoque.tainaS.enums.TipoMovimentacao;
import com.gestao_de_estoque.tainaS.services.MovimentacaoService;
import com.gestao_de_estoque.tainaS.sessoes.SessaoDto;
import com.gestao_de_estoque.tainaS.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovimentacaoController {

    private final MovimentacaoService service;

    public MovimentacaoController(
            MovimentacaoService service) {

        this.service = service;
    }


    @PostMapping("/movimentacoes/registrar")
    public String registrar(
            @RequestParam Long produtoId,
            @RequestParam TipoMovimentacao tipo,
            @RequestParam Integer quantidade,
            HttpSession session,
            RedirectAttributes redirectAttributes) {


        SessaoDto usuario =
                SessaoUtil.ObterSessao(session);


        if (usuario == null) {
            return "redirect:/login";
        }


        try {

            service.registrar(
                    produtoId,
                    tipo,
                    quantidade,
                    usuario
            );


            redirectAttributes.addFlashAttribute(
                    "mensagem",
                    "Movimentação registrada com sucesso!"
            );


        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }


        return "redirect:/estoque";
    }
}
