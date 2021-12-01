package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.PersonDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamDTO;
import at.lbg.dhp.sharedachievementbackend.service.PersonService;
import at.lbg.dhp.sharedachievementbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/all")
    public ResponseEntity<List<TeamDTO>> getTeams () {
        List<TeamDTO> teamDTOs = teamService.getTeams();
        return new ResponseEntity<>(teamDTOs, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<TeamDTO> getTeamByName (String name) {
        TeamDTO teamDTO = teamService.getTeam(name);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @GetMapping("/stepCount")
    public ResponseEntity<Integer> getStepCountOfTeam (String name) {
        int stepCount = teamService.getStepCountOfTeam(name);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @GetMapping("/stepCountToday")
    public ResponseEntity<Integer> getStepCountOfTeamToday (String name) {
        int stepCount = teamService.getStepCountOfTeamToday(name);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addTeam(@RequestBody TeamDTO teamDTO) {
        teamService.createTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateTeam(@RequestBody TeamDTO teamDTO) {
        teamService.updateTeam(teamDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTeam(String name) {
        teamService.deleteTeam(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
