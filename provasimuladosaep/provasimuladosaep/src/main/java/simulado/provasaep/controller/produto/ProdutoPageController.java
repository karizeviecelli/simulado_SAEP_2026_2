package simulado.provasaep.controller.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import simulado.provasaep.dto.produto.ProdutoAtualizarDto;
import simulado.provasaep.dto.produto.ProdutoDto;
import simulado.provasaep.dto.produto.ProdutoRespostaDto;
import simulado.provasaep.entity.ProdutoEntity;
import simulado.provasaep.service.ProdutoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProdutoPageController {

    private final ProdutoService service;

    @GetMapping("/produtolista")
    public String getProdutoLista(@RequestParam(value = "termo", required = false) String termo,
                                  Model model) {
        List<ProdutoRespostaDto> listaProdutos;

        if (termo != null && !termo.trim().isEmpty()) {
            listaProdutos = service.buscarPersonalizada(termo);
        } else {
            listaProdutos = service.listarProdutos();
        }

        model.addAttribute("listaProdutos", listaProdutos);
        model.addAttribute("termo", termo);
        return "produtos/produtolista";
    }

    @GetMapping("/produtocadastrar")
    public String getCadastrarProduto(Model model) {
        model.addAttribute("produtoDto", new ProdutoDto(null, null, null, null, null));
        return "produtos/produtocadastrar";
    }

    @GetMapping("/produtoatualizar/{id}")
    public String getProdutoAtualizar(@PathVariable("id") Long id, Model model) {
        ProdutoEntity produto = service.buscarProdutoPorId(id);

        ProdutoAtualizarDto produtoAtualizarDto = new ProdutoAtualizarDto(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getCaracteristicas(),
                produto.getEstoqueAtual(),
                produto.getEstoqueMinimo()
        );

        model.addAttribute("produtoAtualizacao", produtoAtualizarDto);
        return "produtos/produtoatualizar";
    }
}