package simulado.provasaep.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import simulado.provasaep.entity.MovimentacaoEstoqueEntity;

import java.util.List;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoqueEntity,Long> {

    List<MovimentacaoEstoqueEntity> findAllByOrderByDataMovimentacaoDesc();

    boolean existsByProdutoId(Long produtoId);
}

