package com.senai.simulado.dto;

import com.senai.simulado.entity.Produto;

import java.time.LocalDateTime;

public record ProdutoResponse(Long id, String nome, String descricao, String unidadeMedida,
                              int saldo, LocalDateTime dataCadastro) {

    public static ProdutoResponse de(Produto p) {
        return new ProdutoResponse(p.getId(), p.getNome(), p.getDescricao(), p.getUnidadeMedida(),
                p.getSaldo(), p.getDataCadastro());
    }
}
