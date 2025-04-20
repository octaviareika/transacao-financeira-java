package sistema.gestao.finaceira.Pluggy;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
// Classe responsável por interagir com a API do Pluggy
public class PluggyService {
    

    private final RestTemplate restTemplate;
    PluggyProperties pluggyProperties;

    public String gerarToken(){
        var headers  = new HttpHeaders(); // Configura os headers da requisição
        headers.setContentType(MediaType.APPLICATION_JSON);

        var body = Map.of(
                "client_id", pluggyProperties.getClientId(),
                "client_secret", pluggyProperties.getClientSecret()// configura os corpos da requisição
        );

        var respo = restTemplate.postForEntity(
            pluggyProperties.getBaseUrl() + "/users",
            new HttpEntity<>(body, headers),
            Map.class
        );

        if (respo.getBody() == null || !respo.getBody().containsKey("userToken")){
            throw new RuntimeException("Erro ao gerar token: resposta inválida da API");

        }

        return (String) respo.getBody().get("userToken");
    }

    /**
     * Gera o linkToken para o front‑end exibir o widget
     */
    public String gerarLinkToken(String userToken){
        var header = new HttpHeaders(); // Configura os headers da requisição
        header.setBearerAuth(userToken);
        header.setContentType(MediaType.APPLICATION_JSON);

        var resp = restTemplate.postForEntity(
            "https://api.pluggy.ai/connect_token",
            new HttpEntity<>(Map.of("linkTokenConfig", Map.of("linkTokenType", "ACCOUNT")), header),
            Map.class
        );

        return (String) resp.getBody().get("linkToken");

    }


    /**
     * Busca transações reais pelo itemId retornado no callback do widget
     */

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> listarTransacoes(String userToken, String itemId){

        var headers = new HttpHeaders();
        headers.setBearerAuth(userToken);

         var resp = restTemplate.exchange(
            "https://api.pluggy.ai/transactions?itemId=" + itemId,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            Map.class
        );

        return (List<Map<String,Object>>) resp.getBody().get("results");
    }
}
