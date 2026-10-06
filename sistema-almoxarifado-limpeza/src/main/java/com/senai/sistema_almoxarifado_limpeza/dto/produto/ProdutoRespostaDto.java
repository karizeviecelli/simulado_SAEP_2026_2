package com.senai.sistema_almoxarifado_limpeza.dto.produto;

import com.senai.sistema_almoxarifado_limpeza.entity.ProdutoEntity;

public record ProdutoRespostaDto(

        Long id,

        String codigo,

        String nome,

        String caracteristicas,

        Integer estoqueAtual,

        Integer estoqueMinimo

) {

    public static ProdutoRespostaDto toProdutoRespostaDto(ProdutoEntity produto){
        return new ProdutoRespostaDto(
                produto.getId(),produto.getCodigo(),
                produto.getNome(),produto.getCaracteristicas(),
                produto.getEstoqueAtual(),produto.getEstoqueMinimo()
        );
    }

}