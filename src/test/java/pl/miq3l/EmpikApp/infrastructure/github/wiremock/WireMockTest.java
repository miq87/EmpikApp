package pl.miq3l.EmpikApp.infrastructure.github.wiremock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.miq3l.EmpikApp.dtos.GithubUserResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WireMockTest {
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Bean
    public RestTemplate wiremockRestTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Test
    void test() {
        ResponseEntity<GithubUserResponseDto> responseEntity = restTemplate.getForEntity(
            "http://localhost:8080/users/xxx",
            GithubUserResponseDto.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("xxx", responseEntity.getBody().getLogin());
    }

}
