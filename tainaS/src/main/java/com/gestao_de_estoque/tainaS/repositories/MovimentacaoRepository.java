package com.gestao_de_estoque.tainaS.repositories;

import com.gestao_de_estoque.tainaS.entities.MovimentacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoRepository  extends JpaRepository<MovimentacaoEntity, Long> {

    List<MovimentacaoEntity> findAllByOrderByDataMovimentacaoDesc();

}
