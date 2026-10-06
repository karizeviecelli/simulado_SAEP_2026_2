package com.gestao_de_estoque.tainaS.services;

import com.gestao_de_estoque.tainaS.dtos.ProdutoDto;
import com.gestao_de_estoque.tainaS.entities.CategoriaEntity;
import com.gestao_de_estoque.tainaS.entities.ProdutoEntity;
import com.gestao_de_estoque.tainaS.repositories.CategoriaRepository;
import com.gestao_de_estoque.tainaS.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository) {

        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    // =========================
    // SALVAR
    // =========================

    public ProdutoDto salvar(ProdutoDto produtoDto) {

        CategoriaEntity categoria =
                categoriaRepository.findById(produtoDto.getIdCategoria())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categoria não encontrada"
                                )
                        );

        ProdutoEntity produto = new ProdutoEntity();

        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());

        produto.setEstoqueAtual(
                produtoDto.getEstoqueAtual()
        );

        produto.setEstoqueMinimo(
                produtoDto.getEstoqueMinimo()
        );

        produto.setCategoria(categoria);


        ProdutoEntity produtoSalvo =
                produtoRepository.save(produto);

        return converterEntityParaDto(produtoSalvo);
    }


    // =========================
    // LISTAR
    // =========================

    public List<ProdutoDto> listar() {

        return produtoRepository.findAll()
                .stream()
                .map(this::converterEntityParaDto)
                .toList();
    }

    public List<ProdutoDto> listarOrdenadoPorNome() {

        List<ProdutoDto> produtos =
                new java.util.ArrayList<>(listar());


        // Bubble Sort
        for (int i = 0; i < produtos.size() - 1; i++) {

            for (int j = 0;
                 j < produtos.size() - 1 - i;
                 j++) {

                String nomeAtual =
                        produtos.get(j).getNome();

                String proximoNome =
                        produtos.get(j + 1).getNome();


                if (nomeAtual.compareToIgnoreCase(proximoNome) > 0) {

                    ProdutoDto temporario =
                            produtos.get(j);

                    produtos.set(
                            j,
                            produtos.get(j + 1)
                    );

                    produtos.set(
                            j + 1,
                            temporario
                    );
                }
            }
        }

        return produtos;
    }


    // =========================
    // BUSCAR POR ID
    // =========================

    public ProdutoDto buscarPorId(Long id) {

        ProdutoEntity produto =
                produtoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Produto não encontrado"
                                )
                        );

        return converterEntityParaDto(produto);
    }


    // =========================
    // BUSCAR POR NOME
    // =========================

    public List<ProdutoDto> buscarPorNome(String nome) {

        List<ProdutoEntity> produtos;


        if (nome == null ||
                nome.trim().isEmpty()) {

            produtos =
                    produtoRepository.findAll();

        } else {

            produtos =
                    produtoRepository
                            .findByNomeContainingIgnoreCase(nome);
        }


        return produtos.stream()
                .map(this::converterEntityParaDto)
                .toList();
    }


    // =========================
    // EXCLUIR
    // =========================

    public void excluir(Long id) {

        produtoRepository.deleteById(id);
    }


    // =========================
    // CONVERTER ENTITY → DTO
    // =========================

    private ProdutoDto converterEntityParaDto(
            ProdutoEntity produto) {

        ProdutoDto dto = new ProdutoDto();

        dto.setId(produto.getId());

        dto.setNome(produto.getNome());

        dto.setDescricao(
                produto.getDescricao()
        );

        dto.setPreco(
                produto.getPreco()
        );

        dto.setEstoqueAtual(
                produto.getEstoqueAtual()
        );

        dto.setEstoqueMinimo(
                produto.getEstoqueMinimo()
        );


        if (produto.getCategoria() != null) {

            dto.setIdCategoria(
                    produto.getCategoria().getId()
            );

            dto.setNomeCategoria(
                    produto.getCategoria().getNome()
            );
        }


        return dto;
    }
}
