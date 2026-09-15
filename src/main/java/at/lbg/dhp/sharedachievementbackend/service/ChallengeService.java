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

package at.lbg.dhp.sharedachievementbackend.service;

import at.lbg.dhp.sharedachievementbackend.data.dto.ChallengeDTO;
import at.lbg.dhp.sharedachievementbackend.data.models.Challenge;
import at.lbg.dhp.sharedachievementbackend.data.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    public void createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setSteps(challengeDTO.getSteps());
        challengeRepository.save(challenge);
    }

    public void updateChallenge(ChallengeDTO challengeDTO) {
        Optional<Challenge> challenge = challengeRepository.findById(challengeDTO.getName());
        if (challenge.isEmpty()) {
            // exception
        } else {
            challenge.get().setSteps(challengeDTO.getSteps());
            challengeRepository.save(challenge.get());
        }
    }

    public void deleteChallenge(String name) {
        challengeRepository.deleteById(name);
    }

    public ChallengeDTO getChallenge(String name) {
        Challenge challenge = challengeRepository.findById(name).get();

        ChallengeDTO challengeDTO = new ChallengeDTO();
        challengeDTO.setName(challenge.getName());
        challengeDTO.setSteps(challenge.getSteps());

        return challengeDTO;
    }

    public List<ChallengeDTO> getChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();

        List<ChallengeDTO> challengeDTOs = new LinkedList<>();
        for (Challenge challenge : challenges) {
            ChallengeDTO challengeDTO = new ChallengeDTO();
            challengeDTO.setName(challenge.getName());
            challengeDTO.setSteps(challenge.getSteps());
            challengeDTOs.add(challengeDTO);
        }

        return challengeDTOs;
    }
}
