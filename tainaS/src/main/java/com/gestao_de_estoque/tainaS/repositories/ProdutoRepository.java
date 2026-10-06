package com.gestao_de_estoque.tainaS.repositories;

import com.gestao_de_estoque.tainaS.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByNomeContainingIgnoreCase(String nome);
}
