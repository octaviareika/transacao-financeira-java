package sistema.gestao.finaceira.Service;

import org.springframework.stereotype.Service;

import sistema.gestao.finaceira.Conta.Conta;
import sistema.gestao.finaceira.Repository.ContaRepository;
import sistema.gestao.finaceira.Repository.TransacaoRepo;
import sistema.gestao.finaceira.Transacao.TipoTransacao;
import sistema.gestao.finaceira.Transacao.Transacao;

@Service
public class TransacaoService {
    private TransacaoRepo transacaoRepository;
    private ContaRepository contaRepository;

    public Transacao registrarTransacao(Long contaid, Transacao trans){

        Conta conta = contaRepository.findById(contaid).orElseThrow(() -> 
        new RuntimeException("Erro ao encontrar conta!\n"));

        if (trans.getTipo() == null) {
            throw new RuntimeException("Tipo de transação não pode ser nulo!\n");
        }

        if (trans.getTipo() == TipoTransacao.DESPESA && 
            conta.getSaldo().compareTo(trans.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a despesa!\n");

        }

        conta.setSaldo(conta.getSaldo().add(trans.getValor()) ); // adiciona o valor depositado
        contaRepository.save(conta);
        return transacaoRepository.save(trans);
    }

    
    
}
