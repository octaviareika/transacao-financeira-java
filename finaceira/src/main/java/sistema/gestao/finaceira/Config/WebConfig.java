package sistema.gestao.finaceira.Config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(() -> {
                    var factory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
                    factory.setConnectTimeout(5000); // Set connect timeout in milliseconds
                    factory.setReadTimeout(5000);    // Set read timeout in milliseconds
                    return factory;
                })
                .build();
    }
    
}
