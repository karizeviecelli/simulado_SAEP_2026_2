package simulado.provasaep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simulado.provasaep.entity.ProdutoEntity;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {

    List<ProdutoEntity> findByCodigoContainingIgnoreCaseOrNomeContainingIgnoreCase(String codigo, String nome);

    boolean existsByCodigo(String codigo);

    List<ProdutoEntity> findAllByOrderByNomeAsc();
}
