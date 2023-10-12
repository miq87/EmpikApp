package pl.miq3l.EmpikApp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class GithubUserResponseDto {
    private Long id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;
    private Long followers;
    @JsonProperty("public_repos")
    private Long publicRepos;

    public GithubUserResponseDto() {
    }

    public GithubUserResponseDto(Long id,
                                 String login,
                                 String name,
                                 String type,
                                 String avatarUrl,
                                 ZonedDateTime createdAt,
                                 Long followers,
                                 Long publicRepos) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.followers = followers;
        this.publicRepos = publicRepos;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getFollowers() {
        return followers;
    }

    public Long getPublicRepos() {
        return publicRepos;
    }
}
