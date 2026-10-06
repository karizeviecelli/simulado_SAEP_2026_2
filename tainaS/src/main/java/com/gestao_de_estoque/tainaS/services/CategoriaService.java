package com.gestao_de_estoque.tainaS.services;

import com.gestao_de_estoque.tainaS.dtos.CategoriaDto;
import com.gestao_de_estoque.tainaS.entities.CategoriaEntity;
import com.gestao_de_estoque.tainaS.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public CategoriaDto salvar(CategoriaDto categoriaDto) {

        CategoriaEntity categoria = new CategoriaEntity();

        categoria.setNome(categoriaDto.getNome());

        CategoriaEntity categoriaSalva =
                repository.save(categoria);

        return converterEntityParaDto(categoriaSalva);
    }

    public List<CategoriaDto> listar() {

        return repository.findAll()
                .stream()
                .map(this::converterEntityParaDto)
                .toList();
    }

    public CategoriaDto buscarPorId(Long id) {

        CategoriaEntity categoria =
                repository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Categoria não encontrada"
                                )
                        );

        return converterEntityParaDto(categoria);
    }

    public void excluir(Long id) {

        repository.deleteById(id);
    }

    private CategoriaDto converterEntityParaDto(
            CategoriaEntity categoria) {

        CategoriaDto dto = new CategoriaDto();

        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        return dto;
    }
}