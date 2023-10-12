package pl.miq3l.EmpikApp.domain.user.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.miq3l.EmpikApp.dtos.GithubUserResponseDto;
import pl.miq3l.EmpikApp.dtos.UserResponseDto;

import java.time.ZoneId;

@Component
public class GithubUserResponseDtoToUserResponseDtoConverter
    implements Converter<GithubUserResponseDto, UserResponseDto> {
    private static final ZoneId zoneId = ZoneId.of("Europe/Warsaw");

    @Override
    public UserResponseDto convert(GithubUserResponseDto source) {
        return new UserResponseDto(
            source.getId(),
            source.getLogin(),
            source.getName(),
            source.getType(),
            source.getAvatarUrl(),
            source.getCreatedAt().withZoneSameInstant(zoneId).toLocalDateTime(),
            calculate(source.getFollowers(), source.getPublicRepos()));
    }

    private Double calculate(Long followers, Long publicRepos) {
        if (followers == 0) {
            return 0.0;
        }
        return (double) Math.round(((double) 6 / followers * (2 + publicRepos)) * 100) / 100;
    }
}
