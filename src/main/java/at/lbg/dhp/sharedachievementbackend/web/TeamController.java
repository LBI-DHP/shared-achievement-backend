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

import at.lbg.dhp.sharedachievementbackend.data.dto.RelativeStepsDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.SimpleStepCountDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamDTO;
import at.lbg.dhp.sharedachievementbackend.data.dto.TeamMembersDTO;
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
    public ResponseEntity<List<TeamDTO>> getTeams() {
        List<TeamDTO> teamDTOs = teamService.getTeams();
        return new ResponseEntity<>(teamDTOs, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<TeamDTO> getTeamByName(String name) {
        TeamDTO teamDTO = teamService.getTeam(name);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @GetMapping("/stepCount")
    public ResponseEntity<Integer> getStepCountOfTeam(String name) {
        int stepCount = teamService.getStepCountOfTeam(name);
        return new ResponseEntity<>(stepCount, HttpStatus.OK);
    }

    @GetMapping("/stepCountToday")
    public ResponseEntity<SimpleStepCountDTO> getStepCountOfTeamToday(String name) {
        SimpleStepCountDTO simpleStepCountDTO = teamService.getStepCountOfTeamToday(name);
        return new ResponseEntity<>(simpleStepCountDTO, HttpStatus.OK);
    }

    @GetMapping("/relativeStepCountOfTeamTodayOfChallengeInPercent")
    public ResponseEntity<RelativeStepsDTO> getRelativeStepCountOfTeamTodayOfChallengeInPercent(String name) {
        RelativeStepsDTO relativeStepsDTO = teamService.getRelativeStepCountOfTeamTodayOfChallengeInPercent(name);
        return new ResponseEntity<>(relativeStepsDTO, HttpStatus.OK);
    }

    @GetMapping("/teamMembersStepCountOfToday")
    public ResponseEntity<TeamMembersDTO> getTeamMembersStepCountOfToday(String name) {
        TeamMembersDTO teamMembersDTO = teamService.getStepCountsOfTeamMembersToday(name);
        return new ResponseEntity<>(teamMembersDTO, HttpStatus.OK);
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
