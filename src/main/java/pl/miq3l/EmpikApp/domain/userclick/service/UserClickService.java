package pl.miq3l.EmpikApp.domain.userclick.service;

import org.springframework.stereotype.Service;
import pl.miq3l.EmpikApp.domain.userclick.model.UserClick;
import pl.miq3l.EmpikApp.domain.userclick.repository.UserClickRepository;

@Service
public class UserClickService {
    private final UserClickRepository userClickRepository;

    public UserClickService(UserClickRepository userClickRepository) {
        this.userClickRepository = userClickRepository;
    }

    public void save(UserClick userClick) {
        userClickRepository.save(userClick);
    }

    public void incrementRequestCount(Long id) {
        userClickRepository.incrementRequestCount(id);
    }

    public boolean existsById(Long id) {
        return userClickRepository.existsById(id);
    }
}
