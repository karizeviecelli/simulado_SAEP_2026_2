package senai.amanda_santos.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senai.amanda_santos.entity.ProdutoEntity;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findByCodigo(String codigo);

    boolean existsByCategoriaId(Long categoriaId);

    List<ProdutoEntity> findAllByOrderByNomeAsc();

    List<ProdutoEntity> findByNomeContainingIgnoreCaseOrCodigoContainingIgnoreCaseOrderByNomeAsc(String nome, String codigo);

    @Query("""
                select p from ProdutoEntity p
                where p.quantidade <= p.estoqueMinimo
                order by p.nome
            """)
    List<ProdutoEntity> findEstoqueBaixo();

    /**
     * Trava a linha durante a movimentação para evitar saldo errado com acessos simultâneos.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProdutoEntity p where p.id = :id")
    Optional<ProdutoEntity> buscarComLock(@Param("id") Long id);
}
