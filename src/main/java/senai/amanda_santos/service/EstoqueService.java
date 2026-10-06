package senai.amanda_santos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senai.amanda_santos.entity.MovimentacaoEntity;
import senai.amanda_santos.entity.ProdutoEntity;
import senai.amanda_santos.model.TipoMovimentacao;
import senai.amanda_santos.repository.MovimentacaoRepository;
import senai.amanda_santos.repository.ProdutoRepository;

import java.time.LocalDateTime;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public EstoqueService(ProdutoRepository produtoRepository, MovimentacaoRepository movimentacaoRepository) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    /**
     * Todo produto novo começa com saldo zero; o saldo entra via movimentação de entrada.
     */
    @Transactional
    public ProdutoEntity cadastrar(ProdutoEntity produto) {
        produto.setQuantidade(0);
        return produtoRepository.save(produto);
    }

    /**
     * Atualiza apenas os dados cadastrais. O saldo nunca é alterado por aqui.
     */
    @Transactional
    public ProdutoEntity atualizar(ProdutoEntity dados) {
        ProdutoEntity produto = produtoRepository.findById(dados.getId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produto.setCodigo(dados.getCodigo());
        produto.setNome(dados.getNome());
        produto.setDescricao(dados.getDescricao());
        produto.setUnidade(dados.getUnidade());
        produto.setEstoqueMinimo(dados.getEstoqueMinimo());
        produto.setLocalizacao(dados.getLocalizacao());
        produto.setCategoria(dados.getCategoria());
        return produtoRepository.save(produto);
    }

    @Transactional
    public MovimentacaoEntity registrarMovimentacao(Long produtoId, TipoMovimentacao tipo, int quantidade, String observacao, String responsavel) {

        ProdutoEntity produto = produtoRepository.buscarComLock(produtoId).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        int saldoAtual = produto.getQuantidade();
        int novoSaldo = (tipo == TipoMovimentacao.ENTRADA) ? saldoAtual + quantidade : saldoAtual - quantidade;

        if (novoSaldo < 0) {
            throw new IllegalArgumentException("Saldo insuficiente: há " + saldoAtual + " " + produto.getUnidade() + " em estoque e a saída pede " + quantidade + ".");
        }
        produto.setQuantidade(novoSaldo);

        MovimentacaoEntity mov = new MovimentacaoEntity();
        mov.setProduto(produto);
        mov.setTipo(tipo);
        mov.setQuantidade(quantidade);
        mov.setObservacao(observacao == null || observacao.isBlank() ? null : observacao.trim());
        mov.setResponsavel(responsavel);
        mov.setDataHora(LocalDateTime.now());
        return movimentacaoRepository.save(mov);
    }
}

