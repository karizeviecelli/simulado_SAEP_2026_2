package simulado.provasaep.controller.movimentacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simulado.provasaep.dto.movimentacao.MovimentacaoEstoqueDto;
import simulado.provasaep.service.MovimentacaoEstoqueService;

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