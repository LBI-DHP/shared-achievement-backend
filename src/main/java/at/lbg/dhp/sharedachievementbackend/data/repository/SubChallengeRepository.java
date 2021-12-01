package at.lbg.dhp.sharedachievementbackend.data.repository;

import at.lbg.dhp.sharedachievementbackend.data.models.SubChallenge;
import at.lbg.dhp.sharedachievementbackend.data.models.SubChallengeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubChallengeRepository extends JpaRepository<SubChallenge, SubChallengeId> {
}
