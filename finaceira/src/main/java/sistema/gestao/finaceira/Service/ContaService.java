package sistema.gestao.finaceira.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import sistema.gestao.finaceira.Conta.Conta;
import sistema.gestao.finaceira.Repository.ContaRepository;

@Service
public class ContaService {


    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta criarConta(Conta conta){
        return contaRepository.save(conta);
    }

    public List<Conta> listarContas(Long usuarioId){
        return contaRepository.findByUsuarioId(usuarioId);
    }
    
}
