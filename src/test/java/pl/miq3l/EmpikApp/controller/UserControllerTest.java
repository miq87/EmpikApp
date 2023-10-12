package pl.miq3l.EmpikApp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.miq3l.EmpikApp.domain.user.service.UserService;
import pl.miq3l.EmpikApp.dtos.UserResponseDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void should_return_user_success() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        UserResponseDto userResponseDto = new UserResponseDto(1L,
            "xxx",
            "xxx",
            "User",
            "avatar.jpg",
            LocalDate.now().atStartOfDay(),
            6.37);

        when(userService.getUser("xxx")).thenReturn(userResponseDto);

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
}
