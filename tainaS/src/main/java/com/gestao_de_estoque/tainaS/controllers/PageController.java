package com.gestao_de_estoque.tainaS.controllers;

import com.gestao_de_estoque.tainaS.dtos.CategoriaDto;
import com.gestao_de_estoque.tainaS.dtos.ProdutoDto;
import com.gestao_de_estoque.tainaS.services.CategoriaService;
import com.gestao_de_estoque.tainaS.services.MovimentacaoService;
import com.gestao_de_estoque.tainaS.services.ProdutoService;
import com.gestao_de_estoque.tainaS.services.UsuarioService;
import com.gestao_de_estoque.tainaS.sessoes.SessaoDto;
import com.gestao_de_estoque.tainaS.sessoes.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    private final UsuarioService service;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final MovimentacaoService movimentacaoService;

    public PageController(UsuarioService service, CategoriaService categoriaService, ProdutoService produtoService, MovimentacaoService movimentacaoService) {
        this.service = service;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.movimentacaoService = movimentacaoService;
    }

    // =========================
    // LOGIN
    // =========================

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/")
    public String getIndex() {
        return "redirect:/login";
    }


    // =========================
    // HOME
    // =========================

    @GetMapping("/home")
    public String getHome(
            HttpSession session,
            Model model) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        return "home";
    }


    // =========================
    // CATEGORIAS
    // =========================

    @GetMapping("/categorias")
    public String listarCategoria(
            Model model,
            HttpSession session) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "categorias",
                categoriaService.listar()
        );

        return "categoria-lista";
    }


    @GetMapping("/categorias/nova")
    public String novaCategoria(
            Model model,
            HttpSession session) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "categoria",
                new CategoriaDto()
        );

        return "categoria-form";
    }


    // =========================
    // PRODUTOS
    // =========================

    @GetMapping("/produtos")
    public String listarProduto(
            @RequestParam(required = false) String nome,
            Model model,
            HttpSession session) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "produtos",
                produtoService.buscarPorNome(nome)
        );

        model.addAttribute(
                "nomeBusca",
                nome
        );

        return "produto-lista";
    }


    @GetMapping("/produtos/novo")
    public String novoProduto(
            Model model,
            HttpSession session) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "produto",
                new ProdutoDto()
        );

        model.addAttribute(
                "categorias",
                categoriaService.listar()
        );

        return "produto-form";
    }


    // =========================
    // ESTOQUE
    // =========================

    @GetMapping("/estoque")
    public String estoque(
            Model model,
            HttpSession session) {

        if (!adicionarUsuarioNaSessao(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "produtos",
                produtoService.listarOrdenadoPorNome()
        );

        return "estoque";
    }


    // =========================
    // USUÁRIO LOGADO
    // =========================

    private boolean adicionarUsuarioNaSessao(
            HttpSession session,
            Model model) {

        SessaoDto sessaoDto =
                SessaoUtil.ObterSessao(session);

        if (sessaoDto == null) {
            return false;
        }

        model.addAttribute(
                "usuarioLogado",
                sessaoDto
        );

        return true;
    }
    @GetMapping("/historico-movimentacoes")
    public String historicoMovimentacoes(
            HttpSession session,
            Model model) {

        SessaoDto sessaoDto =
                SessaoUtil.ObterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "usuarioLogado",
                sessaoDto
        );

        model.addAttribute(
                "movimentacoes",
                movimentacaoService.listar()
        );

        return "historico-movimentacoes";
    }

}