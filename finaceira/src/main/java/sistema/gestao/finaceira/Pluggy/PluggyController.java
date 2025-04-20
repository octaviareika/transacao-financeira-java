package sistema.gestao.finaceira.Pluggy;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class PluggyController {

    private PluggyService pluggyService;

    @GetMapping("/api/pluggy/authorization")
    public String gerarToken(){
        return pluggyService.gerarToken();
    }

    @GetMapping("/api/pluggy/link-token")
    public String gerarLinkToken() {
        String userToken = pluggyService.gerarToken(); // Gere o token de usuário
        return pluggyService.gerarLinkToken(userToken);
    }
}
