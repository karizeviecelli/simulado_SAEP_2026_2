package senai.amanda_santos.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import senai.amanda_santos.entity.ProdutoEntity;
import senai.amanda_santos.model.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Escolha o produto")
    private Long produtoId;

    @NotNull(message = "Escolha entrada ou saída")
    private TipoMovimentacao tipo = TipoMovimentacao.ENTRADA;

    @NotNull(message = "Informe a quantidade")
    @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
    private Integer quantidade;

    @Size(max = 255, message = "Use no máximo 255 caracteres")
    private String observacao;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setProduto(ProdutoEntity produto) {
    }

    public void setResponsavel(String responsavel) {
    }

    public void setDataHora(LocalDateTime now) {

    }
}
