package sistema.gestao.finaceira.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sistema.gestao.finaceira.Service.TransacaoService;
import sistema.gestao.finaceira.Transacao.Transacao;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping("/{contaId}")
    public ResponseEntity<Transacao> registrarTransacao(@PathVariable Long contaId, 
                                                        @RequestBody Transacao transacao) {
        return ResponseEntity.ok(transacaoService.registrarTransacao(contaId, transacao));
    }

    
}
