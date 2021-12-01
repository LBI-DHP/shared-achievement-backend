package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.PersonDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountIdDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountTodayDTO;
import at.lbg.dhp.sharedachievementbackend.service.StepCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stepcount")
public class StepCountController {

    @Autowired
    StepCountService stepCountService;

    @GetMapping("/all")
    public ResponseEntity<List<StepCountDTO>> getStepCounts () {
        List<StepCountDTO> stepCountDTOs = stepCountService.getStepCounts();
        return new ResponseEntity<>(stepCountDTOs, HttpStatus.OK);
    }

    @GetMapping("/findByPersonIdByDay")
    public ResponseEntity<StepCountDTO> getStepCountByPersonIdByDay (StepCountIdDTO stepCountIdDTO) {
        StepCountDTO stepCountDTO = stepCountService.getStepCount(stepCountIdDTO);
        return new ResponseEntity<>(stepCountDTO, HttpStatus.OK);
    }

    @GetMapping("/findByPersonForToday")
    public ResponseEntity<Integer> getStepCountOfPersonToday (String personId) {
        int stepCount = stepCountService.getStepCountOfPersonToday(personId);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @GetMapping("/findByPersonId")
    public ResponseEntity<List<StepCountDTO>> getStepCountByPersonId (String personId) {
        List<StepCountDTO> stepCountDTOs = stepCountService.getStepCounts(personId);
        return new ResponseEntity<>(stepCountDTOs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addStepCount(@RequestBody StepCountDTO stepCountDTO) {
        stepCountService.createStepCount(stepCountDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity updateStepCount(@RequestBody StepCountDTO stepCountDTO) {
        stepCountService.updateStepCount(stepCountDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/push")
    public ResponseEntity pushStepCountOfToday(@RequestBody StepCountTodayDTO stepCountTodayDTO) {
        stepCountService.pushStepCountOfToday(stepCountTodayDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStepCount(@RequestBody StepCountIdDTO id) {
        stepCountService.deleteStepCount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
