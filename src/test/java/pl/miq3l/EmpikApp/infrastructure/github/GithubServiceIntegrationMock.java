package pl.miq3l.EmpikApp.infrastructure.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import pl.miq3l.EmpikApp.configuration.properties.GithubIntegrationProperties;
import pl.miq3l.EmpikApp.dtos.GithubUserResponseDto;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@Service
public class GithubServiceIntegrationMock {
    private final ObjectMapper objectMapper;
    private final GithubIntegrationProperties properties;
    private final MockRestServiceServer mockServer;

    public GithubServiceIntegrationMock(ObjectMapper objectMapper,
        GithubIntegrationProperties properties,
        @Qualifier("githubRestTemplate") RestTemplate githubRestTemplate) {
        this.mockServer = MockRestServiceServer
            .bindTo(githubRestTemplate)
            .ignoreExpectOrder(true)
            .build();
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    private void addUsersEndpoint(Object responseObjectBody) {
        mockServer
            .expect(requestToUriTemplate(properties.getCompleteUserPath(), "xxx"))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(writeJsonBody(responseObjectBody)));
    }

    public void reset() {
        mockServer.reset();
    }

    public void init() {
        addUsersEndpoint(getUserResponseDto());
    }

    private GithubUserResponseDto getUserResponseDto() {
        return new GithubUserResponseDto(
            123456L,
            "xxx",
            "xxx",
            "User",
            "avatar.jpg",
            LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()),
            65L,
            67L);
    }

    private String writeJsonBody(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
