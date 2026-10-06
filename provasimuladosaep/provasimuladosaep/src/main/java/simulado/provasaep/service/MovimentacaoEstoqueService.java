package simulado.provasaep.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simulado.provasaep.dto.movimentacao.MovimentacaoEstoqueDto;
import simulado.provasaep.dto.produto.ProdutoRespostaDto;
import simulado.provasaep.entity.MovimentacaoEstoqueEntity;
import simulado.provasaep.entity.ProdutoEntity;
import simulado.provasaep.entity.TipoMovimentacaoEstoque;
import simulado.provasaep.entity.UsuarioEntity;
import simulado.provasaep.repository.MovimentacaoEstoqueRepository;
import simulado.provasaep.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    public List<ProdutoRespostaDto> listarProdutosCadastrados() {
        return produtoRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(ProdutoRespostaDto::toProdutoRespostaDto)
                .toList();
    }

    public List<MovimentacaoEstoqueEntity> listarHistoricoMovimentacoes() {
        return movimentacaoEstoqueRepository.findAllByOrderByDataMovimentacaoDesc();
    }

    @Transactional
    public void registrarEntrada(MovimentacaoEstoqueDto dto, UsuarioEntity usuarioLogado) {
        ProdutoEntity produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no sistema."));

        produto.setEstoqueAtual(produto.getEstoqueAtual() + dto.quantidade());
        produtoRepository.save(produto);

        movimentacaoEstoqueRepository.save(dto.toMovimentacao(produto,usuarioLogado,
                TipoMovimentacaoEstoque.ENTRADA));
    }

    @Transactional
    public void registrarSaida(MovimentacaoEstoqueDto dto, UsuarioEntity usuarioLogado) {
        ProdutoEntity produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no sistema"));

        if (dto.quantidade() > produto.getEstoqueAtual()) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }

        produto.setEstoqueAtual(produto.getEstoqueAtual() - dto.quantidade());
        produtoRepository.save(produto);

        movimentacaoEstoqueRepository.save(dto.toMovimentacao(produto,usuarioLogado,
                TipoMovimentacaoEstoque.SAIDA));
    }

    public String verificarAlertaEstoqueMinimo(Long produtoId) {
        ProdutoEntity produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getEstoqueAtual() < produto.getEstoqueMinimo()) {
            return String.format("O produto '%s' ficou abaixo do estoque mínimo (%d unidades).",
                    produto.getNome(), produto.getEstoqueMinimo());
        }
        return null;
    }
}


