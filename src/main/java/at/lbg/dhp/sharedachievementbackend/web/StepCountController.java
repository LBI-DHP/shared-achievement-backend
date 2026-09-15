/*
 * Copyright (c) 2021-2022 Ludwig Boltzmann Institute for Digital Health and Prevention
 *
 * Licensed under the Apache License, Version 2.0 with the Commons Clause License
 * Condition v1.0 (the "License"); you may not use this file except in compliance
 * with the License. A copy of the License is distributed in the LICENSE file at
 * the root of this repository; the Apache License is also available at
 * http://www.apache.org/licenses/LICENSE-2.0 and the Commons Clause condition at
 * https://commonsclause.com/
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: LicenseRef-Apache-2.0-WITH-Commons-Clause
 */

package at.lbg.dhp.sharedachievementbackend.web;

import at.lbg.dhp.sharedachievementbackend.data.dto.SimpleStepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountIdDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.StepCountTodayDTO;
import at.lbg.dhp.sharedachievementbackend.service.StepCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stepcount")
public class StepCountController {

    @Autowired
    StepCountService stepCountService;

    @GetMapping("/all")
    public ResponseEntity<List<StepCountDTO>> getStepCounts() {
        List<StepCountDTO> stepCountDTOs = stepCountService.getStepCounts();
        return new ResponseEntity<>(stepCountDTOs, HttpStatus.OK);
    }

    @GetMapping("/findByPersonIdByDay")
    public ResponseEntity<StepCountDTO> getStepCountByPersonIdByDay(StepCountIdDTO stepCountIdDTO) {
        StepCountDTO stepCountDTO = stepCountService.getStepCount(stepCountIdDTO);
        return new ResponseEntity<>(stepCountDTO, HttpStatus.OK);
    }

    @GetMapping("/findByPersonIdForToday")
    public ResponseEntity<SimpleStepCountDTO> getStepCountByPersonIdForToday(String personId) {
        SimpleStepCountDTO stepCount = stepCountService.getStepCountByPersonIdForToday(personId);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @GetMapping("/findByPersonId")
    public ResponseEntity<List<StepCountDTO>> getStepCountByPersonId(String personId) {
        List<StepCountDTO> stepCountDTOs = stepCountService.getStepCounts(personId);
        return new ResponseEntity<>(stepCountDTOs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StepCountDTO> addStepCount(@RequestBody StepCountDTO stepCountDTO) {
        stepCountService.createStepCount(stepCountDTO);
        return new ResponseEntity<StepCountDTO>(HttpStatus.CREATED);
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
