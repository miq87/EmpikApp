package pl.miq3l.EmpikApp.dtos;

import java.time.LocalDateTime;

public class UserResponseDto {
    private final Long id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final LocalDateTime createdAt;
    private final Double calculations;

    public UserResponseDto(Long id, String login, String name, String type, String avatarUrl, LocalDateTime createdAt, Double calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Double getCalculations() {
        return calculations;
    }
}
