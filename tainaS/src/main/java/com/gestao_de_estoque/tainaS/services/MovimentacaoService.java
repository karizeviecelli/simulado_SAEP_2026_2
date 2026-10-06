package com.gestao_de_estoque.tainaS.services;

import com.gestao_de_estoque.tainaS.dtos.MovimentacaoDto;
import com.gestao_de_estoque.tainaS.entities.MovimentacaoEntity;
import com.gestao_de_estoque.tainaS.entities.ProdutoEntity;
import com.gestao_de_estoque.tainaS.enums.TipoMovimentacao;
import com.gestao_de_estoque.tainaS.repositories.MovimentacaoRepository;
import com.gestao_de_estoque.tainaS.repositories.ProdutoRepository;
import com.gestao_de_estoque.tainaS.sessoes.SessaoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;


    public MovimentacaoService(
            MovimentacaoRepository movimentacaoRepository,
            ProdutoRepository produtoRepository) {

        this.movimentacaoRepository =
                movimentacaoRepository;

        this.produtoRepository =
                produtoRepository;
    }


    // ==========================================================
    // REGISTRAR MOVIMENTAÇÃO
    // ==========================================================

    public void registrar(
            Long produtoId,
            TipoMovimentacao tipo,
            Integer quantidade,
            SessaoDto usuario) {

        ProdutoEntity produto =
                produtoRepository.findById(produtoId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Produto não encontrado"
                                )
                        );


        if (quantidade == null || quantidade <= 0) {

            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero"
            );
        }


        Integer estoqueAtual =
                produto.getEstoqueAtual();


        if (estoqueAtual == null) {

            estoqueAtual = 0;
        }


        // ENTRADA
        if (tipo == TipoMovimentacao.ENTRADA) {

            produto.setEstoqueAtual(
                    estoqueAtual + quantidade
            );

        }

        // SAÍDA
        else {

            if (quantidade > estoqueAtual) {

                throw new IllegalArgumentException(
                        "Não é possível retirar uma quantidade maior que o estoque atual."
                );
            }

            produto.setEstoqueAtual(
                    estoqueAtual - quantidade
            );
        }


        // Salva o novo estoque
        produtoRepository.save(produto);


        // ======================================================
        // CRIA O HISTÓRICO DA MOVIMENTAÇÃO
        // ======================================================

        MovimentacaoEntity movimentacao =
                new MovimentacaoEntity();


        movimentacao.setProduto(produto);

        movimentacao.setTipo(tipo);

        movimentacao.setQuantidade(quantidade);


        // Data e hora da movimentação
        movimentacao.setDataMovimentacao(
                LocalDateTime.now()
        );


        // Usuário que realizou a movimentação
        movimentacao.setUsuarioId(
                usuario.getId()
        );

        movimentacao.setUsuarioNome(
                usuario.getNome()
        );


        // Salva o histórico
        movimentacaoRepository.save(
                movimentacao
        );
    }


    // ==========================================================
    // LISTAR HISTÓRICO
    // ==========================================================

    public List<MovimentacaoDto> listar() {

        List<MovimentacaoEntity> movimentacoes =
                movimentacaoRepository
                        .findAllByOrderByDataMovimentacaoDesc();


        return movimentacoes
                .stream()
                .map(this::converterEntityParaDto)
                .toList();
    }


    // ==========================================================
    // CONVERTER ENTITY -> DTO
    // ==========================================================

    private MovimentacaoDto converterEntityParaDto(
            MovimentacaoEntity movimentacao) {

        MovimentacaoDto dto =
                new MovimentacaoDto();


        dto.setId(
                movimentacao.getId()
        );


        dto.setTipo(
                movimentacao.getTipo()
        );


        dto.setQuantidade(
                movimentacao.getQuantidade()
        );


        dto.setDataMovimentacao(
                movimentacao.getDataMovimentacao()
        );


        dto.setUsuarioId(
                movimentacao.getUsuarioId()
        );


        dto.setUsuarioNome(
                movimentacao.getUsuarioNome()
        );


        // Dados do produto

        if (movimentacao.getProduto() != null) {

            dto.setProdutoId(
                    movimentacao.getProduto().getId()
            );


            dto.setNomeProduto(
                    movimentacao
                            .getProduto()
                            .getNome()
            );
        }


        return dto;
    }

}
