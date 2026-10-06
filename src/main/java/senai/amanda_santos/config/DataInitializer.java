package senai.amanda_santos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import senai.amanda_santos.dto.CategoriaDto;
import senai.amanda_santos.dto.UsuarioDto;
import senai.amanda_santos.entity.CategoriaEntity;
import senai.amanda_santos.entity.UsuarioEntity;
import senai.amanda_santos.model.Perfil;
import senai.amanda_santos.repository.CategoriaRepository;
import senai.amanda_santos.repository.UsuarioRepository;

import java.util.List;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            UsuarioEntity admin = new UsuarioEntity();
            admin.setUsername("admin");
            admin.setNome("Administrador");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setPerfil(Perfil.ADMIN);
            usuarioRepository.save(admin);
        }
        if (categoriaRepository.count() == 0) {
            for (String nome : List.of("Material de escritório", "Ferramentas", "EPI", "Limpeza", "Informática")) {
                CategoriaEntity c = new CategoriaEntity();
                c.setNome(nome);
                categoriaRepository.save(c);
            }
        }
    }
}
