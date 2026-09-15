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