package com.senai.simulado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(
        @NotBlank(message = "O nome do produto é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres.")
        String nome,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
        String descricao,

        @NotBlank(message = "A unidade de medida é obrigatória.")
        @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres.")
        String unidadeMedida,

        @PositiveOrZero(message = "A quantidade inicial não pode ser negativa.")
        Integer quantidadeInicial) {
}
