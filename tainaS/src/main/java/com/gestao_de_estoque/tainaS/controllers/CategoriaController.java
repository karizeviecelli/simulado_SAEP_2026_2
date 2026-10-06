package com.gestao_de_estoque.tainaS.controllers;

import com.gestao_de_estoque.tainaS.dtos.CategoriaDto;
import com.gestao_de_estoque.tainaS.services.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute CategoriaDto categoriaDto,
            RedirectAttributes redirectAttributes) {

        service.salvar(categoriaDto);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Categoria cadastrada com sucesso!"
        );

        return "redirect:/categorias";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        service.excluir(id);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Categoria excluída com sucesso!"
        );

        return "redirect:/categorias";
    }
}