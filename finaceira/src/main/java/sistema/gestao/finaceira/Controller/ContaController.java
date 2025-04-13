package sistema.gestao.finaceira.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sistema.gestao.finaceira.Conta.Conta;
import sistema.gestao.finaceira.Service.ContaService;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.criarConta(conta));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Conta>> listarContas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(contaService.listarContas(usuarioId));
    }
}
