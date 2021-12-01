package at.lbg.dhp.sharedachievementbackend.data.repository;

import at.lbg.dhp.sharedachievementbackend.data.models.StepCount;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepCountRepository extends JpaRepository<StepCount, StepCountId> {
}
