package at.lbg.dhp.sharedachievementbackend.data.repository;

import at.lbg.dhp.sharedachievementbackend.data.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}
