package at.lbg.dhp.sharedachievementbackend.service;

import at.lbg.dhp.sharedachievementbackend.data.dto.RelativeStepsDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.SimpleStepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamMembersDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import at.lbg.dhp.sharedachievementbackend.data.models.Person;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCount;
import at.lbg.dhp.sharedachievementbackend.data.models.Team;
import at.lbg.dhp.sharedachievementbackend.data.repository.ChallengeRepository;
import at.lbg.dhp.sharedachievementbackend.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    public void createTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        if (teamDTO.getChallengeName() != null) {
            team.setChallenge(challengeRepository.findById(teamDTO.getChallengeName()).get());
        }
        teamRepository.save(team);
    }

    public void updateTeam(TeamDTO teamDTO) {
        Optional<Team> team = teamRepository.findById(teamDTO.getName());
        if (team.isEmpty()) {
            // exception
        } else {
            if (teamDTO.getChallengeName() == null)
                team.get().setChallenge(null);
            else {
                team.get().setChallenge(challengeRepository.findById(teamDTO.getChallengeName()).get());
            }
            teamRepository.save(team.get());
        }
    }

    public void deleteTeam(String name) {
        teamRepository.deleteById(name);
    }

    public TeamDTO getTeam(String name) {
        Team team = teamRepository.findById(name).get();

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(team.getName());
        if (team.getChallenge() != null) {
            teamDTO.setChallengeName(team.getChallenge().getName());
        } else {
            teamDTO.setChallengeName(null);
        }

        return teamDTO;
    }

    public List<TeamDTO> getTeams() {
        List<Team> teams = teamRepository.findAll();

        List<TeamDTO> teamDTOs = new LinkedList<>();
        for (Team team : teams) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setName(team.getName());
            if (team.getChallenge() != null) {
                teamDTO.setChallengeName(team.getChallenge().getName());
            } else {
                teamDTO.setChallengeName(null);
            }
            teamDTOs.add(teamDTO);
        }

        return teamDTOs;
    }

    public int getStepCountOfTeam(String name) {
        int steps = 0;

        List<Person> persons = teamRepository.findById(name).get().getPersons();
        for (Person person : persons) {
            for (StepCount stepCount : person.getStepCounts()) {
                steps = steps + stepCount.getSteps();
            }
        }

        return steps;
    }

    public SimpleStepCountDTO getStepCountOfTeamToday(String name) {

        SimpleStepCountDTO simpleStepCountDTO = new SimpleStepCountDTO();

        List<Person> persons = teamRepository.findById(name).get().getPersons();
        for (Person person : persons) {
            for (StepCount stepCount : person.getStepCounts()) {
                if (stepCount.getDay().equals(LocalDate.now())) {
                    simpleStepCountDTO.setSteps(simpleStepCountDTO.getSteps() + stepCount.getSteps());
                }
            }
        }

        return simpleStepCountDTO;
    }

    public RelativeStepsDTO getRelativeStepCountOfTeamTodayOfChallengeInPercent(String name) {

        RelativeStepsDTO relativeStepsDTO = new RelativeStepsDTO();
        int steps = 0;

        Team team = teamRepository.findById(name).get();

        List<Person> persons = team.getPersons();
        for (Person person : persons) {
            for (StepCount stepCount : person.getStepCounts()) {
                if (stepCount.getDay().equals(LocalDate.now())) {
                    steps = steps + stepCount.getSteps();
                }
            }
        }

        Challenge challenge = team.getChallenge();
        relativeStepsDTO.setRelativeSteps((100 * steps) / challenge.getSteps());

        return relativeStepsDTO;
    }

    public TeamMembersDTO getStepCountsOfTeamMembersToday(String name){
        TeamMembersDTO teamMembersDTO = new TeamMembersDTO();
        teamMembersDTO.setChallenge(new TeamMembersDTO().new Challenge());
        teamMembersDTO.setPersons(new LinkedList<>());

        Team team = teamRepository.findById(name).get();

        teamMembersDTO.setName(team.getName());
        teamMembersDTO.getChallenge().setName(team.getChallenge().getName());
        teamMembersDTO.getChallenge().setSteps(team.getChallenge().getSteps());

        List<Person> persons = team.getPersons();
        for(Person person : persons){

            TeamMembersDTO.Person newPerson = new TeamMembersDTO().new Person();
            newPerson.setId(person.getId());
            newPerson.setName(person.getName());

            for(StepCount stepCount : person.getStepCounts()){
                if (stepCount.getDay().equals(LocalDate.now())) {
                    newPerson.setSteps((stepCount.getSteps()));
                    newPerson.setRelativeNoOfSteps((100 * stepCount.getSteps()) / team.getChallenge().getSteps());
                }
            }

            teamMembersDTO.getPersons().add(newPerson);
        }

        return teamMembersDTO;
    }
}
