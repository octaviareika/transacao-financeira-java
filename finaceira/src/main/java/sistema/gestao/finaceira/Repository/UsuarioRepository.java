package sistema.gestao.finaceira.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.gestao.finaceira.Usuário.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    

    
} 

