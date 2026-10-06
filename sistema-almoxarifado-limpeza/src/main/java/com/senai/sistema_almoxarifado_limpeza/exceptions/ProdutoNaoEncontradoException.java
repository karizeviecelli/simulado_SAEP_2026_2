package com.senai.sistema_almoxarifado_limpeza.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
