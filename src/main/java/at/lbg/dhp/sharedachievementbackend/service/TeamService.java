package at.lbg.dhp.sharedachievementbackend.service;

import at.lbg.dhp.sharedachievementbackend.data.dto.PersonDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Person;
import at.lbg.dhp.sharedachievementbackend.data.models.StepCount;
import at.lbg.dhp.sharedachievementbackend.data.models.Team;
import at.lbg.dhp.sharedachievementbackend.data.repository.ChallengeRepository;
import at.lbg.dhp.sharedachievementbackend.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
        if(team.getChallenge() != null){
            teamDTO.setChallengeName(team.getChallenge().getName());
        }
        else {
            teamDTO.setChallengeName(null);
        }

        return teamDTO;
    }

    public List<TeamDTO> getTeams() {
        List<Team> teams = teamRepository.findAll();

        List<TeamDTO> teamDTOs = new LinkedList<>();
        for(Team team : teams){
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setName(team.getName());
            if(team.getChallenge() != null){
                teamDTO.setChallengeName(team.getChallenge().getName());
            }
            else {
                teamDTO.setChallengeName(null);
            }
            teamDTOs.add(teamDTO);
        }

        return teamDTOs;
    }

    public int getStepCountOfTeam(String name){
        int steps = 0;

        List<Person> persons = teamRepository.findById(name).get().getPersons();
        for(Person person : persons){
            for(StepCount stepCount : person.getStepCounts()){
                steps = steps + stepCount.getSteps();
            }
        }

        return steps;
    }

    public int getStepCountOfTeamToday(String name){
        int steps = 0;

        List<Person> persons = teamRepository.findById(name).get().getPersons();
        for(Person person : persons){
            for(StepCount stepCount : person.getStepCounts()){
                if(stepCount.getDay().equals(LocalDate.now())) {
                    steps = steps + stepCount.getSteps();
                }
            }
        }

        return steps;
    }
}
