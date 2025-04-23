package sistema.gestao.finaceira.Pluggy;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class PluggyService {

    private final RestTemplate restTemplate;
    private final PluggyProperties pluggyProperties;
    Logger logger = LoggerFactory.getLogger(PluggyService.class);

    public String gerarApiKey() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        var body = Map.of(
            "clientId", pluggyProperties.getClientId(),
            "clientSecret", pluggyProperties.getClientSecret()
        );
    
        var request = new HttpEntity<>(body, headers);

        logger.info("ENVIANDO REQUISIÇÃO PARA GERAR APIKEY: {}", request);

        /*eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiYmE3ZjhjNTRiZWFhMWIzN2RhOTgwMTNmODllNDg0YmQ6YjZmNDU2M2NjYmY1YTIwNWJkMWMyMzAwOGE5YjAwMGQ0OWRhOTNmYzQ4NmQ0ZTk2MmZlOGQxMDRjNDE1YzQyNzYyNDQ1NTc5NGZkYTRlMGYyZWM5MjZmMmJlODMwZDg3ZGMyZjEyMjQxMzBmZjUzNTk0NDY5ZTljN2FiYzA0YzE0ZWNmMGNjMWQyZGFlMTExODA4MDgyMTBlZmJlNDYzMyIsImlhdCI6MTc0NTQ1MjY0NSwiZXhwIjoxNzQ1NDU5ODQ1fQ.B9iYHku0bFyk_iMIfjWSmPJEpLy-9TRlK_Ne1hwkfwwFhv4Jjl-ly7hZoA70pE6Ry4Zo9v-LbW6mpRRwXKoe_iNCtlhE9g6sE4fGyDj79Sn7PFYNmyyhtJq8wn5aSX8e06jpZjBm_xTtJ9iF1QvKQV9bIP1zooyJOpoAONOYzpPqzur-vd7jfl71YznczjrTdLmv6XpaqEwKNMpNgKC9S0_3LV4BSKbSZsvgSbIRh65sNeeSBFINamBK_BBVcgt5YG0iSalkywWZgmZIdGFt58NV6BFVYtFqKQTTGqCwsuIx_2hpNxeUGWTC0mtI6wI14I8ytQ6KNpb6XbdsCkqJEA */
    
        var response = restTemplate.postForEntity(
            "https://api.pluggy.ai/auth",
            request,
            Map.class
        );


    
        if (response.getBody() == null || !response.getBody().containsKey("apiKey")) {
            throw new RuntimeException("Erro ao obter API key");
        }
    
        return (String) response.getBody().get("apiKey");
    }

    /**
     * Gera o token de usuário da Pluggy
     */
    public String gerarToken() {
        String apiKey = gerarApiKey();

        var headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var response = restTemplate.postForEntity(
            pluggyProperties.getBaseUrl() + "/users",
            new HttpEntity<>(null, headers),
            Map.class
        );

        logger.info("Resposta da API /users: {}", response.getBody());


        if (response.getBody() == null || !response.getBody().containsKey("userToken")) {
            throw new RuntimeException("Erro ao gerar token: resposta inválida da API");
        }

        return (String) response.getBody().get("userToken");
    }

    /**
     * Gera o linkToken para o front‑end exibir o widget
     */
    public String gerarLinkToken(String apiKey) {
        var headers = new HttpHeaders();
        headers.setBearerAuth(apiKey); // Use the API Key for authentication
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        var body = Map.of(
            "webhookUrl", "https://example.com/webhook",
            "clientUserId", "My App UserId",
            "oauthRedirectUrl", "https://pluggy.ai/demo",
            "avoidDuplicates", true
        );
    
        var response = restTemplate.postForEntity(
            "https://api.pluggy.ai/connect_token",
            new HttpEntity<>(body, headers),
            Map.class
        );
    
        if (response.getBody() == null || !response.getBody().containsKey("connectToken")) {
            throw new RuntimeException("Erro ao gerar connectToken");
        }
    
        return (String) response.getBody().get("connectToken");
    }

    /**
     * Busca transações reais pelo itemId retornado no callback do widget
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> listarTransacoes(String userToken, String itemId) {
        var headers = new HttpHeaders();
        headers.setBearerAuth(userToken);

        var response = restTemplate.exchange(
            "https://api.pluggy.ai/transactions?itemId=" + itemId,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            Map.class
        );

        return (List<Map<String, Object>>) response.getBody().get("results");
    }
}
