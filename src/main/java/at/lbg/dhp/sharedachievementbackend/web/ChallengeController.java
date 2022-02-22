package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.ChallengeDTO;
import at.lbg.dhp.sharedachievementbackend.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @GetMapping("/all")
    public ResponseEntity<List<ChallengeDTO>> getChallenges() {
        List<ChallengeDTO> challengeDTOs = challengeService.getChallenges();
        return new ResponseEntity<>(challengeDTOs, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<ChallengeDTO> getChallengeByName(String name) {
        ChallengeDTO challengeDTO = challengeService.getChallenge(name);
        return new ResponseEntity<ChallengeDTO>(challengeDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ChallengeDTO>  addChallenge(@RequestBody ChallengeDTO challengeDTO) {
        challengeService.createChallenge(challengeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ChallengeDTO>  updateChallenge(@RequestBody ChallengeDTO challengeDTO) {
        challengeService.updateChallenge(challengeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ChallengeDTO>  deleteChallenge(String name) {
        challengeService.deleteChallenge(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}