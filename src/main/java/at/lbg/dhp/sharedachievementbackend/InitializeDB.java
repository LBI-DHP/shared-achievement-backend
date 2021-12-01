package at.lbg.dhp.sharedachievementbackend;

import at.lbg.dhp.sharedachievementbackend.data.dto.*;
import at.lbg.dhp.sharedachievementbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Component
public class InitializeDB implements ApplicationRunner {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    SubChallengeService subChallengeService;

    @Autowired
    TeamService teamService;

    @Autowired
    PersonService personService;

    @Autowired
    StepCountService stepCountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        challengeService.createChallenge(new ChallengeDTO("Untersberg Hike", 15000));

        subChallengeService.createSubChallenge(new SubChallengeDTO("1/4 the Way Up!", 3750, "Untersberg Hike"));
        subChallengeService.createSubChallenge(new SubChallengeDTO("1/2 the Way Up!", 7500, "Untersberg Hike"));
        subChallengeService.createSubChallenge(new SubChallengeDTO("3/4 the Way Up!", 11250, "Untersberg Hike"));

        teamService.createTeam(new TeamDTO("LBI", "Untersberg Hike"));

        personService.createPerson(new PersonDTO("0", "Sebastian", "LBI"));
        personService.createPerson(new PersonDTO("1", "Jan", "LBI"));
        personService.createPerson(new PersonDTO("2", "Daniela", "LBI"));
        personService.createPerson(new PersonDTO("3", "Mohamed", "LBI"));
        personService.createPerson(new PersonDTO("4", "Florian", "LBI"));
        personService.createPerson(new PersonDTO("5", "Eva", "LBI"));
        personService.createPerson(new PersonDTO("6", "Isabell", "LBI"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stepCountService.createStepCount(new StepCountDTO("0", LocalDate.parse("2021-11-29"), 8500));
        stepCountService.createStepCount(new StepCountDTO("0", LocalDate.parse("2021-11-30"), 8000));
        stepCountService.createStepCount(new StepCountDTO("1", LocalDate.parse("2021-11-29"), 2000));
        stepCountService.createStepCount(new StepCountDTO("1", LocalDate.parse("2021-11-30"), 2300));
        stepCountService.createStepCount(new StepCountDTO("2", LocalDate.parse("2021-11-29"), 3000));
        stepCountService.createStepCount(new StepCountDTO("2", LocalDate.parse("2021-11-30"), 3800));
        stepCountService.createStepCount(new StepCountDTO("3", LocalDate.parse("2021-11-29"), 1500));
        stepCountService.createStepCount(new StepCountDTO("3", LocalDate.parse("2021-11-30"), 4000));
    }
}
