package com.gestao_de_estoque.tainaS.repositories;

import com.gestao_de_estoque.tainaS.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
}