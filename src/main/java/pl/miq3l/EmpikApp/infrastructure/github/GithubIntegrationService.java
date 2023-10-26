package pl.miq3l.EmpikApp.infrastructure.github;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.miq3l.EmpikApp.configuration.properties.GithubIntegrationProperties;
import pl.miq3l.EmpikApp.domain.userclick.model.UserClick;
import pl.miq3l.EmpikApp.domain.userclick.service.UserClickService;
import pl.miq3l.EmpikApp.dtos.GithubUserResponseDto;
import pl.miq3l.EmpikApp.exception.DomainException;

import java.util.Objects;

@Service
public class GithubIntegrationService {
    private final GithubIntegrationProperties properties;
    private final UserClickService userClickService;
    private final RestTemplate restTemplate;

    public GithubIntegrationService(GithubIntegrationProperties properties,
                                    UserClickService userClickService,
                                    RestTemplate githubRestTemplate) {
        this.properties = properties;
        this.userClickService = userClickService;
        this.restTemplate = githubRestTemplate;
    }

    public GithubUserResponseDto getUserFromGithub(String login) {
        try {
            ResponseEntity<GithubUserResponseDto> responseEntity = restTemplate.exchange(
                properties.getCompleteUserPath(),
                HttpMethod.GET,
                null,
                GithubUserResponseDto.class,
                login);
            GithubUserResponseDto githubUserResponseDto = responseEntity.getBody();
            incrementIfExists(Objects.requireNonNull(githubUserResponseDto));
            return githubUserResponseDto;
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    private void incrementIfExists(GithubUserResponseDto githubUserResponseDto) {
        if (userClickService.existsById(githubUserResponseDto.getId())) {
            userClickService.incrementRequestCount(githubUserResponseDto.getId());
        } else {
            userClickService.save(
                new UserClick(githubUserResponseDto.getId(), githubUserResponseDto.getLogin(), 1L));
        }
    }
}
