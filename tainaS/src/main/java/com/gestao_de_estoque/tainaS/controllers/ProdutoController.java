package com.gestao_de_estoque.tainaS.controllers;

import com.gestao_de_estoque.tainaS.dtos.ProdutoDto;
import com.gestao_de_estoque.tainaS.services.CategoriaService;
import com.gestao_de_estoque.tainaS.services.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(
            ProdutoService produtoService,
            CategoriaService categoriaService) {

        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute ProdutoDto produtoDto,
            RedirectAttributes redirectAttributes) {

        produtoService.salvar(produtoDto);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Produto cadastrado com sucesso!"
        );

        return "redirect:/produtos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        produtoService.excluir(id);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Produto excluído com sucesso!"
        );

        return "redirect:/produtos";
    }
}
