package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.SubChallengeDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.SubChallengeIdDTO;
import at.lbg.dhp.sharedachievementbackend.service.SubChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subchallenge")
public class SubChallengeController {

    @Autowired
    SubChallengeService subChallengeService;

    @GetMapping("/all")
    public ResponseEntity<List<SubChallengeDTO>> getSubChallenges() {
        List<SubChallengeDTO> subChallengeDTOs = subChallengeService.getSubChallenges();
        return new ResponseEntity<>(subChallengeDTOs, HttpStatus.OK);
    }

    @GetMapping("/findByChallengeByName")
    public ResponseEntity<SubChallengeDTO> getSubChallengeByChallengeByName(SubChallengeIdDTO subChallengeIdDTO) {
        SubChallengeDTO subChallengeDTO = subChallengeService.getSubChallenge(subChallengeIdDTO);
        return new ResponseEntity<>(subChallengeDTO, HttpStatus.OK);
    }

    @GetMapping("/findByChallenge")
    public ResponseEntity<List<SubChallengeDTO>> getSubChallengesByChallenge(String challengeName) {
        List<SubChallengeDTO> subChallengeDTOs = subChallengeService.getSubChallenges(challengeName);
        return new ResponseEntity<>(subChallengeDTOs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addSubChallenge(@RequestBody SubChallengeDTO subChallengeDTO) {
        subChallengeService.createSubChallenge(subChallengeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateSubChallenge(@RequestBody SubChallengeDTO subChallengeDTO) {
        subChallengeService.updateSubChallenge(subChallengeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSubChallenge(@RequestBody SubChallengeIdDTO subChallengeIdDTO) {
        subChallengeService.deleteSubChallenge(subChallengeIdDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
