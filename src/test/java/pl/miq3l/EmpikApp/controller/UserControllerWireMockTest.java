package pl.miq3l.EmpikApp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.miq3l.EmpikApp.dtos.UserResponseDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerWireMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_user_response_from_github() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        UserResponseDto userResponseDto = getUserResponseDto();

        mockMvc.perform(get("/users/xxx"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.login").value(userResponseDto.getLogin()))
            .andExpect(jsonPath("$.avatarUrl").value(userResponseDto.getAvatarUrl()))
            .andExpect(jsonPath("$.name").value(userResponseDto.getName()));
    }

    private UserResponseDto getUserResponseDto() {
        return new UserResponseDto(
            123456L,
            "xxx",
            "xxx",
            "User",
            "https://avatars.githubusercontent.com/u/41678?v=4",
            LocalDate.now().atStartOfDay(),
            6.37);
    }
}
