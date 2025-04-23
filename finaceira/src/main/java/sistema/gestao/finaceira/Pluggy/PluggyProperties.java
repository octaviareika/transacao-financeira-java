package sistema.gestao.finaceira.Pluggy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Component
@ConfigurationProperties(prefix = "pluggy")
public class PluggyProperties {

    @Value("${pluggy.client-id}")
    private String clientId;
    @Value("${pluggy.client-secret}")
    private String clientSecret;
    @Value("${pluggy.base-url}")
    private String baseUrl;

    public PluggyProperties() {
        // Construtor padrão
    }
    public PluggyProperties(String clientId, String clientSecret, String baseUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
    }
    
}
