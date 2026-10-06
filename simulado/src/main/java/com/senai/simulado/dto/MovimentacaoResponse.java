package com.senai.simulado.dto;

import com.senai.simulado.entity.Movimentacao;
import com.senai.simulado.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoResponse(
        Long id,
        Long produtoId, String produtoNome,
        TipoMovimentacao tipo,
        int quantidade,
        int saldoAnterior,
        int saldoPosterior,
        LocalDateTime dataHora,
        Long usuarioId,
        String usuarioNome,
        String usuarioEmail,
        String observacao) {

    public static MovimentacaoResponse de(Movimentacao m) {
        return new MovimentacaoResponse(
                m.getId(),
                m.getProduto().getId(),
                m.getProduto().getNome(),
                m.getTipo(),
                m.getQuantidade(),
                m.getSaldoAnterior(),
                m.getSaldoPosterior(),
                m.getDataHora(),
                m.getUsuario().getId(),
                m.getUsuario().getNome(),
                m.getUsuario().getEmail(),
                m.getObservacao());
    }
}
