package sistema.gestao.finaceira.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.gestao.finaceira.Conta.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findByUsuarioId(Long usuarioId);
    
}
