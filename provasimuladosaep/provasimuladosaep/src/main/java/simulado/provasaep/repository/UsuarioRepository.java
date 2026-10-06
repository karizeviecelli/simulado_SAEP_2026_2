package simulado.provasaep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simulado.provasaep.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByLogin(String login);
}
