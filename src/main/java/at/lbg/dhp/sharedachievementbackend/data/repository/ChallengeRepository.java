package at.lbg.dhp.sharedachievementbackend.data.repository;

import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, String> {
}
