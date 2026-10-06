package com.gestao_de_estoque.tainaS.dtos;

import com.gestao_de_estoque.tainaS.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoDto {

    private Long id;

    private Long produtoId;

    private String nomeProduto;

    private TipoMovimentacao tipo;

    private Integer quantidade;

    private LocalDateTime dataMovimentacao;

    private Long usuarioId;

    private String usuarioNome;


    public MovimentacaoDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }


    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }


    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(
            LocalDateTime dataMovimentacao) {

        this.dataMovimentacao = dataMovimentacao;
    }


    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }


    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

}
