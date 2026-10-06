package senai.amanda_santos.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import senai.amanda_santos.entity.CategoriaEntity;

public class ProdutoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o código do produto")
    @Size(max = 30, message = "Use no máximo 30 caracteres")
    @Column(nullable = false, unique = true, length = 30)
    private String codigo;

    @NotBlank(message = "Informe o nome do produto")
    @Size(max = 120, message = "Use no máximo 120 caracteres")
    @Column(nullable = false, length = 120)
    private String nome;

    @Size(max = 255, message = "Use no máximo 255 caracteres")
    private String descricao;

    @NotBlank(message = "Informe a unidade (UN, CX, KG...)")
    @Size(max = 10, message = "Use no máximo 10 caracteres")
    @Column(nullable = false, length = 10)
    private String unidade = "UN";

    @Column(nullable = false)
    private Integer quantidade = 0;

    @NotNull(message = "Informe o estoque mínimo")
    @Min(value = 0, message = "O estoque mínimo não pode ser negativo")
    @Column(nullable = false)
    private Integer estoqueMinimo = 0;

    @Size(max = 60, message = "Use no máximo 60 caracteres")
    @Column(length = 60)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    public ProdutoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}