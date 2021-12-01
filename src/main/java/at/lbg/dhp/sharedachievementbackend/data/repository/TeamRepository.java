package at.lbg.dhp.sharedachievementbackend.data.repository;

import at.lbg.dhp.sharedachievementbackend.data.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
}
