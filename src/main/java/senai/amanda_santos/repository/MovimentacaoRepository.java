package senai.amanda_santos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.amanda_santos.entity.MovimentacaoEntity;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, Long> {

    List<MovimentacaoEntity> findAllByOrderByDataHoraDesc();

    List<MovimentacaoEntity> findTop5ByOrderByDataHoraDesc();

    boolean existsByProdutoId(Long produtoId);
}
