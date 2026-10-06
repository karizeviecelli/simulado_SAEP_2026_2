package senai.amanda_santos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.amanda_santos.entity.CategoriaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNomeIgnoreCase(String nome);

    List<CategoriaEntity> findAllByOrderByNomeAsc();
}