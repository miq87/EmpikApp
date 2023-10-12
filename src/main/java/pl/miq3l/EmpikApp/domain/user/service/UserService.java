package pl.miq3l.EmpikApp.domain.user.service;

import org.springframework.stereotype.Service;
import pl.miq3l.EmpikApp.domain.user.converter.GithubUserResponseDtoToUserResponseDtoConverter;
import pl.miq3l.EmpikApp.dtos.GithubUserResponseDto;
import pl.miq3l.EmpikApp.infrastructure.github.GithubIntegrationService;

@Service
public class UserService {
    private final GithubIntegrationService githubIntegrationService;
    private final GithubUserResponseDtoToUserResponseDtoConverter converter;

    public UserService(GithubIntegrationService githubIntegrationService,
                       GithubUserResponseDtoToUserResponseDtoConverter converter) {
        this.githubIntegrationService = githubIntegrationService;
        this.converter = converter;
    }

    public Object getUser(String login) {
        return converter.convert(githubIntegrationService.getUserFromGithub(login));
    }
}
