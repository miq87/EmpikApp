package pl.miq3l.EmpikApp.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.miq3l.EmpikApp.configuration.properties.GithubIntegrationProperties;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(GithubIntegrationProperties.class)
public class GithubIntegrationConfiguration {
    private final GithubIntegrationProperties properties;

    public GithubIntegrationConfiguration(GithubIntegrationProperties properties) {
        this.properties = properties;
    }

    @Bean("githubRestTemplate")
    public RestTemplate githubRestTemplate() {
        return new RestTemplateBuilder()
            .rootUri(properties.getRootUrl())
            .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
            .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
            .build();
    }
}
