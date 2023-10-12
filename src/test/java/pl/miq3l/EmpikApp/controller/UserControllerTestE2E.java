package pl.miq3l.EmpikApp.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.miq3l.EmpikApp.configuration.E2ETestsContextConfiguration;
import pl.miq3l.EmpikApp.dtos.UserResponseDto;
import pl.miq3l.EmpikApp.infrastructure.github.GithubServiceIntegrationMock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = E2ETestsContextConfiguration.class)
public class UserControllerTestE2E {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GithubServiceIntegrationMock githubServiceIntegrationMock;

    @BeforeEach
    void init() {
        githubServiceIntegrationMock.init();
    }

    @AfterEach
    void reset() {
        githubServiceIntegrationMock.reset();
    }

    @Test
    void should_return_user_response_from_github() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        UserResponseDto userResponseDto = getUserResponseDto();

        mockMvc.perform(get("/users/xxx"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userResponseDto.getId()))
            .andExpect(jsonPath("$.login").value(userResponseDto.getLogin()))
            .andExpect(jsonPath("$.name").value(userResponseDto.getName()))
            .andExpect(jsonPath("$.avatarUrl").value(userResponseDto.getAvatarUrl()))
            .andExpect(jsonPath("$.createdAt").value(userResponseDto.getCreatedAt().format(formatter)))
            .andExpect(jsonPath("$.calculations").value(userResponseDto.getCalculations()));
    }

    private UserResponseDto getUserResponseDto() {
        return new UserResponseDto(
            123456L,
            "xxx",
            "xxx",
            "User",
            "avatar.jpg",
            LocalDate.now().atStartOfDay(),
            6.37);
    }
}
