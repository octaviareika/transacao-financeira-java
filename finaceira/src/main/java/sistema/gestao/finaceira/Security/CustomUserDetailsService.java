package sistema.gestao.finaceira.Security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sistema.gestao.finaceira.Repository.UsuarioRepository;
import sistema.gestao.finaceira.Usuário.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário nao encontrado com email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
        .username(usuario.getEmail())
        .password(usuario.getSenha())
        .roles("USER") // Replace with dynamic roles if applicable
        .build();
    }

    
}
