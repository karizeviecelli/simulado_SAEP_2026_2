package simulado.provasaep.dto.movimentacao;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import simulado.provasaep.entity.MovimentacaoEstoqueEntity;
import simulado.provasaep.entity.ProdutoEntity;
import simulado.provasaep.entity.TipoMovimentacaoEstoque;
import simulado.provasaep.entity.UsuarioEntity;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueDto(

        @NotNull(message = "O produto é obrigatório")
        Long produtoId,

        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantidade

) {

    public MovimentacaoEstoqueEntity toMovimentacao(ProdutoEntity produto, UsuarioEntity usuario, TipoMovimentacaoEstoque tipo) {
        MovimentacaoEstoqueEntity movimentacao = new MovimentacaoEstoqueEntity();
        movimentacao.setProduto(produto);
        movimentacao.setUsuario(usuario);
        movimentacao.setTipoMovimentacao(tipo);
        movimentacao.setQuantidade(this.quantidade());
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        return movimentacao;
    }

}