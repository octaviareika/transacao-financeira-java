package sistema.gestao.finaceira.Pluggy;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PluggyController {

    private PluggyService pluggyService;

    public PluggyController(PluggyService pluggyService) {
        this.pluggyService = pluggyService;
    }

    @GetMapping("/api/pluggy/token")
    public String gerarToken(){
        return pluggyService.gerarToken();
    }

    @GetMapping("/api/pluggy/link-token")
    public String gerarLinkToken() {
        String userToken = pluggyService.gerarToken(); // Gere o token de usuário
        return pluggyService.gerarLinkToken(userToken);
    }
}
