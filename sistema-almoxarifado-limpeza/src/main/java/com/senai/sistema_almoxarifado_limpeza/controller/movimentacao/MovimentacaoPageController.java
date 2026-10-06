package com.senai.sistema_almoxarifado_limpeza.controller.movimentacao;

import com.senai.sistema_almoxarifado_limpeza.dto.movimentacao.MovimentacaoEstoqueDto;
import com.senai.sistema_almoxarifado_limpeza.service.MovimentacaoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MovimentacaoPageController {

    private final MovimentacaoEstoqueService service;

    @GetMapping("/movimentacoes")
    public String getMovimentacaoLista(Model model) {
        model.addAttribute("listaProdutos", service.listarProdutosCadastrados());
        model.addAttribute("listaMovimentacoes", service.listarHistoricoMovimentacoes());
        model.addAttribute("movimentacaoDto", new MovimentacaoEstoqueDto(null, null));

        return "movimentacoes/listarmovimentacoes";
    }
}
