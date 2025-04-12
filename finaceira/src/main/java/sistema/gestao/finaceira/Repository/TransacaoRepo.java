package sistema.gestao.finaceira.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.gestao.finaceira.Transacao.Transacao;

public interface TransacaoRepo extends JpaRepository<Transacao, Long> {
    
    List<Transacao> findByContaId(Long contaId);
    
} 
