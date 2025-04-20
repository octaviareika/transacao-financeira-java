package sistema.gestao.finaceira.Pluggy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Component
@ConfigurationProperties(prefix = "pluggy")
public class PluggyProperties {

    private String clientId;
    private String clientSecret;
    private String baseUrl;
    
}
