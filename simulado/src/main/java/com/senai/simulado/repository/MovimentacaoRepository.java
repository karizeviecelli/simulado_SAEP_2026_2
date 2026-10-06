package com.senai.simulado.repository;

import com.senai.simulado.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor<Movimentacao> {

    boolean existsByProdutoId(Long produtoId);

    boolean existsByUsuarioId(Long usuarioId);
}
