package senai.amanda_santos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.amanda_santos.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String username);

    List<UsuarioEntity> findAllByOrderByNomeAsc();
}
