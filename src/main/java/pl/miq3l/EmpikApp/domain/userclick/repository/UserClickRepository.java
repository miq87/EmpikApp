package pl.miq3l.EmpikApp.domain.userclick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.miq3l.EmpikApp.domain.userclick.model.UserClick;

@Repository
public interface UserClickRepository extends JpaRepository<UserClick, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UserClick uc SET uc.requestCount = uc.requestCount + 1 WHERE uc.id = :id")
    void incrementRequestCount(@Param("id") Long id);
}
